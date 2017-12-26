package agent.module.state;

import agent.Apid;
import environment.Coordinate;

import java.util.Random;

public class Worker extends State{

    private Apid parent;
    private int previousVector;

    public Worker(Apid parent){
        this.parent = parent;
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
        //20% chance of changing direciton
        if(randomValue <= 80){
            nextVector = Coordinate.VECTOR_NEIGHBOURS.get(previousVector);
        }
        else {   //80% chance of maintaining direction
            int vectorIndexOf = r.nextInt(7);
            nextVector = Coordinate.VECTOR_NEIGHBOURS.get(vectorIndexOf);
            previousVector = vectorIndexOf;
        }
        int targetX = current.X() + nextVector.X();
        int targetY = current.Y() + nextVector.Y();
        nextTarget = new Coordinate(targetX, targetY);
        return nextTarget;
    }

}
