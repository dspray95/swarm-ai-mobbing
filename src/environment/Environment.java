package environment;

import agent.Apid;
import agent.Vespid;
import agent.pheremone.Pheromone;
import agent.swarm.Swarm;
import config.SimulationDefaults;
import simulation.Simulator;
import simulation.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Environment implements Serializable, TickerEventListener {

    private int environmentSize;

    private Simulator simulator;
    private Swarm apidSwarm;
    private ArrayList<Vespid> vespidae;
    private ArrayList<Pheromone> pheromones;

    static ArrayList<TickerEventListener> tickerEventListeners;

    public Environment(int... argSwarmSize) throws IllegalArgumentException{
        this.simulator = simulator;
        this.environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
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
        this.vespidae = new ArrayList<>();
        this.pheromones = new ArrayList<>();
    }

    /**
     * Creates agents on the simulation environment and maps them to an initial position
     * @param  argsPopulation deployment area size, swarm size
     */
    public void populate(int... argsPopulation) throws IllegalArgumentException{
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
            }catch(IllegalArgumentException e){
                System.out.print(e);
                return; //TODO error recovery
            }
            finally {
                addApid(new Apid(location, this), i, location);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Populated in: " + ((endTime - startTime)/10000) + "ms"); //TODO log file
    }

    /**
     * Add a pre-created apid to the environment space
     * @param apid Apid agent to add
     * @param index index at which to add the apid to the swarm array
     * @param location location at which the apid should appear
     */
    public void addApid(Apid apid, int index, Coordinate location){
        //TODO validation
        apidSwarm.add(apid);
        registerTickerListener(apid);
    }

    public void addVespid(Vespid vespid){
        vespidae.add(vespid);
        registerTickerListener(vespid);
    }

    public void addPheromone(Pheromone pheromone){
        this.pheromones.add(pheromone);
        registerTickerListener(pheromone);
    }
    /**
     * Moves the provided coordinate by a vector of 1 in a random cardinal direction
     * @param coordinate coordinate to nudge
     * @return new coordinate
     */
    public Coordinate coordinateNudge(Coordinate coordinate){
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

    public void registerTickerListener(TickerEventListener listener){
        tickerEventListeners.add(listener);
    }

    public void unregisterTickerListener(TickerEventListener listener){
        tickerEventListeners.remove(listener);
    }

    public void notifyTickerListeners(){
        for(TickerEventListener listener : tickerEventListeners){
            listener.tickerEvent();
        }
    }

    public Simulator getSimulator(){return this.getSimulator();}
    public Swarm getApidSwarm(){return this.apidSwarm;}
    public ArrayList<Vespid> getVespidae(){return this.vespidae;}
    public ArrayList<Pheromone> getPheromones(){return this.pheromones;}

    @Override
    public void tickerEvent() {
        for(TickerEventListener listener : tickerEventListeners){
            listener.tickerEvent();
        }
    }
}

