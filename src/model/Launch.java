package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Launch extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        mainWindow(primaryStage);
    }

    public void mainWindow (Stage primaryStage){
        stage = primaryStage;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/homepage.fxml"));

//            URL url = new File("src/view/homepageMenu.fxml").toURI().toURL();

//            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root1 = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
