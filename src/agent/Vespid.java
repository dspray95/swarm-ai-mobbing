package agent;

import simulation.config.SimulationDefaults;
import environment.Coordinate;
import environment.Environment;

import java.io.Serializable;

public class Vespid extends Agent implements Serializable{
    public Vespid(Coordinate location, Environment environment){
        super(location, environment);
        this.hitpoints = SimulationDefaults.VESPID_HP;
        this.speed = SimulationDefaults.VESPID_SPEED;
        this.heatResistance = SimulationDefaults.VESPID_HEAT_THRESHHOLD;
        this.aggression = SimulationDefaults.VESPID_AGGRESSION;
    }

    @Override
    public void judgeStateChange(){
        return;
    }
}