package agent.module;

import agent.Agent;
import agent.Apid;
import agent.Vespid;
import agent.listener.ThreatEvent;
import agent.pheremone.Pheromone;
import config.SimulationDefaults;
import environment.Coordinate;
import environment.Environment;

import java.io.Serializable;
import java.util.ArrayList;

public class Perceptor implements Serializable {

    private int perceptionRadius;

    private Agent parent;
    private ThreatEvent threatObserver;
    private ArrayList<Apid> perceivedApidae;
    private ArrayList<Vespid> perceivedVespidae;
    private ArrayList<Pheromone> perceivedPheremones;

    public Perceptor(Agent parent, ThreatEvent... threatObserver){
        this.parent = parent;
        this.perceptionRadius = SimulationDefaults.PERCEPTION_RADIUS;
        if(threatObserver.length > 0){
            this.threatObserver = threatObserver[0];
        }
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
        Environment environment = parent.getEnvironment();
        //Loop through each object belonging to the environment to see if it is in perceptive range
        for(Apid apid : environment.getApidSwarm()){ //check for apidae
            if(apid.getLocation().euclideanDistance(currentLocation) <= perceptionRadius){
                perceivedApidae.add(apid);
            }
        }
        for(Vespid vespid : environment.getVespidae()){ //check for vespidae, if detected broadcast an alert
            if(vespid.getLocation().euclideanDistance(currentLocation) <= perceptionRadius){
                perceivedVespidae.add(vespid);
                if(threatObserver!=null) {
                    threatObserver.threatAlert();
                }
            }
        }
        for(Pheromone pheromone : environment.getPheromones()){ //Check for pheromones
            if(pheromone.getLocation().euclideanDistance(currentLocation) <= perceptionRadius){
                perceivedPheremones.add(pheromone);
            }
        }
    }

    public ArrayList<Apid> getApidae(){
        return this.perceivedApidae;
    }
}
