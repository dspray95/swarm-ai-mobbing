import environment.Environment
import simulation.Logger

class LoggerTest extends GroovyTestCase {

    Logger logger
    Environment environment

    void setUp() {
        logger = new Logger()
        environment = new Environment()
        environment.populate()
        super.setUp()
    }

    void tearDown() {
        logger = null
        environment = null
    }

    void testLog() {
        logger.addStoredState(environment);
        logger.log()
        assertTrue(new File(logger.getFilepath() + logger.getFilename()).exists())
    }

    void testLoad(){
        for(int i = 0; i <= 20; i++){
            environment.tickerEvent()
            logger.addStoredState(environment)
        }
        logger.log()
        ArrayList<Environment> loadedStates = logger.loadStates()
        assertTrue(loadedStates.size() > 0)
    }
}
