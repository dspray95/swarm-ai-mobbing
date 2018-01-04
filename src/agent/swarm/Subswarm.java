package agent.swarm;

import agent.Apid;
import agent.listener.ThreadDoneListener;

import java.util.ArrayList;

public class Subswarm extends ArrayList<Apid> implements Runnable {

    private ThreadDoneListener listener;

    /**
     * A runnable version of swarm for use when multithreading
     * @param listener
     */
    public Subswarm(ThreadDoneListener listener){
        this.listener = listener;
    }

    @Override
    public void run() {
        for (Apid apid : this){
            apid.tickerEvent();
        }
        listener.threadDone();
    }
}
