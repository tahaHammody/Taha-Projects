package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.Player;
import model.SysData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.JSONPlayersReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    private Button backBtn;

    //Add
    ObservableList<Player> playersList = FXCollections.observableArrayList();

    @FXML
    private TableView<Player> leaderboard;

    @FXML
    private TableColumn<Player, Integer> rank;

    @FXML
    private TableColumn<Player, Long> score;

    @FXML
    private TableColumn<Player, String> username;
    SysData sysData = SysData.getInstance();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONPlayersReader jsonPlayersRead = new JSONPlayersReader();
        jsonPlayersRead.readPlayers("/json/Players.json");

        loadDataToPlayers();
        loadDataToTable();

    }

    //Fill out the table view with data
    public void loadDataToTable(){
        username.setCellValueFactory(new PropertyValueFactory<>("Nickname"));
        score.setCellValueFactory(new PropertyValueFactory<>("HighestScore"));
        rank.setCellValueFactory(new PropertyValueFactory<>("Rank"));

        leaderboard.setItems(playersList);
    }

    //Add data to the observable list
    public void loadDataToPlayers(){
        ArrayList<Player> playersScores = new ArrayList<>();

        playersScores.addAll(this.sysData.getPlayers());

        //Sort the results
        Collections.sort(playersScores, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return  Long.compare(o2.getHighestScore(), o1.getHighestScore());
            }
        });

        // Add rank attribute based on the sorted scores
        for (Player p:
             playersScores) {
            p.setRank(playersScores.indexOf(p) + 1);
        }

        //Load data to the TableView
        playersList.clear();
        playersList.addAll(playersScores);


    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/homepageMenu.fxml"));
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



}
