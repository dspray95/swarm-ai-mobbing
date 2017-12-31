package agent.swarm

import agent.Apid
import environment.Coordinate
import environment.Environment

class SwarmTest extends GroovyTestCase {

    Swarm swarm;

    void setUp() {
        swarm = new Swarm();
        for(int i = 0; i < 300; i++){
            swarm.add(new Apid(new Coordinate(20,20), new Environment()))
        }
        super.setUp()
    }

    void tearDown() {
        swarm = null
    }

    void testSetupMultithreading() {
        swarm.setupMultithreading()
        assertTrue(swarm.getSubswarms().size() == Runtime.getRuntime().availableProcessors() - 1)
    }
}
