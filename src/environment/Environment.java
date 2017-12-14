package environment;

import agent.Apid;
import agent.swarm.Swarm;
import config.SimulationDefaults;

import java.util.Random;

public class Environment {

    private Space[][] environmentMap;
    private Swarm apisSwarm;

    public Environment(){
        int environmentSize = SimulationDefaults.ENVIRONMENT_SIZE;
        this.environmentMap = new Space[environmentSize][environmentSize];
        apisSwarm = new Swarm();
    }

    public void Populate(){
        for(int i = 0; i<= apisSwarm.getSwarmSize(); i++){
            Apid apid = new Apid();
        }
    }

    /**
     * Generates a random coordinate within a square with the edge length of boundXY from the center coordinate
     * @param center to be the center of the bounding square
     * @param boundXY size of the bounding square
     * @return a random coordinate inside the bounding square
     */
    public Coordinate generateFuzzyCoordinate(Coordinate center, int boundXY){
        Random r = new Random();
        int lowerBoundX = center.X() - boundXY/2;
        int lowerBoundY = center.Y() - boundXY/2;
        int fuzzyX = r.nextInt(lowerBoundX + boundXY);
        int fuzzyY = r.nextInt(lowerBoundY + boundXY);

        return new Coordinate(fuzzyX, fuzzyY);
    }

}
