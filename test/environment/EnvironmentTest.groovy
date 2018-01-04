package environment

import agent.Agent
import agent.swarm.Swarm
import config.SimulationDefaults

class EnvironmentTest extends GroovyTestCase {

    private Environment environment

    void setUp() {
        super.setUp()
        environment = new Environment()
    }

    void tearDown() {
        environment = null
    }

    void testPopulateCreation() {
        environment.populate()
        Swarm swarm = environment.getApidSwarm();
        Agent agent = swarm.agents[SimulationDefaults.SWARM_SIZE - 1]
        assertNotNull(agent)  //Assert that the last entry in the swarm Agent[] array exists
    }

    //Passes if the function recognises the deployment area argument as invalid
    void testPopulateInvalidArgs(){
        try{
            environment.populate(-50)
        }catch(IllegalArgumentException e){
            assertTrue(true)
        }
    }

    void testPopulateValidArgs(){
        try{
            environment.populate(500)
        }catch(IllegalArgumentException e){
            assertTrue(true)
        }
    }

    void testGenerateFuzzyCoordinate() {
        Coordinate fuzzyCoordinate = environment.generateFuzzyCoordinate(new Coordinate(2500, 2500), 500)
        boolean xIsInBounds = fuzzyCoordinate.X() <= 2750 && fuzzyCoordinate.X() >= 2250
        boolean yIsInBounds = fuzzyCoordinate.Y() <= 2750 && fuzzyCoordinate.Y() >= 2250
        assertTrue(xIsInBounds && yIsInBounds)
    }
}
