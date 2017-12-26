package agent;

import agent.module.state.State;
import agent.module.state.Worker;
import config.SimulationDefaults;
import environment.Coordinate;
import environment.Environment;

public class Apid extends Agent{

    public static final int STATE_WORKER = 0;
    public static final int STATE_GUARD = 1;
    public static final int STATE_MOB = 2;

    private State state;
    private Coordinate location;
    private int alertLevel;

    public Apid(Coordinate location, Environment environment){
        super(location, environment);
        this.hitpoints = SimulationDefaults.APID_HP;
        this.speed = SimulationDefaults.APID_SPEED;
        this.heatResistance = SimulationDefaults.APID_HEAT_THRESHOLD;
        this.aggression = SimulationDefaults. APID_AGGRESSION;

        this.state = new Worker(this);
        this.alertLevel = 0;
        this.location = location;
    }

    @Override
    public int judgeStateChange(){
        return STATE_WORKER;
    }
}