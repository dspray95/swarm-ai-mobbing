package simulation.config;

import java.io.Serializable;

public class SimulationOptions implements Serializable{

    int swarmSize;

    public SimulationOptions(){
        this.swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public int getSwarmSize() {
        return swarmSize;
    }

    public void setSwarmSize(int swarmSize) {
        this.swarmSize = swarmSize;
    }


}
