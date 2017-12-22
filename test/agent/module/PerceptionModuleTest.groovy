package agent.module

import agent.Agent
import agent.Apid
import environment.Coordinate

class PerceptionModuleTest extends GroovyTestCase {

    PerceptionModule perceptor;

    void setUp() {
        super.setUp()
        Coordinate location = new Coordinate(500,500);
        Agent parent = new Apid(location);
        perceptor = new PerceptionModule(parent);
    }

    void tearDown() {
        perceptor = null;
    }

    void testPerceptionTick() {
        assertTrue(null != perceptor.perceptionTick());
    }
}
