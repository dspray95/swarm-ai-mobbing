package environment;

import java.util.*;

public class Coordinate {

    public static final List<Coordinate> VECTOR_NEIGHBOURS = Arrays.asList(
            new Coordinate(  0, -1 ),   // top
            new Coordinate( +1, -1 ),   // right-top
            new Coordinate( +1,  0 ),   // right-middle
            new Coordinate(  0, +1 ),   // bottom
            new Coordinate( -1, +1 ),   // left-bottom
            new Coordinate( -1,  0 ),   // left-middle
            new Coordinate( +1, +1 ),   // right-bottom
            new Coordinate( -1, -1 )    // left-top
    );

    private int y;
    private int x;

    public Coordinate(){}

    public Coordinate(int x, int y){
        this.setXY(x, y);
    }

    /**
     * Creates a list of all adjacent nodes to this coordinate in 8 directions
     * @return adjacent nodes
     */
    public ArrayList<Coordinate> getNeighbours(){
        ArrayList<Coordinate> neighbours = new ArrayList<Coordinate>();
        for(Coordinate vector : Coordinate.VECTOR_NEIGHBOURS){
            neighbours.add(vectorMove(vector));
        }
        return neighbours;
    }

    /**
     * Move this coordinate by a given vector
     * @param vector Coordinate by which to move
     * @return new coordinate modified by vector
     */
    private Coordinate vectorMove(Coordinate vector){
        return new Coordinate(x + vector.X(), y + vector.Y());
    }

    public int Y(){
        return this.y;
    }
    public int X(){
        return this.x;
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y){ this.y = y;}
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the euclidean distance between this coordinate and a target coordinate
     * Euclidean distance between two points a and b is defined as:
     *   √(Xa - Xb)^2 + (Ya - Yb)^2
     *
     * @param target Coordinate object to measure distance to
     * @return double value of distance between this coordinate and target coordinate
     */
    public double euclideanDistance(Coordinate target){
        double x = Math.pow(X() - target.X(), 2);
        double y = Math.pow(Y() - target.Y(), 2);
        return Math.sqrt(x + y);
    }
}
