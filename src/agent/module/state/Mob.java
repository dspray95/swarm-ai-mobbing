package agent.module.state;

import agent.Agent;
import environment.Coordinate;

import java.io.Serializable;

public class Mob extends State implements Serializable{

    public Mob(Agent parent) {
        super(parent);
    }

    @Override
    public Coordinate getNextTarget() {
        return null;
    }
}
