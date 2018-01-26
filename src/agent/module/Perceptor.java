package agent.module;

import agent.Agent;
import agent.Apid;
import agent.Vespid;
import agent.listener.ThreatEvent;
import agent.module.state.Guard;
import agent.module.state.Mob;
import agent.module.state.Worker;
import agent.pheremone.Pheromone;
import environment.Coordinate;
import environment.Environment;
import simulation.config.SimulationDefaults;

import java.io.Serializable;
import java.util.ArrayList;

public class Perceptor implements Serializable {

    private int perceptionRadius;

    private Agent parent;
    private ThreatEvent threatObserver;
    private ArrayList<Apid> perceivedWorkers;
    private ArrayList<Apid> perceivedGuards;
    private ArrayList<Apid> perceivedMob;
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
        this.perceivedWorkers = new ArrayList<>();
        this.perceivedGuards = new ArrayList<>();
        this.perceivedMob = new ArrayList<>();
        this.perceivedVespidae = new ArrayList<>();
        this.perceivedPheremones = new ArrayList<>();
        Coordinate currentLocation = parent.getLocation();
        //Get a reference to the environment map
        Environment environment = parent.getEnvironment();
        //Loop through each object belonging to the environment to see if it is in perceptive range
        for(Apid apid : environment.getApidSwarm()){ //check for apidae
            if(apid.getLocation().squareDistance(currentLocation) <= perceptionRadius){
                if(apid.getState().getClass() == Worker.class){
                    this.perceivedWorkers.add(apid);
                }
                else if(apid.getState().getClass() == Guard.class){
                    this.perceivedGuards.add(apid);
                }
                else if(apid.getState().getClass() == Mob.class){
                    this.perceivedMob.add(apid);
                }
            }
        }
        for(Vespid vespid : environment.getVespidae()){ //check for vespidae, if detected broadcast an alert
            if(vespid.getLocation().squareDistance(currentLocation) <= perceptionRadius){
                perceivedVespidae.add(vespid);
                if(threatObserver!=null) {
                    threatObserver.threatAlert();
                }
            }
        }
        for(Pheromone pheromone : environment.getPheromones()){ //Check for pheromones
            if(pheromone.getLocation().squareDistance(currentLocation) <= perceptionRadius){
                perceivedPheremones.add(pheromone);
            }
        }
    }


    public ArrayList<Apid> getPerceivedWorkers() {
        return perceivedWorkers;
    }

    public ArrayList<Apid> getPerceivedGuards() {
        return perceivedGuards;
    }

    public ArrayList<Apid> getPerceivedMob() {
        return perceivedMob;
    }
}
