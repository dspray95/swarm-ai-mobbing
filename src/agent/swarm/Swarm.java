package agent.swarm;

import agent.Apid;
import agent.listener.ThreadDoneListener;
import environment.Environment;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Swarm extends RecursiveAction implements TickerEventListener, ThreadDoneListener, Serializable{


    private int swarmSize;
    private ArrayList<Apid> swarm;
    transient private Environment environment;
    transient private boolean multithreading;
    transient private boolean multithreadingSetup;
    transient private ArrayList<Subswarm> subswarms;
    //used to keep track of threads
    private int numThreads;
    private int numThreadsDone;
    ArrayList<Thread> threads;

    public Swarm(Environment environment, int swarmSize, boolean multithreading){
        this.swarm = new ArrayList<>();
        this.environment = environment;
        this.swarmSize = swarmSize;
        this.multithreading = multithreading;
    }

    public void setMultithreading(boolean multithreading){
        this.multithreading = multithreading;
    }

    public int getSwarmSize(){ return this.swarmSize; }

    public void add(Apid apid){
        this.swarm.add(apid);
    }

    public boolean setupMultithreading(){
        subswarms = new ArrayList<>();
        threads = new ArrayList<>();
//        numThreads = Runtime.getRuntime().availableProcessors() -2;
        numThreads = 4;
        int subswarmSize = swarm.size()/numThreads;
        for(int i = 1; i <= numThreads; i++){
            Subswarm subswarm = new Subswarm(this);
            int lowerBound = ((i-1) * subswarmSize)+1;
            int upperBound = i*subswarmSize;
            for(int j = lowerBound; j<=upperBound; j++){
                subswarm.add(swarm.get(j-1));
            }
            subswarms.add(subswarm);
        }

        return true;
    }

    @Override
    public void tickerEvent() {
        if(!multithreading) {
            for (Apid apid : swarm) {
                apid.tickerEvent();
            }
        }else{
            if(!multithreadingSetup){
                setupMultithreading();
            }
        }
    }

    @Override
    public void threadDone(){
        numThreadsDone++;
        if(numThreadsDone == numThreads){
            environment.setThreadsRunning(false);
            numThreadsDone = 0;
        }
    }

    public ArrayList<Apid> getSwarm(){
        return this.swarm;
    }
    public ArrayList<Subswarm> getSubswarms(){
        return this.subswarms;
    }

    @Override
    public void compute() {
        if(!multithreadingSetup){
            setupMultithreading();
        }
        ForkJoinTask.invokeAll(subswarms);

        tickerEvent();
    }
}
