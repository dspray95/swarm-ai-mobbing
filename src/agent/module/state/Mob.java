package agent.module.state;

import agent.Agent;
import environment.Coordinate;

public class Mob extends State {

    public Mob(Agent parent) {
        super(parent);
    }

    @Override
    public Coordinate getNextTarget() {
        return null;
    }
}
