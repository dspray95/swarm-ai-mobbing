package agent;

import agent.module.pathfinding.Pathfinder;

public abstract class Agent {

    int hitpoints;
    int speed;
    double heatResistance;
    double aggression;

    //Modules
    private Pathfinder pathfinder;

    public Agent(){
        pathfinder = new Pathfinder();
    }

    public int getSpeed() {
        return speed;
    }
}
