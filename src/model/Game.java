package model;

import control.Controller;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import utils.DifficultyLevel;
import utils.GameLevel;
import utils.JSONQuestionsReader;

import java.util.*;

public class Game {

    public static Piece currentPiece;

    public static String currentPlayer;
    public static ChessBoard cb;

    public static boolean game; //false = the game over

    public static int currentScore;

    public static int currentLevelScore;

    public static String currentLevel;

    public static int squaresLeft;

    public static boolean isWin;

    public static boolean isPaused;

    public static Queue<Square> threeStepsHistory; //here we save the last three moves


    public Game(GridPane chessBoard, String theme) {
        cb = new ChessBoard(chessBoard, theme);
        currentPiece = null;
        currentPlayer = "white";
        game = true;
        currentScore = 0;
        addEventHandlers(cb.chessBoard);
        currentLevel = GameLevel.BASIC.toString();
        squaresLeft = 63;
        currentLevelScore = 0;
        isWin = true;
        //emptyAllTheSquares();
        //emptyAllTheQuestionsSquares();
        randomJumpSquares();
        threeStepsHistory = new LinkedList<Square>();
        randomThreeQuestions();
        isPaused = false;

    }

    public static boolean isGame() {
        return game;
    }

    public static void setGame(boolean game) {
        Game.game = game;
    }

    public static boolean isIsWin() {
        return isWin;
    }

    public static void setIsWin(boolean isWin) {
        Game.isWin = isWin;
    }

    public static String getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(String currentLevel) {
        Game.currentLevel = currentLevel;
    }

    public static int getCurrentLevelScore() {
        return currentLevelScore;
    }

    public static void setCurrentLevelScore(int currentLevelScore) {
        Game.currentLevelScore = currentLevelScore;
    }

    public static int getSquaresLeft() {
        return squaresLeft;
    }

    public static void setSquaresLeft(int squaresLeft) {
        Game.squaresLeft = squaresLeft;
    }

    public static boolean isIsPaused() {
        return isPaused;
    }

    public static void setIsPaused(boolean isPaused) {
        Game.isPaused = isPaused;
    }

