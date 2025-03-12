package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Serialize {

    public Serialize(){
    }

    public static void serialize(Result object, String timestamp){
        final Gson gson = new Gson();
        try (Writer writer = new FileWriter("history/" + "result" + timestamp + ".json")){
            gson.toJson(object,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Result> deserializeAll(){
        List<Result> resultList = new ArrayList<>();
        try {
            File[] files = new File("history").listFiles();

            assert files != null;
            for (File filename : files) {
                String path = filename.getPath();
                System.out.println(path);

                final Gson gson = new Gson();
                resultList.add(gson.fromJson(new JsonReader(new FileReader(path)), Result.class));
            }
            return resultList;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    public static String deserializeSingle(String path){
        final Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(path);
            JsonReader jsonReader = new JsonReader(fileReader)) {

            Result resultObject = gson.fromJson(jsonReader,Result.class);
            return resultObject.toStringDeserialize();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }




}
