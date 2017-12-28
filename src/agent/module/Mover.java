package agent.module;

import agent.Agent;
import environment.Coordinate;

import java.io.Serializable;

public class Mover implements Serializable{

    Agent parent;
    Class parentType;

    public Mover (Agent parent){
        this.parent = parent;
        this.parentType = parent.getClass();
    }

    /**
     * Handles all operation related to the moving of an Agent from one coordinate to another
     * @param destination
     * @return
     */
    public boolean move(Coordinate destination){
        parent.setLocation(destination);
        return true;
    }
}
