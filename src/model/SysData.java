package model;

import java.util.ArrayList;


public class SysData {

    private static SysData instance;
    private static final int HIGHSCORES_AMOUNT = 10;

    //Store all questions in an arraylist
    private ArrayList<Question> allQuestions = new ArrayList<>();

    //Store all players in an arraylist
    private ArrayList<Player> allPlayers = new ArrayList<>();

    /*Store current logged-in player*/
    public static Player loggedInPlayer = new Player("");

    private  String theme = "Coral";

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    public Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public void setLoggedInPlayer(Player loggedInPlayer) {
        this.loggedInPlayer = loggedInPlayer;
    }

    //Singleton
    public static SysData getInstance() {
        if (instance == null) {
            instance = new SysData();
        }
        return instance;
    }

    public ArrayList<Question> getQuestions() {
        return this.allQuestions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.allQuestions.clear();
        this.allQuestions.addAll(questions);
    }

    public ArrayList<Player> getPlayers() {
        return this.allPlayers;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.allPlayers.clear();
        this.allPlayers.addAll(players);
    }
     public Boolean setPlayersT(ArrayList<Player> players) {
        this.allPlayers.clear();
        return this.allPlayers.addAll(players);
    }
     public Boolean setQuestionsT(ArrayList<Question> questions) {
        this.allQuestions.clear();
        return this.allQuestions.addAll(questions);
    }
}













