package environment;

import java.util.HashMap;

public class Coordinate {

    private int y;
    private int x;

    public Coordinate(){}

    public Coordinate(int x, int y){
        this.setXY(x, y);
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
