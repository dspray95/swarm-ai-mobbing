package agent.listener;

public interface ThreatEvent {
    /**
     * An observer pattern is used here to keep the Perceptor class open to use by both Apid and Vespid agents
     */
    void threatAlert();
}
