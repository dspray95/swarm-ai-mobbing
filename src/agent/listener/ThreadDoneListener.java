package agent.listener;

public interface ThreadDoneListener {
    /**
     * An observer pattern is used here to keep the Perceptor class open to use by both Apid and Vespid agents
     */
    void threadDone();
}
