package agent.module;

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
            double eucDistance = EuclideanDistance(coordinate, target);
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

    /**
     * Euclidean distance between two points a and b is defined as:
     *   √(Xa - Xb)^2 + (Ya - Yb)^2
     * @param a
     * @param b
     * @return Euclidean distance value
     */
    public double EuclideanDistance(Coordinate a, Coordinate b) {
        double x = Math.pow(a.X() - b.X(), 2);
        double y = Math.pow(a.Y() - b.Y(), 2);
        return Math.sqrt(x + y);
    }
}
