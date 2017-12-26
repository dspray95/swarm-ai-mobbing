package agent.module.state;

import agent.Apid;
import environment.Coordinate;

public class Worker extends State{

    public Worker(Apid parent){
        super(parent);
    }

    @Override
    public Coordinate getNextTarget(){
        return randomWalk();
    }
}
