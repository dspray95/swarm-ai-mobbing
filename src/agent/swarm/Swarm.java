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

    public Swarm(Environment environment){
        this.environment = environment;
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(Environment environment, int swarmSize){
        this.environment = environment;
        this.swarmSize = swarmSize;
    }


    public int getSwarmSize(){ return this.swarmSize; }

    @Override
    public void tickerEvent() {
        for (Apid apid : this){
                apid.tickerEvent();
        }
    }

}
