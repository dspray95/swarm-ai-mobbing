package agent;

import config.Defines;

public class Apid extends Agent{
    int role;
    //prey module
    public Apid(){
        this.role = Defines.ROLE_WORKER;
    }
}
