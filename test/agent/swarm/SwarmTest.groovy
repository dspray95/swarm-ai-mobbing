package agent.swarm

import agent.Apid
import environment.Coordinate
import environment.Environment

class SwarmTest extends GroovyTestCase {

    Swarm swarm;
    Environment environment;

    void setUp() {
        environment = new Environment()
        swarm = new Swarm(environment)
        for(int i = 0; i < 300; i++){
            swarm.add(new Apid(new Coordinate(20,20), new Environment()))
        }
        super.setUp()
    }

    void tearDown() {
        swarm = null
        environment = null
    }

    void testSetupMultithreading() {
        swarm.setupMultithreading()
        assertTrue(swarm.getSubswarms().size() == Runtime.getRuntime().availableProcessors() - 1)
    }

    void testRunMultithreadedTick(){
        swarm.setMultithreading(true)
        swarm.setupMultithreading()
        swarm.tickerEvent()
        boolean multithreadingStarted = environment.getThreadsRunning()

        assertTrue(multithreadingStarted)
    }
}
