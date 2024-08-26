package control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import utils.DifficultyLevel;
import utils.JSONQuestionsReader;
import utils.JSONQuestionsWriter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddQuestionController implements Initializable {


    @FXML
    private Button backBtn;

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea answer1;

    @FXML
    private TextArea answer2;

    @FXML
    private TextArea answer3;

    @FXML
    private TextArea answer4;

    @FXML
    private TextArea content;

    @FXML
    private RadioButton radioBtn1;

    @FXML
    private RadioButton radioBtn2;

    @FXML
    private RadioButton radioBtn3;

    @FXML
    private RadioButton radioBtn4;

    @FXML
    private ToggleGroup toggleGroup;


    @FXML
    private Label msgLabel;

    SysData sysData = SysData.getInstance();


    @FXML
    void cancel(ActionEvent event) {
        resetFields();
    }

    @FXML
    void goBack(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(QuestionsController.class.getResource("/view/questions.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void saveQuestion(ActionEvent event) {
        /*Store all the user inputs in local variables*/
        String questionContent = content.getText();
        String answerContent1 = answer1.getText();
        String answerContent2 = answer2.getText();
        String answerContent3 = answer3.getText();
        String answerContent4 = answer4.getText();
        RadioButton correctAnswerBtn = (RadioButton) toggleGroup.getSelectedToggle(); //Temp
        String levelStr = String.valueOf(comboBox.getSelectionModel().getSelectedIndex()); //Temp

        //Create a new jsonRead object
        JSONQuestionsReader jsonQuestionRead = new JSONQuestionsReader();
        //Read all the stored questions in the JSON file
        jsonQuestionRead.readQuestions("/json/QuestionsFormat.json");
        ArrayList<Question> allQuestions = sysData.getQuestions();

        //Check if one or more of the fields are empty
        if(questionContent.isEmpty() || answerContent1.isEmpty()
        || answerContent2.isEmpty() || toggleGroup.getSelectedToggle() == null
        || levelStr.isEmpty()){
            msgLabel.setText("One or more fields are empty!");

        }
        else {

            //Check if there are duplicates in the answers
            Boolean answerDuplicates = false;
            ArrayList answersContent = new ArrayList<>();
            answersContent.add(answerContent1);
            answersContent.add(answerContent2);
            answersContent.add(answerContent3);
            answersContent.add(answerContent4);
            for (int i = 0; i < answersContent.size(); i++) {
                for (int j = i + 1 ; j < answersContent.size(); j++) {
                    if (!answersContent.get(i).equals("") && answersContent.get(i).equals(answersContent.get(j))) {
                        answerDuplicates = true;
                        break;
                    }
                }
            }
            if(answerDuplicates){
                msgLabel.setText("Two or more answers are duplicates!");
            }

            //Check if the question already exists
            Boolean questionExists = false;
            for (Question q: allQuestions) {
                if(q.getContent().equals(questionContent)){
                    questionExists = true;
                }
            }
            if(questionExists){
                msgLabel.setText("This question already exists! Change its content.");
            }

            //Check if the question doesn't exist and all the answers are unique
            if(!questionExists && !answerDuplicates)
            {
                //Find the correct answer
                String correctAnswer = null;
                int correctAnswerNumber = 0;
                if (correctAnswerBtn.equals(radioBtn1)) {
                    correctAnswer = answerContent1;
                    correctAnswerNumber = 1;
                } else if (correctAnswerBtn.equals(radioBtn2)) {
                    correctAnswer = answerContent2;
                    correctAnswerNumber = 2;

                } else if (correctAnswerBtn.equals(radioBtn3)) {
                    correctAnswer = answerContent3;
                    correctAnswerNumber = 3;

                } else if (correctAnswerBtn.equals(radioBtn4)) {
                    correctAnswer = answerContent4;
                    correctAnswerNumber = 4;
                }

                //Diffuclty Level
            DifficultyLevel level = DifficultyLevel.convertor(levelStr);


                //Create an arraylist of answers
                ArrayList<Answer> answers = new ArrayList<Answer>();
                if (correctAnswerNumber == 1) {
                    Answer a1 = new Answer(answerContent1, true);
                    answers.add(a1);
                } else {
                    Answer a1 = new Answer(answerContent1, false);
                    answers.add(a1);
                }

                if (correctAnswerNumber == 2) {
                    Answer a2 = new Answer(answerContent2, true);
                    answers.add(a2);
                } else {
                    Answer a2 = new Answer(answerContent2, false);
                    answers.add(a2);
                }

                if (correctAnswerNumber == 3) {
                    Answer a3 = new Answer(answerContent3, true);
                    answers.add(a3);
                } else {
                    Answer a3 = new Answer(answerContent3, false);
                    answers.add(a3);
                }

                if (correctAnswerNumber == 4) {
                    Answer a4 = new Answer(answerContent4, true);
                    answers.add(a4);
                } else {
                    Answer a4 = new Answer(answerContent4, false);
                    answers.add(a4);
                }

                //Build a question
                Question question = new Question(questionContent, answers, correctAnswer, level, "panther");

                /*Write to JSON*/
                //Add the new question to the list
                allQuestions.add(question);

                //Load all the questions to a JSONArray called allQuestionsJSONArray
                JsonArray allQuestionsJsonArray = new JsonArray();
                for (Question q : allQuestions) {
                    JsonObject questionObj = new JsonObject();

                    questionObj.addProperty("question", q.getContent());
                    JsonArray answerArray = new JsonArray();
                    for (Answer a : q.getAnswers()) {
                        answerArray.add(a.getContent());
                    }
                    questionObj.add("answers", answerArray);
                    questionObj.addProperty("correct_ans", q.getCorrect_ans());
                    questionObj.addProperty("level", q.getLevel());
                    questionObj.addProperty("team", q.getTeam());
                    allQuestionsJsonArray.add(questionObj);
                }

                JSONQuestionsWriter jsonQuestionsWriter = new JSONQuestionsWriter();
                if (jsonQuestionsWriter.writeQuestions(allQuestionsJsonArray)) {
                    showSuccessAlert("A new question was added successfully!");
                    resetFields();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().addAll("Easy", "Medium", "Hard");

        //Set the toggle group
        radioBtn1.setToggleGroup(toggleGroup);
        radioBtn2.setToggleGroup(toggleGroup);
        radioBtn3.setToggleGroup(toggleGroup);
        radioBtn4.setToggleGroup(toggleGroup);

    }

    public void showSuccessAlert(String content)
    {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
            a.show();
        });
    }

    public void resetFields(){
        content.setText("");
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");
        radioBtn1.setSelected(false);
        radioBtn2.setSelected(false);
        radioBtn3.setSelected(false);
        radioBtn4.setSelected(false);
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Easy", "Medium", "Hard");
        msgLabel.setText("");
    }
}
