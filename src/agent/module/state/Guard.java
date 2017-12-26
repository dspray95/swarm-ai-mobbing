package agent.module.state;

import agent.Agent;
import environment.Coordinate;

public class Guard extends State {

    public Guard(Agent parent) {
        super(parent);
    }

    @Override
    public Coordinate getNextTarget() {
        return null;
    }
}
