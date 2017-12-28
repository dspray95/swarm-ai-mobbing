package agent.pheremone;

import environment.Coordinate;
import simulation.listener.TickerEventListener;

public class Pheromone implements TickerEventListener {

    Coordinate location;
    int strength;

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
