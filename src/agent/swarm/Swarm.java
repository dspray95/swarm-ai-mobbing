package agent.swarm;

import agent.Apid;
import config.SimulationDefaults;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm extends ArrayList<Apid> implements TickerEventListener, Serializable{

    private int swarmSize;
    transient private boolean multithreading;

    public Swarm(){
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(int swarmSize){
        this.swarmSize = swarmSize;
    }

    public void setMultithreading(boolean multithreading){
        this.multithreading = multithreading;
    }

    public int getSwarmSize(){ return this.swarmSize; }

    @Override
    public void tickerEvent() {
        if(multithreading){
            for (Apid apid : this){
                apid.tickerEvent();
            }
        }
        else{
            int processors = Runtime.getRuntime().availableProcessors();
            //prep for running a multithread
        }
    }
}
