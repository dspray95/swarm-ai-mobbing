package agent.swarm;

import agent.Apid;
import agent.listener.ThreadDoneListener;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class Subswarm extends RecursiveAction {

    private ThreadDoneListener listener;
    private ArrayList<Apid> swarm;
    /**
     * A runnable version of swarm for use when multithreading
     * @param listener
     */
    public Subswarm(ThreadDoneListener listener){
        swarm = new ArrayList<>();
        this.listener = listener;
    }

    public void add(Apid apid){
        this.swarm.add(apid);
    }

    @Override
    protected void compute() {
        for (Apid apid : swarm){
            apid.tickerEvent();
        }
        listener.threadDone();
    }
}
