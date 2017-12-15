package environment;

import agent.Apid;
import agent.swarm.Swarm;
import com.sun.javaws.exceptions.InvalidArgumentException;
import config.SimulationDefaults;

import java.util.Random;

public class Environment {

    private Space[][] environmentMap;
    private Swarm apidSwarm;

    public Environment(){
        int environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
        this.environmentMap = new Space[environmentSize][environmentSize];
        apidSwarm = new Swarm();
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
        for(int i = 0; i < apidSwarm.getSwarmSize(); i++){
            Coordinate location = generateFuzzyCoordinate(populationCenter, SimulationDefaults.SWARM_DEPLOYMENT_AREA);
            Apid apid = new Apid(location);
            apidSwarm.addAgent(apid, i); //Add Apid at index i
        }
    }

    /**
     * Generates a random coordinate within a square with the edge length of boundXY from the center coordinate
     * @param center to be the center of the bounding square
     * @param boundXY size of the bounding square
     * @return a random coordinate inside the bounding square
     */
    public Coordinate generateFuzzyCoordinate (Coordinate center, int boundXY) throws InvalidArgumentException{
        Random r = new Random();
        //Ensure that the center of the bound is inside the environment area
        if(center.X() < 0 || center.Y() < 0 || boundXY < 0){
            String[] err = new String[]{"Center coordinates and boundXY must be positive"};
            throw new InvalidArgumentException(err);
        }

        int boundX = boundXY;
        int boundY = boundXY;
        int lowerBoundX = center.X() - boundX/2;
        int lowerBoundY = center.Y() - boundY/2;

        //First check to see if we might accidentally try to deploy outside the environment
        if(lowerBoundX + boundX > SimulationDefaults.ENVIRONMENT_SIZE){
            boundX = SimulationDefaults.ENVIRONMENT_SIZE - lowerBoundX;
        }
        if(lowerBoundY + boundY > SimulationDefaults.ENVIRONMENT_SIZE){
            boundY = SimulationDefaults.ENVIRONMENT_SIZE - lowerBoundY;
        }

        int fuzzyX = r.nextInt(boundX) + lowerBoundX;
        int fuzzyY = r.nextInt(boundY) + lowerBoundY;

        return new Coordinate(fuzzyX, fuzzyY);
    }

    public Swarm getApidSwarm(){return this.apidSwarm;}

}
