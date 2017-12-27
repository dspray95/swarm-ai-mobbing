package simulation;

import java.io.Serializable;

public interface TickerEventListener extends Serializable{
    void tickerEvent();
}
