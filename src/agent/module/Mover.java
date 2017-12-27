package agent.module;

import agent.Agent;
import agent.Apid;
import agent.Vespid;
import environment.Coordinate;
import environment.Space;

public class Mover {

    Agent parent;
    Class parentType;

    public Mover (Agent parent){
        this.parent = parent;
        this.parentType = parent.getClass();
    }

    /**
     * Handles all operation related to the moving of an Agent from one coordinate to another
     * @param current
     * @param destination
     * @return
     */
    public boolean move(Coordinate current, Coordinate destination){
        currentSpaceCleanup(parent.getEnvironment().getEnvironmentMap()[current.X()][current.Y()]);
        targetSpaceSetup(parent.getEnvironment().getEnvironmentMap()[destination.X()][destination.Y()]);
        parent.setLocation(destination);
        return true;
    }

    /**
     * Performs cleanup on the Space that parent is moving away from
     * @param currentSpace
     */
    public void currentSpaceCleanup(Space currentSpace){
        //If theres nothing going on in the space we're leaving set it to null, otherwise just remove this object
        //from it's list
        if(currentSpace.getPheromoneStrength() == 0){
            currentSpace = null;
        } else{
            if(parentType == Apid.class){
                currentSpace.setApid(null);
            }
            else if(parentType == Vespid.class){
                currentSpace.setVespid(null);
            }
        }
    }

    /**
     * Performs setup on the Space that the parent is moving to
     * @param targetSpace
     */
    public void targetSpaceSetup(Space targetSpace){
        //make sure the space exists
        if(targetSpace == null){
            targetSpace = new Space();
        }
        //Set space contents
        if(parentType == Apid.class){
            targetSpace.setApid((Apid)parent);
        }
        else if(parentType == Vespid.class){
            targetSpace.setVespid((Vespid)parent);
        }

    }
}
