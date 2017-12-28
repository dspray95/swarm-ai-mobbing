package simulation;

import environment.Environment;

import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger implements Serializable{

    String filepath;
    String filename;
    Environment environmentState;
    ArrayList<Object> storedStates;

    public Logger() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        filepath = Paths.get(".").toAbsolutePath().normalize().toString() +
                File.separator + "swarmlog" + File.separator; //File.seperator used for cross-OS support
        if(!new File(filepath).exists()){
            if(!new File(filepath).mkdir()){
                throw new Exception("Dir failed");
            }
        }
        filename = dateFormat.format(date) + ".swarmlog";
        storedStates = new ArrayList<>();
    }

    public void addStoredState(Object object){
        storedStates.add(object);
    }

    /**
     *
     * Writes every object in storedStates ArrayList sequentially to a SWARMLOG file in the absolute path with a timestamp
     * filename
     * @return true/false successful
     */
    public boolean log(){
        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filepath + filename);
            out = new ObjectOutputStream(fos);
            for(Object obj : storedStates){
                out.writeObject(obj);
            }
            out.close();
            String writtenTo = filepath + filename;
            System.out.println(writtenTo);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Sequentially loads every state into an ArrayList from the previously written file
     * @return Sequential arraylist of Environment states
     *
     * TODO: Vararg of specific file to load from, support for GUI user file selection
     */
    public ArrayList<Environment> loadStates(){
        // read the object from file
        // save the object to
        ArrayList<Environment> readStates = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filepath + filename);
            in = new ObjectInputStream(fis);
            for(int i = 0; i < storedStates.size(); i++){
                readStates.add((Environment) in.readObject());
            }
            in.close();
            return readStates;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getFilepath(){
        return this.filepath;
    }

    public String getFilename(){
        return this.filename;
    }
}
