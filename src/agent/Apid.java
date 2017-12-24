package agent;

import config.SimulationDefaults;
import environment.Coordinate;
import environment.Environment;

public class Apid extends Agent{

    public static final int ROLE_WORKER = 0;
    public static final int ROLE_GUARD = 1;
    public static final int ROLE_MOB = 2;

    private int role;
    private Coordinate location;

    public Apid(Coordinate location, Environment environment){
        super(location, environment);
        this.role = Apid.ROLE_WORKER;
        this.hitpoints = SimulationDefaults.APID_HP;
        this.speed = SimulationDefaults.APID_SPEED;
        this.heatResistance = SimulationDefaults.APID_HEAT_THRESHOLD;
        this.aggression = SimulationDefaults. APID_AGGRESSION;
        this.location = location;
    }
}