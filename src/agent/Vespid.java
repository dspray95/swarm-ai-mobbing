package agent;

import config.SimulationDefaults;
import environment.Coordinate;
import environment.Environment;

public class Vespid extends Agent{
    public Vespid(Coordinate location, Environment environment){
        super(location, environment);
        this.hitpoints = SimulationDefaults.VESPID_HP;
        this.speed = SimulationDefaults.VESPID_SPEED;
        this.heatResistance = SimulationDefaults.VESPID_HEAT_THRESHHOLD;
        this.aggression = SimulationDefaults.VESPID_AGGRESSION;
    }
}