package simulation.config;

public class SimulationOptions {

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
