package agent.module

import agent.Agent
import agent.Apid
import environment.Coordinate
import environment.Environment

class PerceptorTest extends GroovyTestCase {

    Perceptor perceptor;
    Environment environment;
    Agent parentAgent;
    Apid nearbyAgent;
    Apid outsideRangeAgent;

    int parentXY = 200;
    int nearbyXY = 210;
    int farXY = 700;

    void setUp() {
        super.setUp()
        environment = new Environment();
        //Create agents
        Agent parentAgent = new Apid(new Coordinate(parentXY, parentXY), environment);
        nearbyAgent = new Apid(new Coordinate(nearbyXY, nearbyXY), environment);
        outsideRangeAgent = new Apid(new Coordinate(farXY, farXY), environment);
        //Add agents to environment
        environment.addApid(parentAgent, 0, new Coordinate(parentXY, parentXY));
        environment.addApid(nearbyAgent, 1, new Coordinate(nearbyXY, nearbyXY));
        environment.addApid(outsideRangeAgent, 2, new Coordinate(farXY, farXY));
        //Manually initialise perceptor
        perceptor = new Perceptor(parentAgent);
    }

    void tearDown() {
    }

    void testOneApidPercieve() {
        perceptor.PerceptionTick();
        assertTrue(perceptor.getApidae().size() == 1);
    }
}
