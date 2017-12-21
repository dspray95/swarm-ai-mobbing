package agent;

import config.SimulationDefaults;

public class Vespid extends Agent{
    public Vespid() {
        this.hitpoints = SimulationDefaults.VESPID_HP;
        this.speed = SimulationDefaults.VESPID_SPEED;
        this.heatResistance = SimulationDefaults.VESPID_HEAT_THRESHHOLD;
        this.aggression = SimulationDefaults.VESPID_AGGRESSION;
    }
}