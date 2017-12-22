package agent.module;

import agent.Agent;
import agent.Apid;
import agent.Vespid;
import config.SimulationDefaults;
import environment.Coordinate;
import environment.Space;

import java.util.ArrayList;

public class PerceptionModule {

    int perceptionRadius;

    Agent parent;
    ArrayList<Apid> perceivedApidae;
    ArrayList<Vespid> percievedVespidae;
    ArrayList<Integer> perceivedPheremones;

    public PerceptionModule(Agent parent){
        this.parent = parent;
        this.perceptionRadius = SimulationDefaults.PERCEPTION_RADIUS;
    }

    public void perceptionTick(){
        ArrayList<Apid> perceivedApidae = new ArrayList<>();
        ArrayList<Vespid> perceivedVespidae = new ArrayList<>();
        ArrayList<Integer> percievedPheremones = new ArrayList<>();
        Coordinate currentLocation = parent.getLocation();
        //Get square bounds
        Coordinate[] squareBounds = new Coordinate[perceptionRadius*2];
        for(int i = 1; i < perceptionRadius*2; i++){
            for(int j = 1; j < perceptionRadius*2; j++) {
                squareBounds[i] = new Coordinate(i, j);
            }
        }
        //Get a reference to the environment map
        Space[][] environmentMap = parent.getEnvironment().getEnvironmentMap();
        //Now we can check through every coordinate in the bounds, also validating if they're within the euclidean radius
        for(Coordinate coordinate : squareBounds){
            int x = coordinate.X();
            int y = coordinate.Y();
            //Only perceive if the coordinate is within the bounds
            if(coordinate.EuclideanDistance(currentLocation) <= perceptionRadius && null != environmentMap[x][y]){
                //Now that we've narrowed down to radius, we can check for objects to perceive
                if(0 != environmentMap[x][y].getPheremoneStrength()){
                    percievedPheremones.add(environmentMap[x][y].getPheremoneStrength());
                }
                if(null != environmentMap[x][y].getApid()){
                    perceivedApidae.add(environmentMap[x][y].getApid());
                }
                else if(null != environmentMap[x][y].getVespid()){
                    perceivedVespidae.add(environmentMap[x][y].getVespid());

                }
            }
        }
    }

}
