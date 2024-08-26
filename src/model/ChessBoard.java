package model;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;


public class ChessBoard {
    GridPane chessBoard;
    String theme;
    public static ArrayList<Game.Square> squares = new ArrayList<>();

    public ChessBoard(GridPane chessBoard, String theme){
        this.chessBoard = chessBoard;
        this.theme = theme;

        makeBoard(this.chessBoard, theme);
    }


    private void makeBoard(GridPane chessBoard, String theme){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Game.Square square = new Game.Square(i,j);
                square.setName("Square" + i + j);
                square.setPrefHeight(100);
                square.setPrefWidth(100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j);
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
        addKnightPiece();
        addQueenPiece();

    }

    public static void setTheme(Game.Square square, String theme, int i, int j){
        Color color1 = Color.web("#ffffff00");
        Color color2 = Color.web("#ffffff00");

        switch (theme) {
            case "Coral" -> {
                color1 = Color.web("#b1e4b9");
                color2 = Color.web("#70a2a3");
            }
            case "Dusk" -> {
                color1 = Color.web("#cbb7ae");
                color2 = Color.web("#716677");
            }
            case "Wheat" -> {
                color1 = Color.web("#eaefce");
                color2 = Color.web("#bbbe65");
            }
            case "Marine" -> {
                color1 = Color.web("#9dacff");
                color2 = Color.web("#6f74d2");
            }
            case "Emerald" -> {
                color1 = Color.web("#adbd90");
                color2 = Color.web("#6e8f72");
            }
            case "Sandcastle" -> {
                color1 = Color.web("#e4c16f");
                color2 = Color.web("#b88b4a");
            }
        }

        if((i+j)%2==0){
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            square.setBackground(new Background(new BackgroundFill(color2, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    private static void addPiece(Game.Square square, Piece piece){
        square.getChildren().add(piece);
        square.occupied = true;
    }

    public static void addKnightPiece(){
        for(Game.Square square : squares){
            if(square.occupied) continue;

            //Draw the knight piece
            if(square.y == 0 && square.x == 0)
            {
                addPiece(square, new Knight("white", square.x, square.y));
                square.setVisitedByKnight(true);
                square.setBackground(new Background(new BackgroundFill(Color.web("#A2E960"), CornerRadii.EMPTY, Insets.EMPTY)));
            }

        }
    }

    public static void addQueenPiece(){
        for(Game.Square square : squares){
            if(square.occupied) continue;

            //Draw the Queen piece
            if(square.y == 0 && square.x == 7)
            {
                addPiece(square, new Queen("black", square.x, square.y));
            }

        }
    }

    public static void addKingPiece(){
        for(Game.Square square : squares){
            if(square.occupied) continue;
            //Draw the King piece
            if(square.y == 0 && square.x == 7)
            {
                addPiece(square, new King("black", square.x, square.y));
            }


        }
    }




}
