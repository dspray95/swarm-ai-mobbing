package agent;

import agent.listener.ThreatEvent;
import agent.module.state.Guard;
import agent.module.state.Mob;
import agent.module.state.Worker;
import agent.pheremone.Pheromone;
import config.SimulationDefaults;
import environment.Coordinate;
import environment.Environment;

import java.io.Serializable;
import java.util.ArrayList;

public class Apid extends Agent implements ThreatEvent, Serializable {

    public static final int STATE_WORKER = 0;
    public static final int STATE_GUARD = 1;
    public static final int STATE_MOB = 2;

    private Coordinate location;
    private int alertLevel;

    public Apid(Coordinate location, Environment environment){
        super(location, environment);
        this.hitpoints = SimulationDefaults.APID_HP;
        this.speed = SimulationDefaults.APID_SPEED;
        this.heatResistance = SimulationDefaults.APID_HEAT_THRESHOLD;
        this.aggression = SimulationDefaults. APID_AGGRESSION;

        this.state = new Worker(this);
        this.alertLevel = 0;
        this.location = location;
    }

    @Override
    public int judgeStateChange(){
        return STATE_WORKER;
    }

    /**
     * Modifies the state to match the given integer value (0-2)
     * If an incorrect value is given the state will resolve to the worker state
     * @param targetState
     */
    public void changeState(int targetState){
        state = null;
        switch(targetState){
            case 0:
                state = new Worker(this);
                break;
            case 1:
                state = new Guard(this);
                break;
            case 2:
                state = new Mob(this);
            default:
                state = new Worker(this);
        }
    }

    /**
     * Increments the value of pheromone strength in the apid's current space
     */
    public void setPheromone(){
        ArrayList<Pheromone> environmentPheromones = environment.getPheromones();
        for(Pheromone pheromone : environmentPheromones){
            if(pheromone.getLocation() == this.location){
                pheromone.increaseStrength();
                return;
            }
        }
        environment.addPheromone(new Pheromone(this.location));
    }

    @Override
    public void threatAlert() {
        setPheromone();
        alertLevel += 100;
        changeState(STATE_GUARD);
    }
}