    private void addEventHandlers(GridPane chessBoard) {
        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                EventTarget target = event.getTarget();

                // Clicked on square
                if (target.toString().equals("Square")) {
                    Square square = (Square) target;
                    if (square.occupied) {
                        Piece newPiece = (Piece) square.getChildren().get(0);

                        // Selecting a new piece
                        if (currentPiece == null) {
                            currentPiece = newPiece;
//                            currentPiece.getAllPossibleMoves();
                            if (!currentPiece.getColor().equals(currentPlayer)) {
                                currentPiece = null;
                                return;
                            }
                            selectPiece(game);
                        }
                        // Selecting other piece of same color || Killing a piece
                        else {
                            if (currentPiece.color.equals(newPiece.color)) {
                                deselectPiece(false);
                                currentPiece = newPiece;
//                                currentPiece.getAllPossibleMoves();
                                selectPiece(game);
                            } else {
                                killPiece(square);
                            }
                        }

                    }
                    // Dropping a piece on blank square
                    else {
                        if(square!=null){
                            dropPiece(square);
                        }
                    }
                }
                // Clicked on piece
                else {
                    Piece newPiece = (Piece) target;
                    if(newPiece.type.equals("Knight")){
                        Square square = (Square) newPiece.getParent();
                        // Selecting a new piece
                        if (currentPiece == null) {
                            currentPiece = newPiece;
                            return;
                            // }
                            // selectPiece(game);
                        }
                        // Selecting other piece of same color || Killing a piece
                        else {
                            if (currentPiece.color.equals(newPiece.color)) {
                                deselectPiece(false);
                                currentPiece = newPiece;
                                selectPiece(game);
                            } else {
                                killPiece(square);
                            }
                        }

                    }

                }
            }

        });
    }

    private void selectPiece(boolean game) {
        if (!game) {
            currentPiece = null;
            return;
        }

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPiece.setEffect(borderGlow);
        if((currentPiece.type.equals("Knight")) && (currentLevel.equals("Intermediate"))){
            currentPiece.getAllPossibleMovesSecondLevel();
        }
        else {
            currentPiece.getAllPossibleMoves();
        }
        currentPiece.showAllPossibleMoves(true);
    }

    private static void deselectPiece(boolean changePlayer) {
        if(currentPiece != null){
            currentPiece.setEffect(null);
            currentPiece.showAllPossibleMoves(false);
            currentPiece = null;
           /* if((currentLevel.equals(GameLevel.BASIC)) || (currentLevel.equals(GameLevel.INTERMEDIATE))){
                currentPiece = getTheQueen();
                Piece queen = (Queen) currentPiece;
                movingToSquare(((Queen) queen).bestMove(getCurrentSquare().x, getCurrentSquare().y));
                deselectPiece(true);
            }*/

        }
        //if (changePlayer) currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    private static void dropPiece(Square square) {
        //     if(currentPiece.possibleMoves!=null){
        //       if (!currentPiece.possibleMoves.contains(square.name)) return;
        // }
        if(currentLevel.equals("Advanced")||currentLevel.equals("Advanced Pro")) {
            if(isPaused){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Opps");
                alert.setHeaderText(null);
                alert.setContentText("You paused the game, press on play button to continue.");
                alert.showAndWait();
                return;
            }
            Square emptySquare = null;
            boolean flag = false;
            if(square.isBlocked){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Illegal move!!");
                alert.setHeaderText(null);
                alert.setContentText("You can't move to a blocked square!");
                alert.showAndWait();
                return;
            }
            if (currentPiece.type.equals("Knight")) {
                if(square.isQuestionSquare){
                    showQuestionLabel(square.getQuestion());
                    anotherQuestionSquare(square.getQuestion().level);
                    square.setQuestion(null);
                    square.setQuestionSquare(false);
                    //Pause time when showing a question
                    Controller.pauseTimer();
                    Controller.pauseLevel();
                    colorSquare(square);
                }
                if(square.isForget){
                    forgetLast3Steps();
                    movingToSquare(square);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Opps!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Your three last steps were delete!");
                    alert.showAndWait();
                    anotherForgetSquare();
                    square.setVisitedByKnight(true);
                    square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                    squaresLeft--;

                    //  deselectPiece(true);
                    return;
                }
                if (!square.isVisitedByKnight) {
                    if (square.isJump) {
                        emptySquare = randomEmptySquare();
                        while (emptySquare.occupied)
                            emptySquare = randomEmptySquare();
                        //currentScore++;
                        //currentLevelScore++;
                        //squaresLeft--;
                        anotherJumpSquare();
                        flag = true;

                    }
                    square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                    square.setLastStatus(square.isVisitedByKnight);
                    square.setVisitedByKnight(true);
                    currentScore++; //here we add point to the currentScore.
                    currentLevelScore++; //here we add point to the current Level Score.
                    squaresLeft--;
                    if(Game.threeStepsHistory.size()==3)
                        Game.threeStepsHistory.remove();
                    Game.threeStepsHistory.add(square);
                } else { //visited by knight
                    if (square.isJump) {
                        square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                        square.setLastStatus(square.isVisitedByKnight);
                        square.setVisitedByKnight(true);
                        emptySquare = randomEmptySquare();
                        while (emptySquare.occupied)
                            emptySquare = randomEmptySquare();
                        //currentScore++;
                        //currentLevelScore++;
                        //squaresLeft--;
                        anotherJumpSquare();
                        flag = true;

                    } else{ //visited by knight and not jump square
                        square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                        square.setLastStatus(square.isVisitedByKnight);
                        square.setVisitedByKnight(true);
                        currentScore--;//here we less point from the currentScore.
                        currentLevelScore--; //here we less point from the current level Score.
                    }
                }
            }
            if (flag) { //moving the knight to a new empty square if it stands on jump square.
                emptySquare.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                emptySquare.setLastStatus(emptySquare.isVisitedByKnight);
                if(Game.threeStepsHistory.size()==3)
                    Game.threeStepsHistory.remove();
                Game.threeStepsHistory.add(emptySquare);
                emptySquare.setVisitedByKnight(true);
                movingToSquare(emptySquare);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wowww!!");
                alert.setHeaderText(null);
                alert.setContentText("You jumped to another empty square!");
                alert.showAndWait();
                //  deselectPiece(true);
            } else { //orginal square
                if(currentPiece != null){
                    if(currentPiece.type.equals("Knight"))
                        movingToSquare(square);
                    //  deselectPiece(true);
                    //   dropQueen();
                    //  dropKing();
                }
            }
        }
        else{
            if(isPaused){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Opps");
                alert.setHeaderText(null);
                alert.setContentText("You paused the game, press on play button to continue.");
                alert.showAndWait();
                return;
            }
            Square emptySquare = null;
            boolean flag = false;
            if(square.isBlocked){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Illegal move!!");
                alert.setHeaderText(null);
                alert.setContentText("You can't move to a blocked square!");
                alert.showAndWait();
                return;
            }
            if (currentPiece.type.equals("Knight")) {
                if(square.isQuestionSquare){
                    showQuestionLabel(square.getQuestion());
                    anotherQuestionSquare(square.getQuestion().level);
                    square.setQuestion(null);
                    square.setQuestionSquare(false);
                    //Pause time when showing a question
                    Controller.pauseTimer();
                    Controller.pauseLevel();
                    colorSquare(square);
                }
                if(square.isForget){
                    forgetLast3Steps();
                    movingToSquare(square);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Opps!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Your three last steps were delete!");
                    alert.showAndWait();
                    anotherForgetSquare();
                    square.setVisitedByKnight(true);
                    square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                    squaresLeft--;

                    deselectPiece(true);
                    return;
                }
                if (!square.isVisitedByKnight) {
                    if (square.isJump) {
                        emptySquare = randomEmptySquare();
                        while (emptySquare.occupied)
                            emptySquare = randomEmptySquare();
                        //currentScore++;
                        //currentLevelScore++;
                        //squaresLeft--;
                        anotherJumpSquare();
                        flag = true;

                    }
                    square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                    square.setLastStatus(square.isVisitedByKnight);
                    square.setVisitedByKnight(true);
                    currentScore++; //here we add point to the currentScore.
                    currentLevelScore++; //here we add point to the current Level Score.
                    squaresLeft--;
                    if(Game.threeStepsHistory.size()==3)
                        Game.threeStepsHistory.remove();
                    Game.threeStepsHistory.add(square);
                } else { //visited by knight
                    if (square.isJump) {
                        square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                        square.setLastStatus(square.isVisitedByKnight);
                        square.setVisitedByKnight(true);
                        emptySquare = randomEmptySquare();
                        while (emptySquare.occupied)
                            emptySquare = randomEmptySquare();
                        //currentScore++;
                        //currentLevelScore++;
                        //squaresLeft--;
                        anotherJumpSquare();
                        flag = true;

                    } else{ //visited by knight and not jump square
                        square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                        square.setLastStatus(square.isVisitedByKnight);
                        square.setVisitedByKnight(true);
                        currentScore--;//here we less point from the currentScore.
                        currentLevelScore--; //here we less point from the current level Score.
                    }
                }
            }
            if (flag) { //moving the knight to a new empty square if it stands on jump square.
                emptySquare.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
                emptySquare.setLastStatus(emptySquare.isVisitedByKnight);
                if(Game.threeStepsHistory.size()==3)
                    Game.threeStepsHistory.remove();
                Game.threeStepsHistory.add(emptySquare);
                emptySquare.setVisitedByKnight(true);
                movingToSquare(emptySquare);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wowww!!");
                alert.setHeaderText(null);
                alert.setContentText("You jumped to another empty square!");
                alert.showAndWait();
                deselectPiece(true);
            } else { //orginal square
                if(currentPiece != null){
                    if(currentPiece.type.equals("Knight"))
                        movingToSquare(square);
                    deselectPiece(true);
                    dropQueen();
                }
            }}
    }

    private static void killPiece(Square square) {
        if(currentPiece.possibleMoves!=null){
            if (!currentPiece.possibleMoves.contains(square.name)) return;


            Piece killedPiece = (Piece) square.getChildren().get(0);
            if (killedPiece.type.equals("Knight")){
                setGame(false);
                setIsWin(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Illegal move!!");
                alert.setHeaderText(null);
                alert.setContentText("You can't move to an occupied square!");
                alert.showAndWait();
                return;
            }

            Square initialSquare = (Square) currentPiece.getParent();
            square.getChildren().remove(0);
            square.getChildren().add(currentPiece);
            square.occupied = true;
            initialSquare.getChildren().removeAll();
            initialSquare.occupied = false;
            currentPiece.posX = square.x;
            currentPiece.posY = square.y;
            deselectPiece(true);
        }
    }


    public static class Square extends StackPane {

        int x, y;
        boolean occupied;
        String name;
        boolean isVisitedByKnight;
        boolean isJump; //האם היא משבצת קפיצה

        boolean isBlocked;

        boolean isForget;

        boolean lastStatus;

        boolean isQuestionSquare;

        Question question;

        //ImageView img;




        public Square(int x, int y) {
            this.x = x;
            this.y = y;
            this.occupied = false;
            this.isVisitedByKnight = false;
            this.isJump = false;
            this.isBlocked = false;
            this.isForget = false;
            this.lastStatus = false;
            this.isQuestionSquare = false;
            question = null;
            //this.img = new ImageView();
        }

        @Override
        public String toString() {
            String status;
            if (this.occupied) status = "Occupied";
            else status = "Not occupied";
            //        return "Square" + this.x + this.y + " - " + status;
            return "Square";
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setVisitedByKnight(Boolean isVisited) {
            this.isVisitedByKnight = isVisited;
        }

        public void setJump(boolean isJump) {
            this.isJump = isJump;
        }

        public boolean isBlocked() {
            return isBlocked;
        }

        public void setBlocked(boolean blocked) {
            isBlocked = blocked;
        }

        public boolean isOccupied() {
            return occupied;
        }

        public void setOccupied(boolean occupied) {
            this.occupied = occupied;
        }

        public boolean isForget() {
            return isForget;
        }

        public void setForget(boolean forget) {
            isForget = forget;
        }

        public boolean isLastStatus() {
            return lastStatus;
        }

        public void setLastStatus(boolean lastStatus) {
            this.lastStatus = lastStatus;
        }

        public boolean isQuestionSquare() {
            return isQuestionSquare;
        }

        public void setQuestionSquare(boolean questionSquare) {
            isQuestionSquare = questionSquare;
        }

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

    }

    public static void randomJumpSquares() {
        for (Game.Square square : cb.squares) {
            square.setJump(false);
        }
        int n=0;
        if (currentLevel.equals(GameLevel.BASIC.toString()))
            n=3;
        else if (currentLevel.equals(GameLevel.ADVANCED.toString()))
            n=2;
        int i = 0;
        while (i < n) {
            Square square = randomSquare();
            if((!square.isJump) && (!square.isForget)){
                i++;
                square.setJump(true);
            }
        }
    }


    public static Square randomEmptySquare(){
        boolean flag=false;
        Random rand = new Random(); //instance of random class
        int upperbound = 8;
        //generate random values from 0-7
        int randomX1 = rand.nextInt(upperbound);
        int randomY1 = rand.nextInt(upperbound);
        while (!flag){
            for(Game.Square square : cb.squares){
                if((square.x == randomX1 && square.y == randomY1)&&(!square.isVisitedByKnight)){
                    flag=true;
                    return square;
                }
            }
            randomX1 = rand.nextInt(upperbound);
            randomY1 = rand.nextInt(upperbound);
        }
        return null;
    }

    public static Square randomSquare(){
        //boolean flag=false;
        Random rand = new Random(); //instance of random class
        int upperbound = 8;
        //generate random values from 0-7
        int randomX1 = rand.nextInt(upperbound);
        int randomY1 = rand.nextInt(upperbound);
        // while (!flag){
        for(Game.Square square : cb.squares){
            if((square.x == randomX1 && square.y == randomY1)){
                return square;
            }
        }
        //randomX1 = rand.nextInt(upperbound);
        // randomY1 = rand.nextInt(upperbound);
        //}
        return null;
    }

    public static void randomBlockedSquares(){
        if(currentLevel.equals(GameLevel.ADVANCED_PRO.toString())){
            int i=0;
            while (i<8){
                Square square = randomSquare();
                if((!square.occupied) && (!square.isQuestionSquare) && (!square.isBlocked)){
                    square.setBlocked(true);
                    square.setBackground(new Background(new BackgroundFill(Color.web("#E96060"), CornerRadii.EMPTY, Insets.EMPTY)));
                    i++;
                }
            }
            Game.squaresLeft-=8;
        }
    }

    public static void randomForgetSquares(){
        for (Game.Square square : cb.squares) {
            square.setForget(false);
        }
        int n=0;
        if (currentLevel.equals(GameLevel.INTERMEDIATE.toString()))
            n=3;
        else if (currentLevel.equals(GameLevel.ADVANCED.toString()))
            n=2;
        int i = 0;
        Square square;
        while (i < n) {
            square = randomSquare();
            if((!square.isJump) && (!square.isForget)){
                square.setForget(true);
                i++;
            }
        }
    }

    public static void forgetLast3Steps(){
        Queue<Square> queue = new Queue<Square>() {
            @Override
            public boolean add(Square square) {
                return false;
            }

            @Override
            public boolean offer(Square square) {
                return false;
            }

            @Override
            public Square remove() {
                return null;
            }

            @Override
            public Square poll() {
                return null;
            }

            @Override
            public Square element() {
                return null;
            }

            @Override
            public Square peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Square> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Square> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Square s = null;
        for(int i=0; i<3; i++){
            if(!threeStepsHistory.isEmpty()){
                s = threeStepsHistory.poll();
                if(s != null){
                    if(!s.lastStatus){
                        s.isVisitedByKnight = false;
                        currentScore--;
                        currentLevelScore--;
                        squaresLeft++;
                        if((s.x+s.y)%2==0){
                            s.setBackground(new Background(new BackgroundFill(Color.web("#b1e4b9"), CornerRadii.EMPTY, Insets.EMPTY)));
                        }else{
                            s.setBackground(new Background(new BackgroundFill(Color.web("#70a2a3"), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    } else{
                        currentScore++;
                        currentLevelScore++;
                    }
                    queue.add(s);
                }
            }
            threeStepsHistory.addAll(queue);

        }



    }

    public static void anotherJumpSquare(){
        Square anotherJump = randomSquare();
        while (anotherJump.isForget){
            anotherJump = randomSquare();
        }
        anotherJump.setJump(true);
    }

    public static void anotherForgetSquare(){
        Square anotherForget = randomSquare();
        while (anotherForget.isJump){
            anotherForget = randomSquare();
        }
        anotherForget.setForget(true);
    }

    public static void movingToSquare(Square square){
        Square initialSquare = (Square) currentPiece.getParent();
        if(!square.equals(initialSquare)){
            square.getChildren().add(currentPiece);
            square.occupied = true;
            initialSquare.getChildren().removeAll();
            initialSquare.occupied = false;
            currentPiece.posX = square.x;
            currentPiece.posY = square.y;
        }
    }

    public static void colorSquare(Square square){
        if(!square.isVisitedByKnight){
            if((square.x+square.y)%2==0){
                square.setBackground(new Background(new BackgroundFill(Color.web("#b1e4b9"), CornerRadii.EMPTY, Insets.EMPTY)));
            }else{
                square.setBackground(new Background(new BackgroundFill(Color.web("#70a2a3"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        } else {
            square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    public static void randomThreeQuestions(){
        int i=0;
        JSONQuestionsReader jsonQuestionsRead = new JSONQuestionsReader();
        jsonQuestionsRead.readQuestions("/json/QuestionsFormat.json");
        ArrayList<Question> allQuestions = SysData.getInstance().getQuestions();
        Square square1 = Game.randomSquare();
        while(square1.isQuestionSquare)
            square1 = Game.randomSquare();
        square1.setQuestionSquare(true);
        square1.setQuestion(randomEasyQuestion(allQuestions));
        square1.setBackground(new Background(new BackgroundFill(Color.web("#fafafa"), CornerRadii.EMPTY, Insets.EMPTY)));


        Square square2 = Game.randomSquare();
        while(square2.isQuestionSquare)
            square2 = Game.randomSquare();
        square2.setQuestionSquare(true);
        square2.setQuestion(randomMediumQuestion(allQuestions));
        square2.setBackground(new Background(new BackgroundFill(Color.web("#E9E160"), CornerRadii.EMPTY, Insets.EMPTY)));


        Square square3 = Game.randomSquare();
        while(square3.isQuestionSquare)
            square3 = Game.randomSquare();
        square3.setQuestionSquare(true);
        square3.setQuestion(randomHardQuestion(allQuestions));
        square3.setBackground(new Background(new BackgroundFill(Color.web("#cc1212"), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public static void setQuestionMark(Square square, String theme){
        BackgroundImage myBI= new BackgroundImage(new Image("/images/question.png",32,32,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        square.setBackground(new Background(myBI));
    }

    public static Question randomEasyQuestion(ArrayList<Question> questionsList){
        ArrayList<Question> easyQuestions = new ArrayList<Question>();
        for(Question q : questionsList){
            if(q.getLevel().equals("Easy"))
                easyQuestions.add(q);
        }
        Collections.shuffle(easyQuestions);
        return easyQuestions.get(0);

    }

    public static Question randomMediumQuestion(ArrayList<Question> questionsList){
        ArrayList<Question> mediumQuestions = new ArrayList<Question>();
        for(Question q : questionsList){
            if(q.getLevel().equals("Medium"))
                mediumQuestions.add(q);
        }
        Collections.shuffle(mediumQuestions);
        return mediumQuestions.get(0);
    }

    public static Question randomHardQuestion(ArrayList<Question> questionsList){
        ArrayList<Question> hardQuestions = new ArrayList<Question>();
        for(Question q : questionsList){
            if(q.getLevel().equals("Hard"))
                hardQuestions.add(q);
        }
        Collections.shuffle(hardQuestions);
        return hardQuestions.get(0);
    }

    //Showing question label on the screen
    public static void showQuestionLabel(Question question){
        Dialog dialog = new Dialog();
        dialog.setTitle("Question");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.getDialogPane().setContent(createQuestionForm(question));
        dialog.show();

    }

    public static Node createQuestionForm(Question question){
        Label labelFirst= new Label(question.content);
        Label labelResponse= new Label();

        Button button= new Button("Submit");


        RadioButton radio1, radio2, radio3, radio4;
        radio1=new RadioButton(question.answers.get(0).getContent());
        radio2= new RadioButton(question.answers.get(1).getContent());
        radio3= new RadioButton(question.answers.get(2).getContent());
        radio4= new RadioButton(question.answers.get(3).getContent());

        ToggleGroup question1= new ToggleGroup();

        radio1.setToggleGroup(question1);
        radio2.setToggleGroup(question1);
        radio3.setToggleGroup(question1);
        radio4.setToggleGroup(question1);

        button.setDisable(true);

        radio1.setOnAction(e -> button.setDisable(false) );
        radio2.setOnAction(e -> button.setDisable(false) );
        radio3.setOnAction(e -> button.setDisable(false) );
        radio4.setOnAction(e -> button.setDisable(false) );

        RadioButton correctRadio;
        if(question.answers.get(0).isCorrect())
            correctRadio = radio1;
        else if(question.answers.get(1).isCorrect())
            correctRadio = radio2;
        else if(question.answers.get(2).isCorrect())
            correctRadio = radio3;
        else correctRadio = radio4;


        button.setOnAction(e ->
                {
                    if (correctRadio.isSelected())
                    {
                        labelResponse.setText("Correct answer");
                        button.setDisable(true);
                        //Adding to the score according to the difficulty level.
                        if(question.getLevel().equals(DifficultyLevel.EASY.toString())){
                            currentScore++;
                            currentLevelScore++;
                        } else if(question.getLevel().equals(DifficultyLevel.MEDIUM.toString())){
                            currentScore+=2;
                            currentLevelScore+=2;
                        } else {
                            currentScore+=3;
                            currentLevelScore+=3;
                        }
                        correctRadio.setSelected(true);

                    }

                    else
                    {
                        labelResponse.setText("Wrong answer");
                        button.setDisable(true);
                        //Less from the score according to the difficulty level.
                        if(question.getLevel().equals(DifficultyLevel.EASY.toString())){
                            currentScore-=2;
                            currentLevelScore-=2;
                        } else if(question.getLevel().equals(DifficultyLevel.MEDIUM.toString())){
                            currentScore-=3;
                            currentLevelScore-=3;
                        } else {
                            currentScore-=4;
                            currentLevelScore-=4;
                        }

                    }
                    radio1.setDisable(true);
                    radio2.setDisable(true);
                    radio3.setDisable(true);
                    radio4.setDisable(true);
                    Controller.countDownTimeLine.play();
                    Controller.countDownTimeLine1.play();
                }

        );

        GridPane gridPane = new GridPane();

        gridPane.add(labelFirst, 0, 0);
        gridPane.add(radio1, 0, 1);
        gridPane.add(radio2, 0, 2);
        gridPane.add(radio3, 0, 3);
        gridPane.add(radio4, 0, 4);
        gridPane.add(button, 0, 5);
        gridPane.add(labelResponse, 0, 6);

        return gridPane;
    }

    public static void anotherQuestionSquare(DifficultyLevel level){
        Square anotherQuestion = randomSquare();
        while (anotherQuestion.isQuestionSquare || anotherQuestion.isBlocked){
            anotherQuestion = randomSquare();
        }
        anotherQuestion.setQuestionSquare(true);
        JSONQuestionsReader jsonQuestionsRead = new JSONQuestionsReader();
        jsonQuestionsRead.readQuestions("/json/QuestionsFormat.json");
        ArrayList<Question> allQuestions = SysData.getInstance().getQuestions();

        if(level.equals(DifficultyLevel.EASY)) {
            anotherQuestion.setQuestion(randomEasyQuestion(allQuestions));
            anotherQuestion.setBackground(new Background(new BackgroundFill(Color.web("#fafafa"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(level.equals(DifficultyLevel.MEDIUM)){
            anotherQuestion.setQuestion(randomMediumQuestion(allQuestions));
            anotherQuestion.setBackground(new Background(new BackgroundFill(Color.web("#E9E160"), CornerRadii.EMPTY, Insets.EMPTY)));

        }
        else {
            anotherQuestion.setQuestion(randomHardQuestion(allQuestions));
            anotherQuestion.setBackground(new Background(new BackgroundFill(Color.web("#cc1212"), CornerRadii.EMPTY, Insets.EMPTY)));

        }


    }

    public static void emptyAllTheSquares(){
        for(Game.Square square : cb.squares){
            square.setVisitedByKnight(false);
            square.setJump(false);
            square.setForget(false);
            if(!square.isQuestionSquare)
                ChessBoard.setTheme(square, SysData.getInstance().getTheme(), square.x, square.y);
        }
    }

    public static void emptyAllTheQuestionsSquares(){
        for(Game.Square square : cb.squares){
            square.setQuestionSquare(false);
            square.setQuestion(null);
            ChessBoard.setTheme(square, SysData.getInstance().getTheme(), square.x, square.y);
        }
    }

    public static void removeShadow(Square square){
        square.setEffect(null);
        square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public static void replaceTheKnight(){
        Game.Square mySquare=null;
        Piece myPiece;
        for(Game.Square square : cb.squares){
            if ((square.x==0 && square.y ==0)){
                mySquare = square;
            }
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("Knight")){
                        currentPiece = myPiece;
                    }
                }
            }
        }
        assert mySquare != null;
        movingToSquare(mySquare);
        mySquare.setVisitedByKnight(true);
        mySquare.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static void replaceTheQueen(){
        Game.Square mySquare=null;
        Piece myPiece;
        for(Game.Square square : cb.squares){
            if ((square.x==7 && square.y ==0)){
                mySquare = square;
            }
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("Queen")){
                        currentPiece = myPiece;
                    }
                }
            }
        }
        assert mySquare != null;
        movingToSquare(mySquare);
        //deselectPiece(true);
    }

    public static Piece getTheQueen(){
        Piece myPiece = null;
        for(Game.Square square : cb.squares){
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("Queen")){
                        return myPiece;
                    }
                }
            }
        }
        return myPiece;
    }

    public static void replaceTheKing(){
        Game.Square mySquare=null;
        Piece myPiece;
        for(Game.Square square : cb.squares){
            if ((square.x==7 && square.y ==0)){
                mySquare = square;
            }
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("King")){
                        currentPiece = myPiece;
                    }
                }
            }
        }
        assert mySquare != null;
        movingToSquare(mySquare);
    }

    public static void deleteTheQueen(){
        Game.Square mySquare=null;
        Piece myPiece;
        for(Game.Square square : cb.squares){
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("Queen")){
                        Square initialSquare = (Square) myPiece.getParent();
                        square.getChildren().remove(0);
                        //square.getChildren().add(currentPiece);
                        //square.occupied = true;
                        initialSquare.getChildren().removeAll();
                        initialSquare.occupied = false;
                        //currentPiece.posX = square.x;
                        //currentPiece.posY = square.y;
                    }
                }
            }
        }
    }

    public static Square getCurrentSquare(){ //The square where the current piece stand
        Piece myPiece;
        for(Game.Square square : cb.squares){
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("Knight")){
                        return square;
                    }
                }
            }
        }
        return null;
    }

    public static void dropQueen(){
        if(currentLevel.equals("Basic")){
            currentPiece = getTheQueen();
            Piece queen = (Queen) currentPiece;
            Square square = ((Queen) queen).bestMove(getCurrentSquare().x, getCurrentSquare().y);
            if(square.occupied){
                killPiece(square);
            }
            else {
                movingToSquare(square);
                deselectPiece(true);
            }
        } else if(currentLevel.equals("Intermediate")){
            currentPiece = getTheQueen();
            Piece queen = (Queen) currentPiece;
            Square square = ((Queen) queen).bestMove(getCurrentSquare().x, getCurrentSquare().y);
            if(square.occupied){
                killPiece(square);
            }
            else {
                movingToSquare(square);
                deselectPiece(true);
            }
        }
    }
    //last update
    public static void dropKing1 (){
        if(currentLevel.equals("Advanced")){
            Piece king = getTheKing();
            Square square = ((King) king).bestMove( getCurrentSquare().x, getCurrentSquare().y);
            if(square.occupied){
                killPiece2(square);
            }
            else {
                movingKToSquare(square);
            }
        } else if(currentLevel.equals("Advanced Pro")){
            Piece king = getTheKing();
            Square square = ((King) king).bestMove( getCurrentSquare().x, getCurrentSquare().y);
            if(square.occupied){
                killPiece2(square);
            }
            else {
                movingKToSquare(square);

            }
        }
    }
    public static void movingKToSquare(Square square){
        Square initialSquare = (Square) getTheKing().getParent();
        if(!square.equals(initialSquare)){
            square.getChildren().add(getTheKing());
            square.occupied = true;
            initialSquare.getChildren().removeAll();
            initialSquare.occupied = false;
            getTheKing().posX = square.x;
            getTheKing().posY = square.y;
        }
    }
    private static void killPiece2(Square square) {
        Piece killedPiece = (Piece) square.getChildren().get(0);
        if (killedPiece.type.equals("Knight")){
            setGame(false);
            setIsWin(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Illegal move!!");
            alert.setHeaderText(null);
            alert.setContentText("You can't move to an occupied square!");
            alert.showAndWait();
            return;
        }

        Square initialSquare = (Square) getTheKnight().getParent();
        square.getChildren().remove(0);
        square.getChildren().add(getTheKing());
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;

    }

    public static Piece getTheKing(){
        Piece myPiece = null;
        for(Game.Square square : cb.squares){
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("King")){
                        return myPiece;
                    }
                }
            }
        }
        return myPiece;
    }
    public static Piece getTheKnight(){
        Piece myPiece = null;
        for(Game.Square square : cb.squares){
            if (!square.getChildren().isEmpty()){
                myPiece = (Piece) square.getChildren().get(0);
                if(myPiece != null){
                    if (myPiece.type.equals("Knight")){
                        return myPiece;
                    }
                }
            }
        }
        return myPiece;
    }



}
