package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

//this class serializes and deserializes result objects. This allows the HistoryPanel to be built
public class Serialize {

    public Serialize(){
    }

    //this methode serializes an Object
    public static void serialize(Result object, String timestamp){
        final Gson gson = new Gson();
        try (Writer writer = new FileWriter("history/" + "result" + timestamp + ".json")){ //define in which directory the object should be serialized and how to should be called
            gson.toJson(object,writer); //writing the object
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //this methode deserializes all files in the history directory
    public static List<Result> deserializeAll(){
        List<Result> resultList = new ArrayList<>(); //list which later contains all the result objects
        try {
            File[] files = new File("history").listFiles(); //listing all files in the history directory and add them to a list of files

            assert files != null;
            for (File filename : files) {
                String path = filename.getPath(); //getting every specific path for each file
                System.out.println(path);

                final Gson gson = new Gson();
                resultList.add(gson.fromJson(new JsonReader(new FileReader(path)), Result.class)); //deserialize and add the files / result objects to the resultList
            }
            return resultList;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    //this methode deserializes only one files with the given path
    public static String deserializeSingle(String path){
        final Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(path);
            JsonReader jsonReader = new JsonReader(fileReader)) {

            Result resultObject = gson.fromJson(jsonReader,Result.class); //deserialize the file
            return resultObject.toStringDeserialize();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
