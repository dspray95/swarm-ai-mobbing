package agent.swarm;

import agent.Agent;
import config.SimulationDefaults;

public class Swarm {

    private Agent[] swarm;
    private int swarmSize;

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

    public Agent[] getAgents(){return this.swarm;}
    public int getSwarmSize(){ return this.swarmSize; }
}
