package simulation;

import environment.Environment;

public class Simulator{

    static Environment environment;

    public static void main(String args[]){
        environment = new Environment();
        //for sometime Environment.notifytick etc...
        environment.tickerEvent();
    }



}
