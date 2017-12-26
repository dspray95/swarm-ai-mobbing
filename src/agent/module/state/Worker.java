package agent.module.state;

import agent.Apid;
import environment.Coordinate;

import java.util.Random;

public class Worker extends State{

    private Apid parent;
    private int previousVector;

    public Worker(Apid parent){
        this.parent = parent;
        //Randomly initialises 'previous' vector
        this.previousVector = Coordinate.VECTOR_NEIGHBOURS.indexOf(randomVector());
    }

    /**
     * Randomly wander around the hive to simulate 'working' behaviour
     * @return The next coordinate to head towards
     */
    @Override
    public Coordinate getNextTarget(){
        Random r = new Random();
        Coordinate current = parent.getLocation();
        Coordinate nextVector;
        Coordinate nextTarget;

        int randomValue = r.nextInt(100);
        //80% chance of maintaining direction
        if(randomValue <= 80){
            nextVector = Coordinate.VECTOR_NEIGHBOURS.get(previousVector);
        }
        //20% chance of changing direction
        else {
            nextVector = randomVector();
            previousVector = Coordinate.VECTOR_NEIGHBOURS.indexOf(nextVector);
        }

        int targetX = current.X() + nextVector.X();
        int targetY = current.Y() + nextVector.Y();
        nextTarget = new Coordinate(targetX, targetY);
        return nextTarget;
    }

    /**
     * Generates a random vector direction
     * @return Coordinate random vector
     */
    private Coordinate randomVector(){
        Random r = new Random();
        int vectorIndexOf = r.nextInt(Coordinate.VECTOR_NEIGHBOURS.size() - 1);
        return Coordinate.VECTOR_NEIGHBOURS.get(vectorIndexOf);
    }
}
