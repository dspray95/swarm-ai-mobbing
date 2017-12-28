package simulation;

import config.SimulationDefaults;
import environment.Environment;

public class Simulator{

    private Environment environment;
    private Logger logger;

    public Simulator(Logger logger){
        environment = new Environment();
        this.logger = logger;
        //for some length of time Environment.notifytick()
        runSimulationForTicks(SimulationDefaults.SIMULATION_LENGTH);
    }

    /**
     * Runs the simulation for a defined number of ticks
     * @param numTicks
     */
    public void runSimulationForTicks(int numTicks){
        for(int i = 0; i <= numTicks; i++){
            environment.tickerEvent();
            logger.addStoredState(environment);
        }
        logger.log();
    }

}
