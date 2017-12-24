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
        this.environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
        this.environmentMap = CreateEnvironmentMap();
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
    }

    /**
     * Generates a sparse 2d matrix on which agents operate
     * @return 2d sparse matrix
     */
    public Space[][] CreateEnvironmentMap(){
        Space[][] environmentMap = new Space[this.environmentSize][];
        for(int i = 0; i < environmentMap.length; i++){
            environmentMap[i] = new Space[this.environmentSize];
        }
        return environmentMap;
    }

    /**
     * Creates agents on the simulation environment and maps them to an initial position
     * @param  argsPopulation deployment area size, swarm size
     */
    public void Populate(int... argsPopulation) throws IllegalArgumentException{
        long startTime = System.nanoTime();
        //TODO: Varargs validation function, reduce boilerplate
        //varargs validation for deployment area
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
        //varargs validation for swarm size
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
        //Get the center of  the environment
        Coordinate environmentCenter = new Coordinate(
                environmentSize/2,
                environmentSize/2
        );
        //Create apidae and add them to swarm
        for(int i = 0; i < apidSwarm.getSwarmSize(); i++){
            Coordinate location = new Coordinate();
            try {
                 location = generateFuzzyCoordinate(environmentCenter, deploymentArea);
                 if(null != environmentMap[location.X()][location.Y()]){
                     boolean spaceHasObject = environmentMap[location.X()][location.Y()].getApid() != null ? true : false;
                     while (spaceHasObject) {
                        location = CoordinateNudge(location);
                        //Check if the new location is occupied
                        if (null == environmentMap[location.X()][location.Y()]){
                            spaceHasObject = false;
                        }
                    }
                 }
            }catch(IllegalArgumentException e){
                System.out.print(e);
                return;
                //TODO error recovery
            }
            finally {
                addApid(new Apid(location, this), i, location);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Populated in: " + ((endTime - startTime)/10000) + "ms"); //TODO Log file
    }

    public void addApid(Apid apid, int index, Coordinate location){
        Space space = new Space();
        space.setApid(apid);
        environmentMap[location.X()][location.Y()] = space;
        apidSwarm.addAgent(apid, index);
    }
    /**
     * Moves the provided coordinate by a vector of 1 in a random cardinal direction
     * @param coordinate coordinate to nudge
     * @return new coordinate
     */
    public Coordinate CoordinateNudge(Coordinate coordinate){
        Random r = new Random();
        int direction = r.nextInt(3);
        switch(direction){
            case 0:
                coordinate.setX(coordinate.X()+1);
                break;
            case 1:
                coordinate.setX(coordinate.X()-1);
                break;
            case 2:
                coordinate.setY(coordinate.Y()+1);
                break;
            case 3:
                coordinate.setY(coordinate.Y()-1);
                break;
        }
        return coordinate;
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

    public Space[][] getEnvironmentMap(){return this.environmentMap;}
    public Swarm getApidSwarm(){return this.apidSwarm;}

}

