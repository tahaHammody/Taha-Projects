package model;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
    public Queen(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Queen";
        setImage();
    }

   /* @Override
    public void addEventHandler(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getAllPossibleMoves();
            }
        });
    }*/


    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        String name;

        this.possibleMoves = new ArrayList<>();

        for(int i=x-1; i>=0; i--){
            name = "Square" + i + y;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x+1; i<8; i++){
            name = "Square" + i + y;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int j=y-1; j>=0; j--){
            name = "Square" + x + j;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int j=y+1; j<8; j++){
            name = "Square" + x + j;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x-1, j=y+1; i>=0 && j<8; i--, j++){
            name = "Square" + i + j;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x+1, j=y+1; i<8 && j<8; i++, j++){
            name = "Square" + i + j;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x+1, j=y-1; i<8 && j>=0; i++, j--){
            name = "Square" + i + j;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }

        for(int i=x-1, j=y-1; i>=0 && j>=0; i--, j--){
            name = "Square" + i + j;
            //if(getSquareByName(name).occupied && getPieceByName(name).getColor().equals(Game.currentPlayer)) break;

            possibleMoves.add(name);

            //if(getSquareByName(name).occupied && !getPieceByName(name).getColor().equals(Game.currentPlayer)) break;
        }


    }

    public Game.Square bestMove(int kx,int ky){
        getAllPossibleMoves();
        double d = 11;
        double distance=d;
        Game.Square bestSquare = null;
        for (int i = 0; i <this.possibleMoves.size() ; i++){
            String move= this.possibleMoves.get(i);
            Game.Square square = getSquareByName(move);
            if(square.occupied)
                return square;
            //d=(Math.abs(square.x-kx)+ Math.abs(square.y-ky));
            if(kx==square.x){
                d = Math.abs(square.y - ky);
                if(d<distance){
                    if(square.occupied)
                        return square;
                    distance=d;
                    bestSquare = square;
                }
            }
            else if(ky==square.y){
                d=Math.abs(square.x-kx);
                if(d<distance){
                    if(square.occupied)
                        return square;
                    distance=d;
                    bestSquare = square;
                }
            } else {
                double x = Math.abs(square.x-kx);
                double y = Math.abs(square.y - ky);
                d = Math.sqrt((x*x)+(y*y));
                if(d<distance){
                    if(square.occupied)
                        return square;
                    distance=d;
                    bestSquare = square;
                }
            }

        }
        return bestSquare;
    }
}
