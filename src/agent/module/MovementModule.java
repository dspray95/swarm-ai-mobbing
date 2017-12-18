package agent.module;

import agent.Agent;
import environment.Coordinate;

public class MovementModule {

    public static final int UP = 0;
    public static final int UP_LEFT = 1;
    public static final int LEFT = 2;
    public static final int DOWN_LEFT = 3;
    public static final int DOWN = 4;
    public static final int DOWN_RIGHT = 5;
    public static final int RIGHT = 6;
    public static final int UP_RIGHT = 7;

    private Agent parent;
    private double speed;
    private boolean moving = false;
    private int currentDirection;
    private Coordinate targetCoordinate;
    private Coordinate currentCoordinate;

    /**
     * Handles agent movement, either along a path or in straight line towards a specified target.
     * Uses A* pathfinding
     * @param parent Parent agent
     * @param initialPosition Start position of parent Agent
     */
    public MovementModule(Agent parent, Coordinate initialPosition){
        this.currentCoordinate = initialPosition;
        this.parent = parent;
        this.speed = parent.getSpeed();
    }

    /**
     * Optional custom speed definition
     * @param parent Parent agent
     * @param initialPosition Start position of parent Agent
     * @param speed custom speed
     */
    public MovementModule(Agent parent, Coordinate initialPosition, int speed){
        this.currentCoordinate = initialPosition;
        this.parent = parent;
        this.speed = speed;
    }

    public boolean moveTo(Coordinate targetCoordinate, Coordinate fromCoordinate){
        return false;
    }
}
