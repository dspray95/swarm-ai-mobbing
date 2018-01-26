package simulation.config;

public class SimulationDefaults {

    //Simulation
    public static boolean MULTITHREADING = false;
    public static int SIMULATION_LENGTH = 4000; //Number of ticks to perform

    //Swarm
    public static int SWARM_SIZE = 100;
    public static int JOIN_GUARD_THRESHOLD = SWARM_SIZE/10;
    //Agent
    public static int PERCEPTION_RADIUS = 100;
    //Apid
    public static int APID_HP = 100;
    public static int APID_SPEED = 1;
    public static double APID_HEAT_THRESHOLD = 49; //Celsius
    public static double APID_AGGRESSION = 1d;
    //Vespid
    public static int VESPID_HP = 500;
    public static int VESPID_SPEED = 1;
    public static double VESPID_HEAT_THRESHHOLD = 47; //Celsius
    public static double VESPID_AGGRESSION = 1d;
    //Environment
    public static int ENVIRONMENT_SIZE = SWARM_SIZE * 10;
    public static int SWARM_DEPLOYMENT_AREA = ENVIRONMENT_SIZE/2;
}
