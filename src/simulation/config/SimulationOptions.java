package simulation.config;

public class SimulationOptions {

    int swarmSize;
    boolean multithreading;

    public SimulationOptions(){
        this.swarmSize = SimulationDefaults.SWARM_SIZE;
        this.multithreading = SimulationDefaults.MULTITHREADING;
    }

    public int getSwarmSize() {
        return swarmSize;
    }

    public void setSwarmSize(int swarmSize) {
        this.swarmSize = swarmSize;
    }

    public boolean isMultithreading() {
        return multithreading;
    }

    public void setMultithreading(boolean multithreading) {
        this.multithreading = multithreading;
    }

}
