package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JSONQuestionsWriter {
    public Boolean writeQuestions(JsonArray allQuestionsJsonArray){
        Boolean success = false;

        JsonObject root = new JsonObject();
        root.add("questions", allQuestionsJsonArray);

        //Write to JSON file
        try{
            PrintWriter pw = new PrintWriter("db.json");
            pw.write(root.toString());
            pw.flush();
            pw.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;

    }
}
