package agent;

import agent.listener.ThreatEvent;
import agent.module.state.State;
import agent.module.state.Worker;
import agent.pheremone.Pheromone;
import agent.swarm.Swarm;
import environment.Coordinate;
import environment.Environment;
import simulation.config.SimulationDefaults;

import java.io.Serializable;
import java.util.ArrayList;

public class Apid extends Agent implements ThreatEvent, Serializable {

    public static final int STATE_WORKER = 0;
    public static final int STATE_GUARD = 1;
    public static final int STATE_MOB = 2;

    private Coordinate location;
    transient private int alertLevel;
    transient private Swarm swarm;

    public Apid(Coordinate location, Environment environment){
        super(location, environment);
        this.hitpoints = SimulationDefaults.APID_HP;
        this.speed = SimulationDefaults.APID_SPEED;
        this.heatResistance = SimulationDefaults.APID_HEAT_THRESHOLD;
        this.aggression = SimulationDefaults. APID_AGGRESSION;
        this.state = new Worker(this);
        this.swarm = environment.getApidSwarm();
        this.swarm.getWorkers().add(this);
        this.alertLevel = 0;
        this.location = location;
    }

    @Override
    public State judgeStateChange(){
        /**
         * In order to decide whether to join guards --
         * 1. Get perceived number of guards n
         * 2. If n <= i (some integer) join guards
         * 3. As n increases, chance to join c also increases
         * 4. Perform random chance to join (modified by aggression a)
         *
         * So c = f(n, a)
         */
        /**
         * In order to decide whether to leave guards and work --
         * 1. Get perceived number of guards n
         * 2. If n >= i leave guards
         * 3. As n increases chance to leave (c) also increases
         * 4. As threat level t increases chance to leave decreases
         * 5. perform random chance to leave (modified by aggression a)
         *
         * So c = f(n, t, a) !!!
         */


        return new Worker(this);
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
    }
}