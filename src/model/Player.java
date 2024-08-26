package model;


import java.util.Objects;

public class Player{

    //Unique identifier
    private String nickname;

    private long highestScore;

    private int rank;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public Player(String nickname, int highestScore) {
        this.nickname = nickname;
        this.highestScore = highestScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public long getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(long highestScore) {
        this.highestScore = highestScore;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return getHighestScore() == player.getHighestScore() && Objects.equals(getNickname(), player.getNickname());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getNickname(), getHighestScore());
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", highestScore=" + highestScore +
                '}';
    }
}
