package environment;

import agent.Apid;
import agent.Vespid;
import agent.pheremone.Pheromone;
import agent.swarm.Swarm;
import simulation.config.SimulationDefaults;
import simulation.config.SimulationOptions;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Environment implements Serializable, TickerEventListener {

    transient private int environmentSize;

    transient private SimulationOptions options;
    private Swarm apidSwarm;
    private ArrayList<Vespid> vespidae;
    private ArrayList<Pheromone> pheromones;

    transient private ArrayList<TickerEventListener> tickerEventListeners;

    public Environment(SimulationOptions options) throws IllegalArgumentException{
        this.environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
        this.options = options;
        this.vespidae = new ArrayList<>();
        this.pheromones = new ArrayList<>();
        this.tickerEventListeners = new ArrayList<>();
        populate();
    }

    /**
     * Creates agents on the simulation environment and maps them to an initial position
     */
    public void populate() throws IllegalArgumentException{
        long startTime = System.nanoTime();
        int deploymentArea = SimulationDefaults.SWARM_DEPLOYMENT_AREA;
        //Setup swarm
        apidSwarm = new Swarm(this);
        registerTickerListener(apidSwarm);

        //Get the center of  the environment
        Coordinate environmentCenter = new Coordinate(
                environmentSize/2,
                environmentSize/2
        );
        //Create apidae and add them to swarm
        for(int i = 0; i < apidSwarm.getSwarmSize(); i++){
            try {
                Coordinate location = generateFuzzyCoordinate(environmentCenter, deploymentArea);
                apidSwarm.addApid(new Apid(location, this));
            }catch(IllegalArgumentException e){
                System.out.print(e);
                return; //TODO error recovery for out of bounds
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Populated in: " + ((endTime - startTime)/10000) + "ms"); //TODO log file
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

    @Override
    public void tickerEvent() {
            for(TickerEventListener listener : tickerEventListeners){
                listener.tickerEvent();
        }
    }

    public Swarm getApidSwarm() {
        return apidSwarm;
    }

    public void setApidSwarm(Swarm apidSwarm) {
        this.apidSwarm = apidSwarm;
    }

    public ArrayList<Vespid> getVespidae() {
        return vespidae;
    }

    public void setVespidae(ArrayList<Vespid> vespidae) {
        this.vespidae = vespidae;
    }

    public ArrayList<Pheromone> getPheromones() {
        return pheromones;
    }

    public void setPheromones(ArrayList<Pheromone> pheromones) {
        this.pheromones = pheromones;
    }

}

