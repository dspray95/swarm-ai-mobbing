package agent;

import agent.module.pathfinding.Pathfinder;
import environment.Coordinate;
import environment.Environment;

public abstract class Agent {

    int hitpoints;
    int speed;
    double heatResistance;
    double aggression;

    Coordinate location;
    Environment environment;
    //Modules
    private Pathfinder pathfinder;

    public Agent(Coordinate location, Environment environment){
        this.location = location;
        this.pathfinder = new Pathfinder();
        this.environment = environment;
    }

    public Environment getEnvironment(){return this.environment;}
    public Coordinate getLocation(){ return location;}
    public int getSpeed() { return speed; }
}
