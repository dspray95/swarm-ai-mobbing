package environment

class EnvironmentTest extends GroovyTestCase {

    private Environment environment;

    void setUp() {
        super.setUp()
        environment = new Environment();
    }

    void tearDown() {
        environment = null;
    }

    void testPopulate() {
    }

    void testGenerateFuzzyCoordinate() {
        Coordinate fuzzyCoordinate = environment.generateFuzzyCoordinate(new Coordinate(2500, 2500), 500);
        boolean xIsInBounds = fuzzyCoordinate.X() <= 2750 && fuzzyCoordinate.X() >= 2250;
        boolean yIsInBounds = fuzzyCoordinate.Y() <= 2750 && fuzzyCoordinate.Y() >= 2250;
        assertTrue(xIsInBounds && yIsInBounds);
    }
}
