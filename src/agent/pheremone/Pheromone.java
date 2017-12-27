package agent.pheremone;

import environment.Coordinate;
import simulation.TickerEventListener;

public class Pheromone implements TickerEventListener {

    Coordinate location;
    int strength;

    public Pheromone(Coordinate location){
        this.location = location;
    }

    public void increaseStrength(){
        this.strength++;
    }

    public Coordinate getLocation(){
        return this.location;
    }

    @Override
    public void tickerEvent() {

    }
}
