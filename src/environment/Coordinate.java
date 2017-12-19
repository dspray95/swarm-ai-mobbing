package environment;

import java.util.*;

public class Coordinate {

    public static final Collection<Coordinate> VECTOR_NEIGHBOURS = Arrays.asList(
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
    public List<Coordinate> getNeighbours(){
        List<Coordinate> neighbours = new ArrayList<Coordinate>();
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
    public HashMap<String, Integer> getCoordinates(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("X", this.x);
        hashMap.put("Y", this.y);
        return hashMap;
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y){ this.y = y;}
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
