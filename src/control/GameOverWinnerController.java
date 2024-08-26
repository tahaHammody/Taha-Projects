package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOverWinnerController implements Initializable {
    @FXML
    private Button noBtn;

    @FXML
    private Button yesBtn;

    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView gobletImg;

    @FXML
    void openMenu(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/leaderboard.fxml"));
            Stage stage = (Stage) noBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void startNewGame(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/homepage.fxml"));
            Stage stage = (Stage) yesBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialising path of the media file, replace this with your file path
        URL path = getClass().getResource("/audio/GameOver.mp3");

        //Instantiating Media class
        Media media = new Media(path.toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

        scoreLabel.setText(Integer.toString(Game.currentScore));

        if(Game.currentScore>=200){
            gobletImg.setVisible(true);
        } else {
            gobletImg.setVisible(false);
        }
    }
}
