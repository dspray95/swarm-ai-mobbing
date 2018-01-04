package simulation;

import config.SimulationDefaults;

public class SimulationOptions {

    private int swarmSize;
    private boolean multithreading;

    public SimulationOptions(){
        this.swarmSize = SimulationDefaults.SWARM_SIZE;
        this.multithreading = false;
    }

    public int getSwarmSize() {
        return swarmSize;
    }

    public boolean getMultithreading() {
        return multithreading;
    }

    public void setSwarmSize(int swarmSize) {
        this.swarmSize = swarmSize;
    }

    public void setMultithreading(boolean multithreading) {
        this.multithreading = multithreading;
    }
}
