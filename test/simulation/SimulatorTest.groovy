package simulation

import environment.Environment
import org.junit.Test
import simulation.config.SimulationOptions

class SimulatorTest extends GroovyTestCase {

    Simulator simulator;
    Environment environment
    Logger logger

    void setUp() {
        SimulationOptions options = new SimulationOptions()
        options.setSwarmSize(100)
        logger = new Logger()
        simulator = new Simulator(logger, options)
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

    @Test
    void testRunSimulationForLargeTicks(){
        simulator.runSimulationForTicks(1000)
        ArrayList<Environment> loadedStates = logger.loadStates()
        assertTrue(loadedStates.size() == 1000)
    }
}
