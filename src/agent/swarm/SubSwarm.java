package agent.swarm;

import agent.Apid;

import java.util.ArrayList;

public class SubSwarm implements Runnable {

    ArrayList<Apid> swarm;

    public SubSwarm(ArrayList<Apid> members){
        this.swarm = members;
    }

    @Override
    public void run() {

    }
}
