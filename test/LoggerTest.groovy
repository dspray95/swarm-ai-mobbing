import environment.Coordinate
import environment.Environment

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
        assertTrue(logger.log(new Coordinate(10, 25)))
    }

    void testLoad(){
        logger.log(environment)
        Environment env = logger.load();
        assertTrue(env!= null);
    }
}
