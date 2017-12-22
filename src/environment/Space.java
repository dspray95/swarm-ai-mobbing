package environment;

import agent.Apid;
import agent.Vespid;

public class Space {

    private Apid apid;
    private Vespid vespid;
    private int pheremoneStrength;

    public void setApid(Apid apid){ this.apid = apid; }
    public void setVespid(Vespid vespid){ this.vespid = vespid; }
    public void setPheremoneStrength(int pheremoneStrength){ this.pheremoneStrength = pheremoneStrength; } //TODO pheremone implementation

    public Apid getApid(){ return this.apid; }
    public Vespid getVespid(){ return this.vespid; }
    public int getPheremoneStrength() { return this.pheremoneStrength; }
}
