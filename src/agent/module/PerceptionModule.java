package agent.module;

import agent.Agent;
import config.SimulationDefaults;
import environment.Coordinate;

public class PerceptionModule {

    Agent parent;
    int perceptionRadius;

    public PerceptionModule(Agent parent){
        this.parent = parent;
        this.perceptionRadius = SimulationDefaults.PERCEPTION_RADIUS;
    }

    public Coordinate[] perceptionTick(){
        Coordinate currentLocation = parent.getLocation();
        //Get square bounds
        Coordinate[] squareBounds = new Coordinate[perceptionRadius*2];
        for(int i = 1; i < perceptionRadius*2; i++){
            for(int j = 1; j < perceptionRadius*2; j++) {
                squareBounds[i] = new Coordinate(i, j);
            }
        }
        //Now we can check through every coordinate in the bounds, also validating if they're within the euclidean radius
        for(Coordinate coordinate : squareBounds){
            //Only perceive if the coordinate is within the bounds
            if(coordinate.EuclideanDistance(currentLocation) <= perceptionRadius){

            }
        }
        //Narrow to radius
        //Check for things
        return squareBounds;
    }

}
