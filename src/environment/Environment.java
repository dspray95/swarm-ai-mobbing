package environment;

import config.SimulationOptions;

public class Environment {

    private Space[][] environmentMap;

    public Environment(){
        int environmentSize = SimulationOptions.ENVIRONMENT_SIZE;
        this.environmentMap = new Space[environmentSize][environmentSize];
    }

}
