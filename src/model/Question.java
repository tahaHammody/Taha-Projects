package model;

import utils.DifficultyLevel;

import java.util.ArrayList;
import java.util.Objects;

public class Question {
    private int question;
    public String content;
    public ArrayList<Answer> answers ;
    public  String correct_ans;
    public DifficultyLevel level;
    public String team ;


    public Question(int i) {
        super();
        this.question = i;
    }

    public Question(String questionContent, ArrayList<Answer> answers, String correctAnswer, DifficultyLevel level, String team) {
        this.content = questionContent;
        this.answers = answers;
        this.correct_ans = correctAnswer;
        this.level = level;
        this.team = team;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestion() {
        return question;
    }
    public void setQuestion(int question) {
        this.question = question;
    }


    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    public String getCorrect_ans() {
        return correct_ans;
    }
    public void setCorrect_ans(String correct_ans) {
        this.correct_ans = correct_ans;
    }
    public String getLevel() {
        return level.toString();
    }
    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }


    @Override
    public int hashCode() {
        return Objects.hash(answers, correct_ans, level, question, team);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        return Objects.equals(answers, other.answers) && Objects.equals(correct_ans, other.correct_ans)
                && Objects.equals(level, other.level) && question == other.question && Objects.equals(team, other.team);
    }

    @Override
    public String toString() {
        return "Question [question=" + question + ", answers=" + answers + ", correct_ans=" + correct_ans + ", level="
                + level + ", team=" + team + "]";
    }


}


