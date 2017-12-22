package environment;

public class Space {

    private boolean hasObject;

    public Space(){
        hasObject = false;
    }

    public void setHasObject(boolean hasObject){ this.hasObject = hasObject;}
    public boolean getHasObject(){ return this.hasObject; }
}
