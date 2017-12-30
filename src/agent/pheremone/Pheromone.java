package agent.pheremone;

import environment.Coordinate;
import simulation.listener.TickerEventListener;

public class Pheromone implements TickerEventListener {

    private Coordinate location;
    transient private int strength;

    public Pheromone(Coordinate location){
        this.location = location;
    }

    public void increaseStrength(){
        strength = strength + 10;
    }

    public Coordinate getLocation(){
        return this.location;
    }

    @Override
    public void tickerEvent() {
        strength--;
    }
}
