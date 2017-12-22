package agent;

import agent.module.pathfinding.Pathfinder;
import environment.Coordinate;

public abstract class Agent {

    int hitpoints;
    int speed;
    double heatResistance;
    double aggression;
    Coordinate location;

    //Modules
    private Pathfinder pathfinder;

    public Agent(Coordinate location){
        this.location = location;
        this.pathfinder = new Pathfinder();
    }

    public Coordinate getLocation(){ return location;}
    public int getSpeed() { return speed; }
}
