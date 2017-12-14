package agent;

import config.Defines;
import environment.Coordinate;

public class Apid extends Agent{

    int role;
    Coordinate location;
    //prey module
    public Apid(Coordinate location){
        this.role = Defines.ROLE_WORKER;
        this.location = location;
    }
}
