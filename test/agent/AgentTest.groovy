package agent

import environment.Coordinate
import environment.Environment

class AgentTest extends GroovyTestCase {

    Apid agent;
    Environment environment;

    void setUp() {
        environment = new Environment()
        environment.populate()
        agent = new Apid(new Coordinate(10,10), environment)
        environment.addApid((Apid) agent)
        super.setUp()
    }

    void tearDown() {
        environment = null
        agent = null
    }

    void testTickerEvent() {
        Coordinate firstLocation = agent.getLocation()
        for(int i = 0; i < 20; i++){
            agent.tickerEvent()
        }
        Coordinate newLocation = agent.getLocation();
        assertTrue(firstLocation != newLocation);
    }
}
