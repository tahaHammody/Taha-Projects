package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.SysData;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController  {
    @FXML
    private Button backBtn;

    @FXML
    private Button changeThemeBtn;

    @FXML
    private RadioButton radioBtn1;

    @FXML
    private RadioButton radioBtn2;

    @FXML
    private RadioButton radioBtn3;

    @FXML
    private RadioButton radioBtn4;

    @FXML
    private ToggleGroup themeToggleGroup;

    SysData sysData = SysData.getInstance();

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

    @FXML
    void changeTheme(ActionEvent event) {
        //Get selected radio button
        Toggle themeRadioBtn = themeToggleGroup.getSelectedToggle();
        String selectedTheme = "Coral";
        if (themeRadioBtn.equals(radioBtn1)){
            selectedTheme = "Coral";
        } else if (themeRadioBtn.equals(radioBtn2)) {
            selectedTheme = "Dusk";
        }else if (themeRadioBtn.equals(radioBtn3)) {
            selectedTheme = "Emerald";
        }else if (themeRadioBtn.equals(radioBtn4)) {
            selectedTheme = "Sandcastle";
        }

        this.sysData.setTheme(selectedTheme);
        System.out.println(this.sysData.getTheme());

    }

}
