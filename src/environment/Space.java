package environment;

import agent.Apid;
import agent.Vespid;

import java.io.Serializable;

public class Space implements Serializable{

    private Apid apid;
    private Vespid vespid;
    private int pheremoneStrength;

    public void setApid(Apid apid){ this.apid = apid; }
    public void setVespid(Vespid vespid){ this.vespid = vespid; }
    public void setPheromoneStrength(int pheremoneStrength){ this.pheremoneStrength = pheremoneStrength; } //TODO pheremone implementation

    public Apid getApid(){ return this.apid; }
    public Vespid getVespid(){ return this.vespid; }
    public int getPheromoneStrength() { return this.pheremoneStrength; }
}