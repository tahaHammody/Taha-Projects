package control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Player;
import model.Question;
import model.SysData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.simple.parser.ParseException;
import utils.JSONPlayersReader;
import utils.JSONPlayersWriter;
import utils.JSONQuestionsReader;
import utils.JSONQuestionsWriter;


public class HomepageController{

    public static String currentNickname;
    @FXML
    private Button enter;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField nickname;

    SysData sysData = SysData.getInstance();

    JSONParser jsonParser = new JSONParser();


    ArrayList<Player> players = null;

    @FXML
    void enter(ActionEvent event) {

        //Store the input in a variable called result
        String result = nickname.getText();

        //Read players from JSON
        JSONPlayersReader jsonPlayersRead = new JSONPlayersReader();
        jsonPlayersRead.readPlayers("/json/Players.json");
        players = this.sysData.getPlayers();


        //Check if the nickname's field isn't empty
        if (!result.isEmpty()) {
            //Check if the nickname already exists
            Boolean nicknameExist = false;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getNickname().equals(result)) {
                    nicknameExist = true;
                }
            }

            //If the nickname doesn't exist, then add it to the JSON db
            if (nicknameExist == false) {
                /*Add the new player to the arraylist*/
                Player newPlayer = new Player(result, 0);
                players.add(newPlayer);

                //Load all the players to a JSONArray called allPlayers
                JsonArray allPlayersJsonArray = new JsonArray();
                for (Player p : players) {
                    JsonObject playerObj = new JsonObject();
                    playerObj.addProperty("nickname", p.getNickname());
                    playerObj.addProperty("highestScore", p.getHighestScore());
                    allPlayersJsonArray.add(playerObj);
                }

                //Write to JSON
                JSONPlayersWriter jsonPlayersWriter = new JSONPlayersWriter();
                if (jsonPlayersWriter.writePlayers(allPlayersJsonArray)) {
                    System.out.println("A new player was added!");
                }

                //Update players in sysData
                jsonPlayersRead.readPlayers("/json/Players.json");
                players = sysData.getPlayers();
            }

            //Global variable to store the current logged-in user
            currentNickname = result;
            sysData.loggedInPlayer.setNickname(result);

            //Open the homepage menu page
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/homepageMenu.fxml"));
                Stage stage = (Stage) enter.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Chess");
                stage.show();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        else {
            msgLabel.setText("Please Enter Nickname!");
        }
    }



}
