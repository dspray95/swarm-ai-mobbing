package agent.module.state;

import agent.Agent;
import environment.Coordinate;

import java.io.Serializable;
import java.util.Random;

public abstract class State implements Serializable{

    int previousVector;
    Agent parent;

    public State(Agent parent){
        this.parent = parent;
        this.previousVector = Coordinate.VECTOR_NEIGHBOURS.indexOf(randomVector());
    }


    /**
     * Randomly wander around the environment
     * @return The next coordinate to head towards
     */
    public Coordinate randomWalk(){
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
     * Generates a random vector direction using the coordinate class' VECTOR_NEIGHBOURS constant
     * @return Coordinate random vector
     */
    private Coordinate randomVector(){
        Random r = new Random();
        int vectorIndexOf = r.nextInt(Coordinate.VECTOR_NEIGHBOURS.size() - 1);
        return Coordinate.VECTOR_NEIGHBOURS.get(vectorIndexOf);
    }

    public abstract Coordinate getNextTarget();
}
