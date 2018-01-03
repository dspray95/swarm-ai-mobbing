package agent.swarm;

import agent.Apid;
import agent.listener.ThreadDoneListener;
import config.SimulationDefaults;
import environment.Environment;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Swarm extends ArrayList<Apid> implements TickerEventListener, ThreadDoneListener, Serializable{


    private int swarmSize;
    transient private Environment environment;
    transient private boolean multithreading;
    transient private boolean multithreadingSetup;
    transient private ArrayList<SubSwarm> subswarms;
    //used to keep track of threads
    public int numThreads;
    public int numThreadsDone;

    public Swarm(Environment environment){
        this.environment = environment;
        swarmSize = SimulationDefaults.SWARM_SIZE;
    }

    public Swarm(Environment environment, int swarmSize){
        this.environment = environment;
        this.swarmSize = swarmSize;
    }

    public void setMultithreading(boolean multithreading){
        this.multithreading = multithreading;
    }

    public int getSwarmSize(){ return this.swarmSize; }

    public boolean setupMultithreading(){
        subswarms = new ArrayList<>();
        numThreads = Runtime.getRuntime().availableProcessors() - 1;
        int subswarmSize = this.size()/numThreads;
        for(int i = 1; i <= numThreads; i++){
            SubSwarm subswarm = new SubSwarm(this);
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
        if(!multithreading){
            for (Apid apid : this){
                apid.tickerEvent();
            }
        } else{
            if(multithreadingSetup){
                multithreadingSetup = setupMultithreading();
            }
            for(SubSwarm subSwarm : subswarms){
                subSwarm.run();
            }
            environment.setDoingMultiThreading(true);
        }
    }

    @Override
    public void threadDone(){
        numThreadsDone++;
        if(numThreadsDone == numThreads){
            environment.setDoingMultiThreading(false);
            numThreadsDone = 0;
        }
    }

    public ArrayList<SubSwarm> getSubswarms(){return this.subswarms;}

}
