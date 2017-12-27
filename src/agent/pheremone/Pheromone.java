package agent.pheremone;

import environment.Coordinate;

public class Pheromone {

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
}
