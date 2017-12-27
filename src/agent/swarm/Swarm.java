package agent.swarm;

import agent.Apid;
import config.SimulationDefaults;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm extends ArrayList<Apid> implements Serializable{

    private ArrayList<Apid>[] swarm;
    private int swarmSize;

    public Swarm(){
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(int swarmSize){
        this.swarmSize = swarmSize;
    }

    public int getSwarmSize(){ return this.swarmSize; }
}
