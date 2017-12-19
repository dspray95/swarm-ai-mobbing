package agent.module.pathfinding;

import environment.Coordinate;

import java.util.ArrayList;

public class Pathfinder {

    private Coordinate start;
    private Coordinate end;

    public Pathfinder(){}

    /**
     * Get the next destination coordinate en-route to target
     * @param start current location coordinate
     * @param target destination coordinate
     * @return next coordinate
     */
    public Coordinate nextStep(Coordinate start, Coordinate target){
        ArrayList<Coordinate> neighbours = start.getNeighbours();
        Coordinate bestCoordinate = new Coordinate();
        Double bestDistance = 0d;
        boolean testingInitialised = false;
        for(Coordinate coordinate : neighbours){
            if(testingInitialised){
                if(EuclideanDistance(coordinate, target) < bestDistance){
                    bestDistance = EuclideanDistance(coordinate, target);
                    bestCoordinate = coordinate;
                }
            } else{
              bestDistance = EuclideanDistance(coordinate, target);
              bestCoordinate = coordinate;
            }
        }
        return bestCoordinate;
    }

    /**
     * Euclidean distance between two points a and b is defined as:
     *   √(Xa - Xb)^2 + (Ya - Yb)^2
     * @param a
     * @param b
     * @return Euclidean distance value
     */
    public double EuclideanDistance(Coordinate a, Coordinate b){
        return(Math.sqrt(Math.pow(a.X()-b.X(), 2)) + Math.pow(a.Y()-b.Y(), 2));
    }

}
