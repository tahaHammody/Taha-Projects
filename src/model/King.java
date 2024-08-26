package model;

import control.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import utils.GameLevel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class King extends Piece {
    private int  partoflevel=6;
    private int  kposX;
    private int  kposY;

    private Controller c;

    public int getKposX() {
        return kposX;
    }

    public void setKposX(int kposX) {
        this.kposX = kposX;
    }

    public int getKposY() {
        return kposY;
    }

    public void setKposY(int kposY) {
        this.kposY = kposY;
    }

    public King(String color, int posX, int posY) {
        super(color, posX, posY);
        ;
        this.type = "King";
        setImage();
    }

	/*@Override
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
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
        if(x==0&&y==0){
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x+7) + (y));
            moves.add("Square" + (x) + (y+7));

        }
        if(x==7&&y==0) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x-7) + (y));
            moves.add("Square" + (x) + (y+7));
        }
        if(x==7&&y==7) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x) + (y-7));
            moves.add("Square" + (x-7) + (y));
        }
        if(x==0&&y==7) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x) + (y-7));
            moves.add("Square" + (x+7) + (y));
        }
        if (x==0&&0<y&&y<7) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x+7) + (y));
        }
        if (x==7&&0<y&&y<7) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x-7) + (y));
        }
        if (y==0&&0<x&&x<7) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x) + (y+7));
        }
        if (y==7&&0<x&&x<7) {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
            moves.add("Square" + (x) + (y-7));

        }else {
            moves.add("Square" + (x) + (y - 1));
            moves.add("Square" + (x + 1) + (y - 1));
            moves.add("Square" + (x + 1) + (y));
            moves.add("Square" + (x + 1) + (y + 1));
            moves.add("Square" + (x) + (y + 1));
            moves.add("Square" + (x - 1) + (y + 1));
            moves.add("Square" + (x - 1) + (y));
            moves.add("Square" + (x - 1) + (y - 1));
        }

        for (String move : moves) {
            if (getSquareByName(move) != null) {
//                if (getSquareByName(move).occupied/* && getPieceByName(move).getColor().equals(Game.currentPlayer)*/)
//                    continue;

                possibleMoves.add(move);


            }}}
    public Game.Square bestMove(int kx,int ky){
        getAllPossibleMoves();

        double d = 11;
        double distance=d;
        Game.Square bestSquare = null;

        for (int i = 0; i <this.possibleMoves.size() ; i++){
            String move= this.possibleMoves.get(i);
            Game.Square square = getSquareByName(move);
            if(square.occupied==true) {

                return square;
            }
            if(kx==square.x){
                d = Math.abs(square.y - ky);
                if(d<distance){
                    if(square.occupied) {

                        return square;
                    }
                    distance=d;
                    bestSquare = square;
                }
            }
            else if(ky==square.y){
                d=Math.abs(square.x-kx);
                if(d<distance){
                    if(square.occupied) {
                        return square;
                    }
                    distance=d;
                    bestSquare = square;
                }
            } else {
                double x = Math.abs(square.x-kx);
                double y = Math.abs(square.y - ky);
                d = Math.sqrt((x*x)+(y*y));
                if(d<distance){
                    if(square.occupied) {
                        return square;
                    }
                    distance=d;
                    bestSquare = square;
                }
            }

        }
        return bestSquare;
    }

}
  
