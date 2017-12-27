import environment.Environment;

import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements Serializable{

    String filepath;
    String filename;
    Environment environmentState;

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
    }

    public boolean log(Object object){
        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filepath + filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(object);
            out.close();
            String writtenTo = filepath + filename;
            System.out.println(writtenTo);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Environment load(){
        // read the object from file
        // save the object to
        Environment env;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filepath + filename);
            in = new ObjectInputStream(fis);
            env = (Environment) in.readObject();
            in.close();
            return env;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
