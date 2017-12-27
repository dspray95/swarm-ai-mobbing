package agent.module;

import environment.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;

public class Pathfinder implements Serializable {

    private Coordinate start;
    private Coordinate end;

    public Pathfinder(){}

    /**
     * Get the next destination coordinate en-route to target
     * @param start current location coordinate
     * @param target destination coordinate
     * @return next coordinate
     *
     * TODO: Space occupied testing
     */
    public Coordinate nextStep(Coordinate start, Coordinate target){
        ArrayList<Coordinate> neighbours = start.getNeighbours();
        Coordinate bestCoordinate = new Coordinate();
        Double bestDistance = 0d;
        boolean testingInitialised = false;
        for(Coordinate coordinate : neighbours){
            double eucDistance = coordinate.euclideanDistance(target);
            if(testingInitialised){
                if(eucDistance < bestDistance){
                    bestDistance = eucDistance;
                    bestCoordinate = coordinate;
                }
            } else{
              bestDistance = eucDistance;
              bestCoordinate = coordinate;
              testingInitialised = true;
            }
        }
        return bestCoordinate;
    }
}
