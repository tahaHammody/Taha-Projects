package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import control.HomepageController;
import model.Player;
import model.SysData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class JSONPlayersWriter {

    public Boolean writePlayers(JsonArray allPlayersJsonArray) {
        Boolean success = false;

        //Write to JSON file
        JsonObject root = new JsonObject();
        root.add("players", allPlayersJsonArray);

        try{
            PrintWriter pw = new PrintWriter("db_players.json");
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
