package agent.module

import agent.Agent
import agent.Apid
import environment.Coordinate

class PerceptorTest extends GroovyTestCase {

    Perceptor perceptor;

    void setUp() {
        super.setUp()
        Coordinate location = new Coordinate(500,500);
        Agent parent = new Apid(location);
        perceptor = new Perceptor(parent);
    }

    void tearDown() {
        perceptor = null;
    }

    void testPerceptionTick() {

    }
}
