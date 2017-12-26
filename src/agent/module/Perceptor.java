package agent.module;

import agent.Agent;
import agent.Apid;
import agent.Vespid;
import config.SimulationDefaults;
import environment.Coordinate;
import environment.Space;

import java.util.ArrayList;

public class Perceptor {

    private int perceptionRadius;

    private Agent parent;
    private ArrayList<Apid> perceivedApidae;
    private ArrayList<Vespid> perceivedVespidae;
    private ArrayList<Integer> perceivedPheremones;

    public Perceptor(Agent parent){
        this.parent = parent;
        this.perceptionRadius = SimulationDefaults.PERCEPTION_RADIUS;
    }

    public void perceptionTick(){
        //Empty the perceived lists
        this.perceivedApidae = new ArrayList<>();
        this.perceivedVespidae = new ArrayList<>();
        this.perceivedPheremones = new ArrayList<>();
        Coordinate currentLocation = parent.getLocation();
        //Get square bounds
        ArrayList<Coordinate> squareBounds = new ArrayList<>();
        //Setup bounds
        int iBoundLower = parent.getLocation().X() - perceptionRadius;
        int jBoundLower = parent.getLocation().Y() - perceptionRadius;
        int iBoundLimit = iBoundLower + perceptionRadius*2;
        int jBoundLimit = jBoundLower + perceptionRadius*2;
        for(int i = iBoundLower; i < iBoundLimit; i++){
            for(int j = jBoundLower; j < jBoundLimit; j++) {
                squareBounds.add(new Coordinate(i, j));
            }
        }
        //Get a reference to the environment map
        Space[][] environmentMap = parent.getEnvironment().getEnvironmentMap();
        //Now check through every coordinate in the bounds, also validating if they're within the euclidean radius
        for(Coordinate coordinate : squareBounds){
            int x = coordinate.X();
            int y = coordinate.Y();
            //Only perceive if the coordinate is within the bounds
            if(coordinate.euclideanDistance(currentLocation) <= perceptionRadius && null != environmentMap[x][y]){
                //Now that we've narrowed down to radius, we can check for objects to perceive
                if(0 != environmentMap[x][y].getPheremoneStrength()){
                    perceivedPheremones.add(environmentMap[x][y].getPheremoneStrength());
                }
                if(null != environmentMap[x][y].getApid() && environmentMap[x][y].getApid() != parent){ //exclude parent from perceieve list
                    perceivedApidae.add(environmentMap[x][y].getApid());
                }
                else if(null != environmentMap[x][y].getVespid() && environmentMap[x][y].getVespid() != parent){
                    perceivedVespidae.add(environmentMap[x][y].getVespid());
                }
            }
        }
    }

    public ArrayList<Apid> getApidae(){
        return this.perceivedApidae;
    }
}
