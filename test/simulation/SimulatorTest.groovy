package simulation

import environment.Environment

class SimulatorTest extends GroovyTestCase {

    Environment environment
    Logger logger

    void setUp() {
        environment = new Environment()
        environment.populate()
        logger = new Logger()
        super.setUp()
    }

    void tearDown() {
        environment = null
        logger = null
    }

    void testRunSimulationForTicks() {
        for(int i = 0; i <= 20; i++){
            environment.tickerEvent()
            logger.log(environment)
        }
        assertTrue(new File(logger.getFilepath() + logger.getFilename()).exists())
    }
}
