package control;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import model.Game;

public class GameController {

    @FXML
    private GridPane chessBoard;

            Game game = new Game(chessBoard, "Coral");




}
