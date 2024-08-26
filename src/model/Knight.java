package model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import utils.GameLevel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Knight extends Piece {
    public Knight(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Knight";
        setImage();
        addEventHandler();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
         if(x==0&&y==0){
        moves.add("Square" + (x+2) + (y+1));
        moves.add("Square" + (x+2) + (y-1));
        moves.add("Square" + (x+1) + (y+2));
        moves.add("Square" + (x-1) + (y+2));
        moves.add("Square" + (x-2) + (y+1));
        moves.add("Square" + (x-2) + (y-1));
        moves.add("Square" + (x+1) + (y-2));
        moves.add("Square" + (x-1) + (y-2));
        moves.add("Square" + (x+7) + (y));
    	  moves.add("Square" + (x) + (y+7));
         
      }
         if(x==7&&y==0) {
        	 moves.add("Square" + (x+2) + (y+1));
             moves.add("Square" + (x+2) + (y-1));
             moves.add("Square" + (x+1) + (y+2));
             moves.add("Square" + (x-1) + (y+2));
             moves.add("Square" + (x-2) + (y+1));
             moves.add("Square" + (x-2) + (y-1));
             moves.add("Square" + (x+1) + (y-2));
             moves.add("Square" + (x-1) + (y-2));
             moves.add("Square" + (x-7) + (y));
             moves.add("Square" + (x) + (y+7));
         }
         if(x==7&&y==7) {
        	 moves.add("Square" + (x+2) + (y+1));
             moves.add("Square" + (x+2) + (y-1));
             moves.add("Square" + (x+1) + (y+2));
             moves.add("Square" + (x-1) + (y+2));
             moves.add("Square" + (x-2) + (y+1));
             moves.add("Square" + (x-2) + (y-1));
             moves.add("Square" + (x+1) + (y-2));
             moves.add("Square" + (x-1) + (y-2));
             moves.add("Square" + (x) + (y-7));
             moves.add("Square" + (x-7) + (y));
         }
         if(x==0&&y==7) {
        	 moves.add("Square" + (x+2) + (y+1));
             moves.add("Square" + (x+2) + (y-1));
             moves.add("Square" + (x+1) + (y+2));
             moves.add("Square" + (x-1) + (y+2));
             moves.add("Square" + (x-2) + (y+1));
             moves.add("Square" + (x-2) + (y-1));
             moves.add("Square" + (x+1) + (y-2));
             moves.add("Square" + (x-1) + (y-2));
             moves.add("Square" + (x) + (y-7));
             moves.add("Square" + (x+7) + (y));
         }
        if (x==0&&0<y&&y<7) {
        	 moves.add("Square" + (x+2) + (y+1));
             moves.add("Square" + (x+2) + (y-1));
             moves.add("Square" + (x+1) + (y+2));
             moves.add("Square" + (x-1) + (y+2));
             moves.add("Square" + (x-2) + (y+1));
             moves.add("Square" + (x-2) + (y-1));
             moves.add("Square" + (x+1) + (y-2));
             moves.add("Square" + (x-1) + (y-2));
             moves.add("Square" + (x+7) + (y));
        }
        if (x==7&&0<y&&y<7) {
        	 moves.add("Square" + (x+2) + (y+1));
             moves.add("Square" + (x+2) + (y-1));
             moves.add("Square" + (x+1) + (y+2));
             moves.add("Square" + (x-1) + (y+2));
             moves.add("Square" + (x-2) + (y+1));
             moves.add("Square" + (x-2) + (y-1));
             moves.add("Square" + (x+1) + (y-2));
             moves.add("Square" + (x-1) + (y-2));
             moves.add("Square" + (x-7) + (y));
        }
       if (y==0&&0<x&&x<7) {
    	   moves.add("Square" + (x+2) + (y+1));
           moves.add("Square" + (x+2) + (y-1));
           moves.add("Square" + (x+1) + (y+2));
           moves.add("Square" + (x-1) + (y+2));
           moves.add("Square" + (x-2) + (y+1));
           moves.add("Square" + (x-2) + (y-1));
           moves.add("Square" + (x+1) + (y-2));
           moves.add("Square" + (x-1) + (y-2));
           moves.add("Square" + (x) + (y+7));
        }
       if (y==7&&0<x&&x<7) {
    	   moves.add("Square" + (x+2) + (y+1));
           moves.add("Square" + (x+2) + (y-1));
           moves.add("Square" + (x+1) + (y+2));
           moves.add("Square" + (x-1) + (y+2));
           moves.add("Square" + (x-2) + (y+1));
           moves.add("Square" + (x-2) + (y-1));
           moves.add("Square" + (x+1) + (y-2));
           moves.add("Square" + (x-1) + (y-2));
           moves.add("Square" + (x) + (y-7));

                }else {
                	moves.add("Square" + (x+2) + (y+1));
                    moves.add("Square" + (x+2) + (y-1));
                    moves.add("Square" + (x+1) + (y+2));
                    moves.add("Square" + (x-1) + (y+2));
                    moves.add("Square" + (x-2) + (y+1));
                    moves.add("Square" + (x-2) + (y-1));
                    moves.add("Square" + (x+1) + (y-2));
                    moves.add("Square" + (x-1) + (y-2));
                }
       
        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;

                possibleMoves.add(move);

            }
        }


    }

    @Override
    public void getAllPossibleMovesSecondLevel() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
        if(x==0&&y==0){
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x+7) + (y));
            moves.add("Square" + (x) + (y+7));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (x+7) + (y));
            moves.add("Square" + (x) + (y+7));

        }
        if(x==7&&y==0) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x-7) + (y));
            moves.add("Square" + (x) + (y+7));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y-7) + (x));
            moves.add("Square" + (y) + (x+7));

        }
        if(x==7&&y==7) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x) + (y-7));
            moves.add("Square" + (x-7) + (y));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y) + (x-7));
            moves.add("Square" + (y-7) + (x));
        }
        if(x==0&&y==7) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x) + (y-7));
            moves.add("Square" + (x+7) + (y));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y) + (x-7));
            moves.add("Square" + (y+7) + (x));

        }
        if (x==0&&0<y&&y<7) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x+7) + (y));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y+7) + (x));
        }
        if (x==7&&0<y&&y<7) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x-7) + (y));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y-7) + (x));
        }
        if (y==0&&0<x&&x<7) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x) + (y+7));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y) + (x+7));

        }
        if (y==7&&0<x&&x<7) {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            moves.add("Square" + (x) + (y-7));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
            moves.add("Square" + (y) + (x-7));

        }else {
            moves.add("Square" + (x+2) + (y+1));
            moves.add("Square" + (x+2) + (y-1));
            moves.add("Square" + (x+1) + (y+2));
            moves.add("Square" + (x-1) + (y+2));
            moves.add("Square" + (x-2) + (y+1));
            moves.add("Square" + (x-2) + (y-1));
            moves.add("Square" + (x+1) + (y-2));
            moves.add("Square" + (x-1) + (y-2));
            //שתיים באלכסון ואחת ישיר
            moves.add("Square" + (y+2) + (x+3));//
            moves.add("Square" + (y-2) + (x-3));//
            moves.add("Square" + (y+3) + (x+2));//
            moves.add("Square" + (y+3) + (x-2));//
            moves.add("Square" + (y+2) + (x-3));//
            moves.add("Square" + (y-3) + (x+2));//
            moves.add("Square" + (y-3) + (x-2));//
            moves.add("Square" + (y-2) + (x+3));//
        }

        for(String move : moves){
            if(getSquareByName(move) != null){
                if(getSquareByName(move).occupied && getPieceByName(move).getColor().equals(Game.currentPlayer)) continue;
                possibleMoves.add(move);

            }
        }


    }


    public void addEventHandler(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(Game.currentLevel.equals(GameLevel.INTERMEDIATE))
                    getAllPossibleMovesSecondLevel();
                else getAllPossibleMoves();
            }
        });
    }
}
