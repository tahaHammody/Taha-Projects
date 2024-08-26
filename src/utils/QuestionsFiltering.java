package utils;

import model.Question;
import model.SysData;

import java.util.ArrayList;

public class QuestionsFiltering {

    SysData sysData = SysData.getInstance();

    JSONQuestionsReader jsonQuestionRead = new JSONQuestionsReader();

    public static ArrayList<Question> easyQuestions = new ArrayList<Question>();
    public static ArrayList<Question> mediumQuestions = new ArrayList<Question>();
    public static ArrayList<Question> hardQuestions = new ArrayList<Question>();

    public void filterQuestions(){
        jsonQuestionRead.readQuestions("/json/QuestionsFormat.json");
        ArrayList<Question> allQuestions = sysData.getQuestions();

        for (Question q: allQuestions) {
            if(q.getLevel().equals("Easy")){
                easyQuestions.add(q);
            }
            else if(q.getLevel().equals("Medium")){
                mediumQuestions.add(q);
            }
            else{
                hardQuestions.add(q);
            }
        }
    }
}
