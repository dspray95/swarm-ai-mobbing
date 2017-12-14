package agent.swarm;

import agent.Agent;
import config.SimulationDefaults;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Swarm {

    Agent[] swarm;
    int swarmSize;

    public Swarm(){
        swarmSize = SimulationDefaults.SWARM_SIZE;
        swarm = new Agent[SimulationDefaults.SWARM_SIZE];
    }

    public Swarm(int swarmSize){
        this.swarmSize = swarmSize;
        swarm = new Agent[swarmSize];
    }

    public void addAgent(Agent agent, int index){
        swarm[index] = agent;
    }

    public Agent[] getSwarm(){return this.swarm;}
    public int getSwarmSize(){ return this.swarmSize; }
}
