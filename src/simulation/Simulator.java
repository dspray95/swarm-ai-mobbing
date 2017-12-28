package simulation;

import config.SimulationDefaults;
import environment.Environment;

public class Simulator{

    static Environment environment;
    static Logger logger;

    public static void main(String args[]){
        environment = new Environment();
        //for sometime Environment.notifytick etc...
        runSimulationForTicks(SimulationDefaults.SIMULATION_LENGTH);
    }

    public static void runSimulationForTicks(int numTicks){
        for(int i = 0; i <= numTicks; i++){
            environment.tickerEvent();
            logger.log(environment);
        }
    }

}
