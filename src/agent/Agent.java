package agent;

import agent.module.Mover;
import agent.module.Pathfinder;
import agent.module.Perceptor;
import agent.module.state.State;
import environment.Coordinate;
import environment.Environment;
import simulation.TickerEventListener;

import java.io.Serializable;

public abstract class Agent implements Serializable, TickerEventListener {

    int hitpoints;
    int speed;
    double heatResistance;
    double aggression;
    //Environment data
    Coordinate location;
    Environment environment;
    //Modules
    Pathfinder pathfinder;
    Perceptor perceptor;
    Mover mover;
    State state;
    public Agent(Coordinate location, Environment environment){
        this.location = location;
        this.environment = environment;
        this.pathfinder = new Pathfinder();
        this.perceptor = new Perceptor(this);
        this.mover = new Mover(this);
    }

    /**
     * Decide whether or not to change current state to another
     * @return
     */
    public abstract int judgeStateChange();

    public void setLocation(Coordinate location){
        this.location = location;
    }

    @Override
    public void tickerEvent(){
        perceptor.perceptionTick();
        judgeStateChange();
        mover.move(pathfinder.nextStep(location, state.getNextTarget()));
    }

    public Environment getEnvironment(){return this.environment;}
    public Coordinate getLocation(){ return location;}
    public int getSpeed() { return speed; }
}
