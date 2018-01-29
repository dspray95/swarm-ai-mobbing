package agent.swarm;

import agent.Apid;
import simulation.config.SimulationDefaults;
import environment.Environment;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm implements TickerEventListener, Serializable{


    private int swarmSize;
    transient private Environment environment;
    transient private ArrayList<Apid> swarm;

    public Swarm(Environment environment){
        this.environment = environment;
        this.swarm = new ArrayList<>();
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(Environment environment, int swarmSize){
        this.environment = environment;
        this.swarmSize = swarmSize;
    }

    @Override
    public void tickerEvent() {
        for (Apid apid : swarm){
                apid.tickerEvent();
        }
    }

    public int getSwarmSize(){ return this.swarmSize; }

    public ArrayList<Apid> getSwarm(){
        return this.swarm;
    }
    public void addApid(Apid apid){
        if(swarm == null){
            swarm = new ArrayList<>();
        }
        swarm.add(apid);
    }

    public void removeApid(Apid apid){
        swarm.remove(swarm.indexOf(apid));
    }
}
