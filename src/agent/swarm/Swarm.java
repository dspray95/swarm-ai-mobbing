package agent.swarm;

import agent.Apid;
import config.SimulationDefaults;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm extends ArrayList<Apid> implements TickerEventListener, Serializable{

    private int swarmSize;
    transient private boolean multithreading;
    transient private boolean multithreadingSetup;
    transient private ArrayList<SubSwarm> subswarms;

    public Swarm(){
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(int swarmSize){
        this.swarmSize = swarmSize;
    }

    public void setMultithreading(boolean multithreading){
        this.multithreading = multithreading;
    }

    public int getSwarmSize(){ return this.swarmSize; }

    public boolean setupMultithreading(){
        subswarms = new ArrayList<>();
        int numProcessors = Runtime.getRuntime().availableProcessors() - 1;
        int subswarmSize = this.size()/numProcessors;
        for(int i = 1; i <= numProcessors; i++){
            SubSwarm subswarm = new SubSwarm();
            /**
             * example...
             * Numprocessors = 5
             * swarm size = 400
             * subswarm size = 80
             *
             * so groups are 0-80, 80-160, 160-240, 240-320, 320-400
             * for subgroup i = 1...
             * bottom bound = 1 = (i-1 * 80) + 1
             * upper bound = 80 = i * 80 (80 total indicies)
             *
             * for subgroup i = 2...
             * bottom bound = 81 = i-1 * 80 + 1
             * upper bound = 160 = i * 80
             *
             * for subground i = 3...
             * bottom bound = 161 = i-1 * 80 + 1
             */
            int lowerBound = ((i-1) * subswarmSize)+1;
            int upperBound = i*subswarmSize;
            for(int j = lowerBound; j<=upperBound; j++){
                subswarm.add(this.get(j-1));
            }
            subswarms.add(subswarm);
        }

        return true;
    }

    @Override
    public void tickerEvent() {
        if(multithreading){
            for (Apid apid : this){
                apid.tickerEvent();
            }
        } else{
            if(multithreadingSetup){
                multithreadingSetup = setupMultithreading();
            }
        }
    }

    public ArrayList<SubSwarm> getSubswarms(){return this.subswarms;}

}
