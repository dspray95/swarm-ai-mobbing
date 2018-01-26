package agent.module.state;

import agent.Agent;
import environment.Coordinate;

import java.io.Serializable;

public class Guard extends State implements Serializable {

    public Guard(Agent parent) {
        super(parent);
    }

    @Override
    public Coordinate getNextTarget() {
        return randomWalk();
    }
}
