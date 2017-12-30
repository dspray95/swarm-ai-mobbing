package simulation

import environment.Environment

class SimulatorTest extends GroovyTestCase {

    Simulator simulator;
    Environment environment
    Logger logger

    void setUp() {
        environment = new Environment()
        environment.populate()
        logger = new Logger()
        simulator = new Simulator(logger)
        super.setUp()
    }

    void tearDown() {
        simulator = null
        environment = null
        logger = null
    }

    void testRunSimulationForTicks() {
        simulator.runSimulationForTicks(20)
        ArrayList<Environment> loadedStates = logger.loadStates()
        assertTrue(loadedStates.size() == 20) //Assert that all of our saved states can be loaded
    }

    void testRunSimulationForLargeTicks(){
        simulator.runSimulationForTicks(10000)
        ArrayList<Environment> loadedStates = logger.loadStates()
        assertTrue(loadedStates.size() == 10000)
    }
}
