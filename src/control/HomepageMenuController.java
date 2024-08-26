package control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.SysData;

import java.net.URL;
import java.util.ResourceBundle;


public class HomepageMenuController implements Initializable {

    @FXML
    private Button newGame;
    @FXML
    private Button manageQuestions;
    @FXML
    private Button logout;

    @FXML
    private Label nickname;

    @FXML
    private Button gameRulesBtn;

    @FXML
    private Button settingsBtn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeUser();
    }

    @FXML
    void openLoginPage(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/login.fxml"));
            Stage stage = (Stage) manageQuestions.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void startGame(ActionEvent event) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/game.fxml"));
                Stage stage = (Stage) newGame.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Chess");
                stage.show();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    @FXML
    void logout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/homepage.fxml"));
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void openLeaderboard(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/leaderboard.fxml"));
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    void welcomeUser(){
        nickname.setText("Hello " + SysData.loggedInPlayer.getNickname() + "!");
    }

    @FXML
    void goToGameRules(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/gameRules.fxml"));
            Stage stage = (Stage) gameRulesBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void goToSettings(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/settings.fxml"));
            Stage stage = (Stage) settingsBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
//            stage.getIcons().add(new Image(HomepageMenuController.class.getResourceAsStream("/images/chess.png")));
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}