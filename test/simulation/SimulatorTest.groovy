package simulation

import environment.Environment
import org.junit.Test

class SimulatorTest extends GroovyTestCase {

    Simulator simulator;
    Environment environment
    Logger logger

    void setUp() {
        logger = new Logger()
        simulator = new Simulator(logger, 100)
        super.setUp()
    }

    void tearDown() {
        simulator = null
        environment = null
        logger = null
    }

    @Test
    void testRunSimulationForTicks() {
        simulator.runSimulationForTicks(20)
        ArrayList<Environment> loadedStates = logger.loadStates()
        assertTrue(loadedStates.size() == 20) //Assert that all of our saved states can be loaded
    }

    @Test
    void testRunSimulationForMediumTicks(){
        simulator.runSimulationForTicks(100)
        assertTrue(logger.loadStates().size() == 100)
    }

//    @Test @Ignore
//    void testRunSimulationForLargeTicks(){
//        simulator.runSimulationForTicks(10000)
//        ArrayList<Environment> loadedStates = logger.loadStates()
//        assertTrue(loadedStates.size() == 10000)
//    }
}
