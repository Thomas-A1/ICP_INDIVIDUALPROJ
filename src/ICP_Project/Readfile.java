package ICP_Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

/**
 * Descriptionn: Reading each csv file into an array and then adding them to an ArrayList to be returned
 * This is done by spliting the csv file using the comma as the delimeter
 */

public class Readfile {
    public static ArrayList<String[]> Readfile(String filename){
        ArrayList<String[]> contents = new ArrayList<>();
        BufferedReader reader = null;
        try{
            File file = new File(filename);
            reader = new BufferedReader(new FileReader(file));
            String content;
            String[] fieldobjects;

            while ((content = reader.readLine()) != null){
                fieldobjects = content.split(",");
                contents.add(fieldobjects);
            }
        }catch (FileNotFoundException fne){
            fne.printStackTrace();
        }catch (IOException ie){
            ie.printStackTrace();
        }
        // Handling the error catched
        finally{
            try{
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return contents;
    }

}
