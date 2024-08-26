package control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ChessBoard;
import model.Game;
import model.Player;
import model.SysData;
import utils.GameLevel;
import utils.JSONPlayersReader;
import utils.JSONPlayersWriter;

import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Controller implements Initializable {
    SysData sysData = SysData.getInstance();

    Timeline timeline3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game game = new Game(chessBoard, this.sysData.getTheme());

        showResultBtn.setVisible(false);
        playBtn.setVisible(false);
        gameTimer();

        if(Game.getCurrentLevelScore()==0)
            levelTimer();

        display();

        //Updating the labels per second
         timeline3 = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            display();
            if(!Game.isGame()){
                //Update score
                updateScore(Integer.parseInt(currentScore.getText()));

                countDownTimeLine.stop();
                countDownTimeLine1.stop();
                if(Game.isWin){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(GameOverWinnerController.class.getResource("/view/winner.fxml"));
                        Stage stage = (Stage) showResultBtn.getScene().getWindow();
                        stage.setScene(new Scene(fxmlLoader.load()));
                        stage.setTitle("Result");
                        stage.show();

                    } catch (Exception err) {
                        System.err.println(err.getMessage());
                    }
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(GameOverController.class.getResource("/view/gameOver.fxml"));
                        Stage stage = (Stage) showResultBtn.getScene().getWindow();
                        stage.setScene(new Scene(fxmlLoader.load()));
                        stage.setTitle("Result");
                        stage.show();

                    } catch (Exception err) {
                        System.err.println(err.getMessage());
                    }
                }
                timeline3.stop();
            }
        }));

        timeline3.setCycleCount(Animation.INDEFINITE);
        timeline3.play();
    }

    @FXML
    private GridPane chessBoard;

    @FXML
    private Label timeLeftToGame;

    @FXML
    private Label level;

    @FXML
    private Label currentScore;

    @FXML
    private Label squaresLeft;

    @FXML
    private Label timeLeftToLevel;

    @FXML
    private Button exitBtn;

    @FXML
    private Button showResultBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private Button playBtn;

    public static // Create duration property for time remaining: for the whole game
    ObjectProperty<java.time.Duration> remainingDuration
            = new SimpleObjectProperty<>(java.time.Duration.ofMinutes(4)); // Here: 4 min count down

    public static // Create time line to lower remaining duration every second:
    Timeline countDownTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
            remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));

    public static // Create duration property for time remaining: for each level
    ObjectProperty<java.time.Duration> remainingDuration1
            = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(60)); // Here: 1 min count down

    // Create time line to lower remaining duration every second:
    public static Timeline countDownTimeLine1 = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
            remainingDuration1.setValue(remainingDuration1.get().minus(1, ChronoUnit.SECONDS))));



    public void display() {
        level.setText(Game.currentLevel);
        currentScore.setText(String.valueOf(Game.currentScore));
        squaresLeft.setText(String.valueOf(Game.squaresLeft));
    }

    public void gameTimer() {
        // Binding with media time format (hh:mm:ss):
        timeLeftToGame.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d:%02d",
                                remainingDuration.get().toHours(),
                                remainingDuration.get().toMinutesPart(),
                                remainingDuration.get().toSecondsPart()),
                remainingDuration));


        // Set number of cycles (remaining duration in seconds):
        countDownTimeLine.setCycleCount((int) remainingDuration.get().getSeconds());

        // Show alert when time is up:
        countDownTimeLine.setOnFinished(event ->  Game.setGame(false));
        // Start the time line:
        countDownTimeLine.play();

    }

    public static void pauseTimer(){
        countDownTimeLine.pause();
        Game.setIsPaused(true);
    }

    public static void playTimer(){
        countDownTimeLine.play();
        Game.setIsPaused(false);
    }

    public void levelTimer() {
        // Binding with media time format (hh:mm:ss):
        timeLeftToLevel.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d:%02d",
                                remainingDuration1.get().toHours(),
                                remainingDuration1.get().toMinutesPart(),
                                remainingDuration1.get().toSecondsPart()),
                remainingDuration1));
        // Set number of cycles (remaining duration in seconds):
        countDownTimeLine1.setCycleCount((int) remainingDuration1.get().getSeconds());

        // Change level when time is up
        countDownTimeLine1.setOnFinished(event -> changeToSecondLevel());

        // Start the time line:
     countDownTimeLine1.play();
    }

    public void changeToSecondLevel(){
        if (Game.currentLevelScore >= 15) {
            if (Game.getCurrentLevel().equals(GameLevel.BASIC.toString())) {
                Game.emptyAllTheSquares();
                Game.replaceTheQueen();
                Game.replaceTheKnight();
                Game.setCurrentLevel(GameLevel.INTERMEDIATE.toString());
                Game.setCurrentLevelScore(0);
                Game.randomJumpSquares();
                Game.randomForgetSquares();
                Game.setSquaresLeft(63);
                level2Timer();
            }
        } else {
            countDownTimeLine.stop();
            Game.setIsWin(false);
            Game.setGame(false);
        }
    }

    public void level2Timer() {
        // Create duration property for time remaining: for each level
         remainingDuration1 = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(60)); // Here: 1 min count down
        // Binding with media time format (hh:mm:ss):
        timeLeftToLevel.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d:%02d",
                                remainingDuration1.get().toHours(),
                                remainingDuration1.get().toMinutesPart(),
                                remainingDuration1.get().toSecondsPart()),
                remainingDuration1));

        // Create time line to lower remaining duration every second:
       // Timeline countDownTimeLine1 = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
         //       remainingDuration1.setValue(remainingDuration1.get().minus(1, ChronoUnit.SECONDS))));
        // Set number of cycles (remaining duration in seconds):
        countDownTimeLine1.setCycleCount((int) remainingDuration1.get().getSeconds());

        // Change level when time is up
        countDownTimeLine1.setOnFinished(event -> changeToThirdLevel());

        // Start the time line:
        countDownTimeLine1.play();
    }

    public void changeToThirdLevel(){
        if (Game.currentLevelScore  >= 15) {
            Game.emptyAllTheSquares();
            Game.deleteTheQueen();
            ChessBoard.addKingPiece();
            Game.replaceTheKnight();
            if (Game.getCurrentLevel().equals(GameLevel.INTERMEDIATE.toString())) {
                Game.setCurrentLevel(GameLevel.ADVANCED.toString());
                Game.setCurrentLevelScore(0);
                Game.randomJumpSquares();
                Game.randomForgetSquares();
                Game.setSquaresLeft(63);
                level3Timer();
            }
        } else {
            countDownTimeLine.stop();
            Game.setIsWin(false);
            Game.setGame(false);
        }
    }

    public void level3Timer() {
        // Create duration property for time remaining: for each level
        remainingDuration1 = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(60)); // Here: 1 min count down
        // Binding with media time format (hh:mm:ss):
        timeLeftToLevel.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d:%02d",
                                remainingDuration1.get().toHours(),
                                remainingDuration1.get().toMinutesPart(),
                                remainingDuration1.get().toSecondsPart()),
                remainingDuration1));

        // Create time line to lower remaining duration every second:
       // Timeline countDownTimeLine1 = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
          //      remainingDuration1.setValue(remainingDuration1.get().minus(1, ChronoUnit.SECONDS))));
        // Set number of cycles (remaining duration in seconds):
        countDownTimeLine1.setCycleCount((int) remainingDuration1.get().getSeconds());
        AutoKmoves();
        // Change level when time is up
        countDownTimeLine1.setOnFinished(event -> changeToFourthLevel());

        // Start the time line:
        countDownTimeLine1.play();
    }

    public void changeToFourthLevel(){
        if (Game.currentLevelScore >= 15) {
            if (Game.getCurrentLevel().equals(GameLevel.ADVANCED.toString())) {
                Game.emptyAllTheSquares();
                Game.replaceTheKing();
                Game.replaceTheKnight();
                Game.setCurrentLevel(GameLevel.ADVANCED_PRO.toString());
                Game.setCurrentLevelScore(0);
                //Game.randomJumpSquares();
                //Game.randomForgetSquares();
                Game.randomBlockedSquares();
                Game.setSquaresLeft(63);
                level4Timer();
            }
        } else {
            Controller.countDownTimeLine.stop();
            Game.setIsWin(false);
            Game.setGame(false);
        }
    }

    public void level4Timer() {
        // Create duration property for time remaining: for each level
         remainingDuration1 = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(60)); // Here: 1 min count down
        // Binding with media time format (hh:mm:ss):
        timeLeftToLevel.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d:%02d",
                                remainingDuration1.get().toHours(),
                                remainingDuration1.get().toMinutesPart(),
                                remainingDuration1.get().toSecondsPart()),
                remainingDuration1));

        // Create time line to lower remaining duration every second:
        //Timeline countDownTimeLine1 = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
            //    remainingDuration1.setValue(remainingDuration1.get().minus(1, ChronoUnit.SECONDS))));
        // Set number of cycles (remaining duration in seconds):
        countDownTimeLine1.setCycleCount((int) remainingDuration1.get().getSeconds());
                AutoKmoves();

        // Change level when time is up
        countDownTimeLine1.setOnFinished(event -> Game.setGame(false));

        // Start the time line:
        countDownTimeLine1.play();
    }

    public static void pauseLevel(){
        countDownTimeLine1.pause();
        Game.setIsPaused(true);
    }

    public static void playLevel(){
        countDownTimeLine1.play();
        Game.setIsPaused(false);
    }


    // This should be called whenever the game state is set to false - it just needs the current score at the moment that the game ended
    // Example: updateScore(Integer.parseInt(currentScore.getText()));
    private void updateScore(int gameScore){
        ArrayList<Player> players = SysData.getInstance().getPlayers();
        long currentPlayerScore = SysData.getInstance().getLoggedInPlayer().getHighestScore();
        Player currPlayer = SysData.getInstance().getLoggedInPlayer();
        int indexOfCurrPlayer = players.indexOf(currPlayer);
        currPlayer.setHighestScore(currentPlayerScore+ gameScore);

        players.set(indexOfCurrPlayer, currPlayer);

        //Load all the players to a JSONArray called allPlayers
        JsonArray allPlayersJsonArray = new JsonArray();
        for (Player p : players) {
            JsonObject playerObj = new JsonObject();
            playerObj.addProperty("nickname", p.getNickname());
            playerObj.addProperty("highestScore", p.getHighestScore());
            allPlayersJsonArray.add(playerObj);
        }

        JSONPlayersWriter jsonPlayersWriter = new JSONPlayersWriter();
        JSONPlayersReader jsonPlayersRead = new JSONPlayersReader();
        if (jsonPlayersWriter.writePlayers(allPlayersJsonArray)) {
            //Update players in sysData
            jsonPlayersRead.readPlayers("/json/Players.json");
        }

    }

    @FXML
    void exit(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomepageMenuController.class.getResource("/view/homepageMenu.fxml"));
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void pauseGame(ActionEvent event){
        pauseLevel();
        pauseTimer();
        playBtn.setVisible(true);
        pauseBtn.setVisible(false);
    }

    @FXML
    void playGame(ActionEvent event){
        playLevel();
        playTimer();
        playBtn.setVisible(false);
        pauseBtn.setVisible(true);
    }
    //lastupdate
    private void AutoKmoves() {
        Timeline timeline = new Timeline(
  	    new KeyFrame(Duration.seconds(5), e -> {
          Game.dropKing1();



      })
  );
  Timeline timeline2 = new Timeline(
  	    new KeyFrame(Duration.seconds(2), e -> {
          Game.dropKing1();


      })
  );
  timeline.play();
  timeline.setOnFinished(e->timeline2.play());
  //**
  Timeline timeline3 = new Timeline(
  		    new KeyFrame(Duration.seconds(2), e -> {
                Game.dropKing1();


  		
  		    })
  		);
  timeline2.setOnFinished(e->timeline3.play());

    Timeline timeline4 = new Timeline(
  		    new KeyFrame(Duration.seconds(2), e -> {
                Game.dropKing1();

  		
  		    })
  		);
    timeline3.setOnFinished(e->timeline4.play());


  Timeline timeline5 = new Timeline(
  	    new KeyFrame(Duration.seconds(2), e -> {
          Game.dropKing1();


      })
  );
  	timeline4.setOnFinished(e->timeline5.play());
  //10		
    Timeline timeline6 = new Timeline(
  		    new KeyFrame(Duration.seconds(5), e -> {
                Game.dropKing1();

  		
  		    })
  		);                  		
  		timeline5.setOnFinished(e->timeline6.play());
  		
  		Timeline timeline7 = new Timeline(
     		    new KeyFrame(Duration.seconds(5), e -> {
                     Game.dropKing1();

     		
     		    })
     		);                  		
     		timeline6.setOnFinished(e->timeline7.play());
     //20 

     	 Timeline timeline8 = new Timeline(
      		    new KeyFrame(Duration.seconds(4), e -> {
                      Game.dropKing1();

      		
      		    })
      		);                  		
      		timeline7.setOnFinished(e->timeline8.play());
      		Timeline timeline9 = new Timeline(
      			    new KeyFrame(Duration.seconds(4), e -> {
      		        Game.dropKing1();


      		    })
      		);
      		timeline8.setOnFinished(e->timeline9.play());
      		//**
      		
      		Timeline timeline10 = new Timeline(
      				    new KeyFrame(Duration.seconds(5), e -> {
      		              Game.dropKing1();


      				
      				    })
      				);
      		timeline9.setOnFinished(e->timeline10.play());

      		  Timeline timeline11 = new Timeline(
      				    new KeyFrame(Duration.seconds(3), e -> {
      		              Game.dropKing1();

      				
      				    })
      				);
      		  timeline10.setOnFinished(e->timeline11.play());


      		Timeline timeline12 = new Timeline(
      			    new KeyFrame(Duration.seconds(3), e -> {
      		        Game.dropKing1();


      		    })
      		);
      			timeline11.setOnFinished(e->timeline12.play());
      		//40		
      		  Timeline timeline13 = new Timeline(
      				    new KeyFrame(Duration.seconds(5), e -> {
      		              Game.dropKing1();

      				
      				    })
      				);                  		
      				timeline12.setOnFinished(e->timeline13.play());
      				
      				Timeline timeline14 = new Timeline(
      		   		    new KeyFrame(Duration.seconds(4), e -> {
      		                   Game.dropKing1();

      		   		
      		   		    })
      		   		);                  		
      		   		timeline13.setOnFinished(e->timeline14.play());
      		   

      		   	 Timeline timeline15 = new Timeline(
      		    		    new KeyFrame(Duration.seconds(3), e -> {
      		                    Game.dropKing1();

      		    		
      		    		    })
      		    		);                  		
      		    		timeline14.setOnFinished(e->timeline15.play());
      		    	  
      		    		Timeline timeline16 = new Timeline(
      		    	    	    new KeyFrame(Duration.seconds(1), e -> {
      		    	            Game.dropKing1();



      		    	        })
      		    	    );
     
      		    		timeline15.setOnFinished(e->timeline16.play());
      		    	    //**
      		    	    Timeline timeline17 = new Timeline(
      		    	    		    new KeyFrame(Duration.seconds(1), e -> {
      		    	                  Game.dropKing1();


      		    	    		
      		    	    		    })
      		    	    		);
      		    	    timeline16.setOnFinished(e->timeline17.play());

      		    	      Timeline timeline18 = new Timeline(
      		    	    		    new KeyFrame(Duration.seconds(1), e -> {
      		    	                  Game.dropKing1();

      		    	    		
      		    	    		    })
      		    	    		);
      		    	      timeline17.setOnFinished(e->timeline18.play());


      		    	    Timeline timeline19 = new Timeline(
      		    	    	    new KeyFrame(Duration.seconds(1), e -> {
      		    	            Game.dropKing1();


      		    	        })
      		    	    );
      		    	    	timeline18.setOnFinished(e->timeline19.play());
      		    	    //10		
      		    	      Timeline timeline20 = new Timeline(
      		    	    		    new KeyFrame(Duration.seconds(1), e -> {
      		    	                  Game.dropKing1();

      		    	    		
      		    	    		    })
      		    	    		);                  		
      		    	    		timeline19.setOnFinished(e->timeline20.play());
      		    	    		
      		    	    		Timeline timeline21 = new Timeline(
      		    	       		    new KeyFrame(Duration.seconds(1), e -> {
      		    	                       Game.dropKing1();

      		    	       		
      		    	       		    })
      		    	       		);                  		
      		    	       		timeline20.setOnFinished(e->timeline21.play());
      		    	       //20 

      		    	       	 Timeline timeline22 = new Timeline(
      		    	        		    new KeyFrame(Duration.seconds(1), e -> {
      		    	                        Game.dropKing1();

      		    	        		
      		    	        		    })
      		    	        		);                  		
      		    	        		timeline21.setOnFinished(e->timeline22.play());
      		    	        		Timeline timeline23 = new Timeline(
      		    	        			    new KeyFrame(Duration.seconds(4), e -> {
      		    	        		        Game.dropKing1();


      		    	        		    })
      		    	        		);
      		    	        		timeline22.setOnFinished(e->timeline23.play());
  	}





}



