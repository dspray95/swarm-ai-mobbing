package agent;

import agent.module.MovementModule;
import environment.Coordinate;

public class Apid extends Agent{

    public static final int ROLE_WORKER = 0;
    public static final int ROLE_GUARD = 1;
    public static final int ROLE_MOB = 2;

    int role;
    Coordinate location;
    //modules
    MovementModule mMovement;

    public Apid(Coordinate location){
        this.role = Apid.ROLE_WORKER;
        this.location = location;
        this.mMovement = new MovementModule();
    }
}
