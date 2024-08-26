package utils;

import control.HomepageController;
import model.Answer;
import model.Question;
import model.SysData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.DifficultyLevel;

import java.io.*;
import java.util.ArrayList;

public class JSONQuestionsReader {
    SysData sysData = SysData.getInstance();

    public void readQuestions(String filename) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        //Create a JSONArray
        JSONArray questionsList = null;
        //Create an arraylist of Questions
        ArrayList<Question> questions = new ArrayList<Question>();
        try (InputStreamReader reader = new InputStreamReader(HomepageController.class.getResourceAsStream(filename))) {


            //Read JSON file
            Object obj = jsonParser.parse(reader);
            //Create a JSON object
            JSONObject mainJsonObject = (JSONObject) obj;

            //Load the questions into a JSON array
            questionsList = (JSONArray) mainJsonObject.get("questions");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        /*Check if db.json exits in the project*/
        File f = new File("db.json");
        if(f.exists())
        {
            InputStream is = null;
            try {
                is = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            JSONObject dbJsonObject = null;
            try {
                dbJsonObject = (JSONObject) jsonParser.parse(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray dbArray = (JSONArray) dbJsonObject.get("questions");

            questionsList = dbArray;
        }


        //Iterate over all the questions
        for (int i = 0; i < questionsList.size(); i++) {
            Question Q = new Question(i + 1);

            JSONObject questionObj = (JSONObject) questionsList.get(i);

            String correct_ans = (String) questionObj.get("correct_ans");
            Q.setCorrect_ans(correct_ans);

            ArrayList<String> answers = (ArrayList<String>) questionObj.get("answers");
            ArrayList<Answer> currentAnswersList = new ArrayList<>();
            for (int j = 0; j < answers.size(); j++) {
                Answer A = new Answer(answers.get(j), false);
                currentAnswersList.add(A);
                //Check if the current answer is correct, then set its value to true
                if (currentAnswersList.get(j).getContent().equals(correct_ans)) {
                    currentAnswersList.get(j).setCorrect(true);
                }
            }
            Q.setAnswers(currentAnswersList);

            String content = (String) questionObj.get("question");
            Q.setContent(content);

            String team = (String) questionObj.get("team");
            Q.setTeam(team);


            DifficultyLevel level = DifficultyLevel.convertor(questionObj.get("level").toString());
            Q.setLevel(level);


            questions.add(Q);
        }
        this.sysData.setQuestions(questions);

    }
}
