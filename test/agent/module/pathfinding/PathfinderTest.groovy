package agent.module.pathfinding

import environment.Coordinate

class PathfinderTest extends GroovyTestCase {

    Pathfinder pathfinder;
    Coordinate start;
    Coordinate destination;

    void setUp() {
        pathfinder = new Pathfinder();
        start = new Coordinate(0, 0);
        destination = new Coordinate(10,10);
        super.setUp()
    }

    void tearDown() {
        pathfinder = null;
        start = null;
        destination = null;
    }

    void testNextStep() {
        Coordinate step = pathfinder.nextStep(start, destination);
        assertTrue(step.X() == 1 && step.Y() == 1);
    }

    void testEuclideanDistance() {
        double distance = pathfinder.EuclideanDistance(start, destination);
        assertTrue(distance >= 14.14 && distance <= 14.15) //Test only to significant figures
    }
}
