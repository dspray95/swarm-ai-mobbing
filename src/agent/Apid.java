package agent;

import agent.listener.ThreatEvent;
import agent.module.state.Guard;
import agent.module.state.Mob;
import agent.module.state.Worker;
import agent.pheremone.Pheromone;
import agent.swarm.Swarm;
import environment.Coordinate;
import environment.Environment;
import simulation.config.SimulationDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Apid extends Agent implements ThreatEvent, Serializable {

    private Coordinate location;
    transient private int alertLevel;
    transient private Swarm swarm;

    public Apid(Coordinate location, Environment environment){
        super(location, environment);
        this.hitpoints = SimulationDefaults.APID_HP;
        this.speed = SimulationDefaults.APID_SPEED;
        this.heatResistance = SimulationDefaults.APID_HEAT_THRESHOLD;
        this.aggression = SimulationDefaults. APID_AGGRESSION;
        this.state = new Worker(this);
        this.swarm = environment.getApidSwarm();
        this.swarm.add(this);
        this.swarm.add(this);
        this.alertLevel = 0;
        this.location = location;
    }

    @Override
    public void judgeStateChange(){
        Random r = new Random();
        int numWorkers = perceptor.getPerceivedWorkers().size();
        int numMob = perceptor.getPerceivedMob().size();
        double chanceToJoin = 0;
        //Chance to join the guard group is affected by the number of perceived guards multiplied by the aggression
        if(state.getClass() == Worker.class){
            int numGuards; //The number of guards currently perceived by this agent
            //If the alert level is high enough to cause alarm instantly change to guard state
            if(alertLevel >= 100){
                this.state = new Guard(this);
                return;
            }
            //If the alert level was not high enough to trigger guard state, run random chance of triggering guard state
            //based on aggression and perceived number of other guards
            numGuards = perceptor.getPerceivedGuards().size();
            if(numGuards > 0){
                System.out.println("PERCIEVED " + numGuards + " GUARDS");
            }
            if(numGuards <= SimulationDefaults.JOIN_GUARD_THRESHOLD) { //High rate of joining below the size threshold
                chanceToJoin = numGuards/SimulationDefaults.JOIN_GUARD_THRESHOLD;
                chanceToJoin = (chanceToJoin*aggression)*2;
            }
            else if(numGuards > SimulationDefaults.JOIN_GUARD_THRESHOLD) { //Lower rate of joining above the threshold
                chanceToJoin = numGuards/SimulationDefaults.JOIN_GUARD_THRESHOLD;
                chanceToJoin = chanceToJoin*aggression;
            }
            if(r.nextInt(100) <= chanceToJoin){
                this.state = new Guard(this);
//                System.out.println("Agent join GUARDS");
                return;
            }
        }

        else if(state.getClass() == Guard.class){

        }

        else if(state.getClass() == Mob.class){

        }


        /**
         * In order to decide whether to leave guards and work --
         * 1. Get perceived number of guards n
         * 2. If n >= i leave guards
         * 3. As n increases chance to leave (c) also increases
         * 4. As threat level t increases chance to leave decreases
         * 5. perform random chance to leave (modified by aggression a)
         *
         * So c = f(n, t, a) !!!
         */
    }

    /**
     * Increments the value of pheromone strength in the apid's current space
     */
    public void setPheromone(){
        ArrayList<Pheromone> environmentPheromones = environment.getPheromones();
        for(Pheromone pheromone : environmentPheromones){
            if(pheromone.getLocation() == this.location){
                pheromone.increaseStrength();
                return;
            }
        }
        environment.addPheromone(new Pheromone(this.location));
    }

    @Override
    public void threatAlert() {
        setPheromone();
        alertLevel += 100;
    }
}