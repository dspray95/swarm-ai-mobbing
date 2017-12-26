package agent.listener;

import java.util.ArrayList;
import java.util.List;

public interface ThreatEvent {

    List<Object> listeners = new ArrayList<>();

    public void threatAlert();

}
