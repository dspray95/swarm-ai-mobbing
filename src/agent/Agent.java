package agent;

import agent.module.Perceptor;
import agent.module.Pathfinder;
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
    Pathfinder pathfinder;
    Perceptor perceptor;

    public Agent(Coordinate location, Environment environment){
        this.location = location;
        this.environment = environment;
        this.pathfinder = new Pathfinder();
        this.perceptor = new Perceptor(this);
    }

    public abstract int judgeStateChange();

    public void setLocation(Coordinate location){
        this.location = location;
    }

    public Environment getEnvironment(){return this.environment;}
    public Coordinate getLocation(){ return location;}
    public int getSpeed() { return speed; }
}
