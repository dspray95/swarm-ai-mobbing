package agent.swarm;

import agent.Apid;
import config.SimulationDefaults;

import java.io.Serializable;

public class Swarm implements Serializable{

    private Apid[] swarm;
    private int swarmSize;

    public Swarm(){
        swarmSize = SimulationDefaults.SWARM_SIZE;
        swarm = new Apid[SimulationDefaults.SWARM_SIZE];
    }

    public Swarm(int swarmSize){
        this.swarmSize = swarmSize;
        swarm = new Apid[swarmSize];
    }

    public void addAgent(Apid agent, int index){
        swarm[index] = agent;
    }

    public Apid[] getAgents(){return this.swarm;}
    public int getSwarmSize(){ return this.swarmSize; }
}
