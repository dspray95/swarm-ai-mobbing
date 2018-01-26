package agent.swarm;

import agent.Apid;
import simulation.config.SimulationDefaults;
import environment.Environment;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm extends ArrayList<Apid> implements TickerEventListener, Serializable{


    private int swarmSize;
    transient private Environment environment;

    transient private Swarm workers;
    transient private Swarm guards;
    transient private Swarm mob;

    public Swarm(Environment environment){
        this.environment = environment;
        this.workers = new Swarm(environment);
        this.guards = new Swarm(environment);
        this.mob = new Swarm(environment);
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(Environment environment, int swarmSize){
        this.environment = environment;
        this.swarmSize = swarmSize;
    }

    @Override
    public void tickerEvent() {
        for (Apid apid : this){
                apid.tickerEvent();
        }
    }

    public int getSwarmSize(){ return this.swarmSize; }

    public Swarm getWorkers() {
        return workers;
    }

    public Swarm getGuards() {
        return guards;
    }

    public Swarm getMob() {
        return mob;
    }
}
