package utils;

import control.HomepageController;
import model.Answer;
import model.Player;
import model.Question;
import model.SysData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class JSONPlayersReader {
    SysData sysData = SysData.getInstance();

    public void readPlayers(String filename) {
        JSONParser jsonParser = new JSONParser();

        JSONArray nicknamesList = null;

        ArrayList<Player> players = new ArrayList<Player>();

        try (InputStreamReader reader = new InputStreamReader(HomepageController.class.getResourceAsStream("/json/Players.json")))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            //Create a JSON object
            JSONObject mainJsonObject = (JSONObject) obj;

            //Load the players into a JSON array
            nicknamesList = (JSONArray) mainJsonObject.get("players");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*Check if db.json exits in the project*/
        File f = new File("db_players.json");
        if(f.exists())
        {
            InputStream is = null;
            try {
                is = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            JSONObject dbJsonObject = null;
            try {
                dbJsonObject = (JSONObject) jsonParser.parse(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray dbArray = (JSONArray) dbJsonObject.get("players");

            nicknamesList = dbArray;
        }

//        Iterate over the players' list and create an array list of Player objects
        for (int i = 0; i < nicknamesList.size(); i++) {
            JSONObject playerObj = (JSONObject) nicknamesList.get(i);
            String nickname = (String) playerObj.get("nickname");
            long highestScore = (long) playerObj.get("highestScore");
            Player p = new Player("");
            p.setNickname(nickname);
            p.setHighestScore(highestScore);
            players.add(p);
        }
        this.sysData.setPlayers(players);
    }
}
