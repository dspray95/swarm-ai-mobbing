package agent.module.state;

import environment.Coordinate;

public abstract class State {
    public abstract Coordinate getNextTarget();
}
