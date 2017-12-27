package agent.module.state;

import agent.Apid;
import environment.Coordinate;

import java.io.Serializable;

public class Worker extends State implements Serializable{

    public Worker(Apid parent){
        super(parent);
    }

    @Override
    public Coordinate getNextTarget(){
        return randomWalk();
    }
}
