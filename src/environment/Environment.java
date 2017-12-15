package environment;

import agent.Apid;
import agent.swarm.Swarm;
import config.SimulationDefaults;

import java.util.Random;

public class Environment {

    private Space[][] environmentMap;
    private int environmentSize;
    private Swarm apidSwarm;

    public Environment(int... argSwarmSize) throws IllegalArgumentException{
        //optional configuration for swarm size
        if(argSwarmSize.length > 0){
            try{
                this.apidSwarm = new Swarm(argSwarmSize[0]);
            }
            catch(ArrayIndexOutOfBoundsException e){
                throw new IllegalArgumentException( "optional swarm size must be greater than 0");
            }
        }
        else{
            this.apidSwarm = new Swarm();
        }
        this.environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
        this.environmentMap = new Space[this.environmentSize][this.environmentSize];
    }

    /**
     * Creates agents on environment and maps them to an initial position
     * @param  argsPopulation deployment area size, swarm size
     */
    public void Populate(int... argsPopulation) throws IllegalArgumentException{
        long startTime = System.nanoTime();
        //optional configuration for deployment area
        int deploymentArea;
        if(argsPopulation.length > 0){
            if(argsPopulation[0] > 0){
                deploymentArea = argsPopulation[0];
            } else{
                throw new IllegalArgumentException("Deployment area must be greater than 0");
            }
        }
        else{
            deploymentArea = SimulationDefaults.SWARM_DEPLOYMENT_AREA;
        }
        //Optional configs for swarm size
        if(argsPopulation.length > 1){
            if(argsPopulation[1] > 0){
                this.apidSwarm = new Swarm(argsPopulation[1]);
            } else{
                throw new IllegalArgumentException("Swarm size must be greater than 0");
            }
        }
        else{
            this.apidSwarm = new Swarm(SimulationDefaults.SWARM_SIZE);
        }


        Coordinate populationCenter = new Coordinate(
                environmentSize/2,
                environmentSize/2
        ); //Get the center of the environment

        //Create apidae and add them to swarm
        for(int i = 0; i < apidSwarm.getSwarmSize(); i++){
            Coordinate location;
            try {
                 location = generateFuzzyCoordinate(populationCenter, deploymentArea);
            }catch(IllegalArgumentException e){
                System.out.print(e);
                return;
                //TODO error recovery
            }
            Apid apid = new Apid(location);
            apidSwarm.addAgent(apid, i); //Add Apid at index i
        }

        long endTime = System.nanoTime();
        System.out.println("Populated in: " + (endTime - startTime) + "ns"); //TODO Log
    }

    /**
     * Generates a random coordinate within a square with the edge length of boundXY from the center coordinate
     * @param center to be the center of the bounding square
     * @param boundXY size of the bounding square
     * @return a random coordinate inside the bounding square
     */
    public Coordinate generateFuzzyCoordinate (Coordinate center, int boundXY) throws IllegalArgumentException{

        //Ensure that the center of the bound is inside the environment area
        if(center.X() < 0 || center.Y() < 0 || boundXY < 0){
            String err = "Center coordinates and boundXY must be positive";
            throw new IllegalArgumentException(err);
        }

        int boundX = boundXY;
        int boundY = boundXY;
        int lowerBoundX = center.X() - boundX/2;
        int lowerBoundY = center.Y() - boundY/2;
        //check to see if we might accidentally try to deploy outside the environment
        //If true limit the bound to the edge of the environment
        if(lowerBoundX + boundX > environmentSize){
            boundX = environmentSize - lowerBoundX;
        }
        if(lowerBoundY + boundY > environmentSize){
            boundY = environmentSize - lowerBoundY;
        }
        //Return a new coordinate with random x and y variable within the bounding box
        Random r = new Random();
        return new Coordinate(r.nextInt(boundX) + lowerBoundX, r.nextInt(boundY) + lowerBoundY);
    }

    public Swarm getApidSwarm(){return this.apidSwarm;}

}
