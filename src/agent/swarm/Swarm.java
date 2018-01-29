package agent.swarm;

import agent.Apid;
import environment.Environment;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm implements TickerEventListener, Serializable{

    transient private Environment environment;
    transient private ArrayList<Apid> swarm;

    public Swarm(Environment environment){
        this.environment = environment;
        this.swarm = new ArrayList<>();

    }

    @Override
    public void tickerEvent() {
        for (Apid apid : swarm){
                apid.tickerEvent();
        }
    }

    public int getSwarmSize(){ return this.swarm.size(); }

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
