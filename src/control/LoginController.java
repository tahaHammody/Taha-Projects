
package control;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;

        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import java.io.File;
        import java.net.URL;

public class LoginController {

    @FXML
    private Button backBtn;
    @FXML
    private Button loginBtn;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField nickname;

    @FXML
    private PasswordField password;

    @FXML
    void goBack(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/homepageMenu.fxml"));
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void handleLogin(ActionEvent event) {
        //Validate the username/nickname field
        if(nickname.getText().isEmpty() || password.getText().isEmpty()) {
            msgLabel.setText("Please Enter Nickname or Password!");
        }
        else if(nickname.getText().equals("admin") && password.getText().equals("admin")){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/questions.fxml"));
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Chess");
                stage.show();

            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        else{
            msgLabel.setText("Incorrect Nickname or Password!");
        }
    }

}
