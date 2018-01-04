package agent.swarm;

import agent.Apid;
import agent.listener.ThreadDoneListener;
import environment.Environment;
import simulation.listener.TickerEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Swarm extends ArrayList<Apid> implements TickerEventListener, ThreadDoneListener, Serializable{


    private int swarmSize;
    transient private Environment environment;
    transient private boolean multithreading;
    transient private boolean multithreadingSetup;
    transient private ArrayList<Subswarm> subswarms;
    //used to keep track of threads
    private int numThreads;
    private int numThreadsDone;
    ArrayList<Thread> threads;
    //Fork/Join
    private ForkJoinPool commonPool;

    public Swarm(Environment environment, int swarmSize, boolean multithreading){
        commonPool = ForkJoinPool.commonPool();
        this.environment = environment;
        this.swarmSize = swarmSize;
        this.multithreading = multithreading;
    }

    public void setMultithreading(boolean multithreading){
        this.multithreading = multithreading;
    }

    public int getSwarmSize(){ return this.swarmSize; }

    public boolean setupMultithreading(){
        subswarms = new ArrayList<>();
        threads = new ArrayList<>();
//        numThreads = Runtime.getRuntime().availableProcessors() -2;
        numThreads = 4;
        int subswarmSize = this.size()/numThreads;
        for(int i = 1; i <= numThreads; i++){
            Subswarm subswarm = new Subswarm(this);
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
            Thread thread = new Thread(subswarm);
            threads.add(thread);
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
            if(!multithreadingSetup){
                multithreadingSetup = setupMultithreading();
            }
            environment.setThreadsRunning(true);
            for(Thread thread : threads){
                thread.start();
            }
            try {
                System.out.println("Waiting for threads to finish.");
                threads.get(numThreads-1).join();
            } catch (InterruptedException e) {
                System.out.println("Main thread Interrupted");
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

    public ArrayList<Subswarm> getSubswarms(){return this.subswarms;}

}
