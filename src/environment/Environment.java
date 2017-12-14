package environment;

import agent.Apid;
import agent.swarm.Swarm;
import config.SimulationDefaults;

import java.util.Random;

public class Environment {

    private Space[][] environmentMap;
    private Swarm apisSwarm;

    public Environment(){
        int environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
        this.environmentMap = new Space[environmentSize][environmentSize];
        apisSwarm = new Swarm();
    }

    /**
     * Creates agents on environment and maps them to an initial position
     */
    public void Populate(){
        Coordinate populationCenter = new Coordinate(
                SimulationDefaults.ENVIRONMENT_SIZE/2,
                SimulationDefaults.ENVIRONMENT_SIZE/2
        ); //Get the center of the environment

        //Create apids and add them to swarm
        for(int i = 0; i<= apisSwarm.getSwarmSize(); i++){
            Coordinate location = generateFuzzyCoordinate(populationCenter, SimulationDefaults.SWARM_DEPLOYMENT_AREA);
            Apid apid = new Apid(location);
            apisSwarm.addAgent(apid, i); //Add Apid at index i
        }
    }

    /**
     * Generates a random coordinate within a square with the edge length of boundXY from the center coordinate
     * @param center to be the center of the bounding square
     * @param boundXY size of the bounding square
     * @return a random coordinate inside the bounding square
     */
    public Coordinate generateFuzzyCoordinate(Coordinate center, int boundXY){
        Random r = new Random();
        int lowerBoundX = center.X() - boundXY/2;
        int lowerBoundY = center.Y() - boundXY/2;
        int fuzzyX = r.nextInt(boundXY) + lowerBoundX;
        int fuzzyY = r.nextInt(boundXY) + lowerBoundY;

        return new Coordinate(fuzzyX, fuzzyY);
    }

}
