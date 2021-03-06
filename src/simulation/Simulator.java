package simulation;

import environment.Environment;
import simulation.config.SimulationOptions;

public class Simulator{

    private Environment environment;
    private Logger logger;

    public Simulator(Logger logger, SimulationOptions options){;
        environment = new Environment(options);
        this.logger = logger;
        //for some length of time Environment.notifytick()
    }

    /**
     * Runs the simulation for a defined number of ticks
     * @param numTicks
     */
    public void runSimulationForTicks(int numTicks){

        environment.populate();
        for(int i = 0; i < numTicks; i++){
            long startTime = System.nanoTime();
            environment.tickerEvent();
            logger.addStoredState(environment);
            long endTime = System.nanoTime();
            System.out.println("tick " + i + " in: " + ((endTime - startTime)/1000000) + "ms"); //TODO log file
        }
        logger.log();
    }

}
