package control;


        import com.google.gson.*;
        import javafx.application.Platform;
        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
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
        import java.util.Optional;
        import java.util.ResourceBundle;

public class QuestionsController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button editQuestion;

    @FXML
    private Button deleteQuestion;

    @FXML
    private Button newQuestion;

    @FXML
    private ListView questionsList;

    @FXML
    private ScrollPane listScroll;

    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> items = FXCollections.observableArrayList();

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

    /*Read all the questions stored in the JSON file
    * Store all the questions in an ArrayList*/
    JSONQuestionsReader jsonQuestionRead = new JSONQuestionsReader();
    ArrayList<Question> allQuestions = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jsonQuestionRead.readQuestions("/json/QuestionsFormat.json");
        allQuestions = sysData.getQuestions();
        // This adds the questions we just loaded to the UI element
        loadQuestions();

        comboBox.getItems().addAll("Easy", "Medium", "Hard");

        //Listening to the changes in the list view selection

        questionsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedQuestionContent = newValue;
                Question selectedQuestion = null;
                for (int i = 0; i < allQuestions.size(); i++) {
                    Question q = allQuestions.get(i);

                    if(q.getContent().equals(selectedQuestionContent)){
                        selectedQuestion = q;
                        break;
                    }
                }

                content.setText(selectedQuestion.getContent());
                answer1.setText(String.valueOf(selectedQuestion.getAnswers().get(0).getContent()));
                answer2.setText(String.valueOf(selectedQuestion.getAnswers().get(1).getContent()));
                answer3.setText(String.valueOf(selectedQuestion.getAnswers().get(2).getContent()));
                answer4.setText(String.valueOf(selectedQuestion.getAnswers().get(3).getContent()));

                int correctAns = 0;
                for (int i = 0; i < selectedQuestion.getAnswers().size(); i++) {
                    if(selectedQuestion.getAnswers().get(i).isCorrect()){
                        correctAns = i+1;
                    }
                }

                if(correctAns == 1){
                    radioBtn1.setSelected(true);
                } else if (correctAns == 2) {
                    radioBtn2.setSelected(true);
                } else if (correctAns == 3) {
                    radioBtn3.setSelected(true);
                } else if (correctAns == 4) {
                    radioBtn4.setSelected(true);
                }

                String dl = selectedQuestion.getLevel();
                if(dl.equals("Easy")){
                    comboBox.setValue("Easy");
                } else if (dl.equals("Medium")) {
                    comboBox.setValue("Medium");
                } else if (dl.equals("Hard")) {
                    comboBox.setValue("Hard");
                }
            }

        });

    }
    @FXML
    void deleteQuestion(ActionEvent event) {
        final int selectedId = questionsList.getSelectionModel().getSelectedIndex();
        if(selectedId != -1){
            /*Ask the user to confirm deleting a question*/
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Question");
            alert.setHeaderText("Do you want to remove this question?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //removes the question for the ArrayList
                allQuestions.remove(selectedId);

                //removes the question for the json array
                JsonArray allQuestionsJsonArray = new JsonArray();
                for (Question q: allQuestions) {
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
                //Write questions to JSON
                JSONQuestionsWriter jsonQuestionsWriter = new JSONQuestionsWriter();
                if(jsonQuestionsWriter.writeQuestions(allQuestionsJsonArray)){
                   System.out.println("Question was deleted!");
                }
                //Remove item from list view
                items.remove(selectedId);

                //Update question in sysData
                jsonQuestionRead.readQuestions("/json/QuestionsFormat.json");
                allQuestions = sysData.getQuestions();



            }
        }
    }
    @FXML
    void editQuestion(ActionEvent event) {
        final int selectedId = questionsList.getSelectionModel().getSelectedIndex();


            /*Store all the user inputs in local variables*/
            String questionContent = content.getText();
            String answerContent1 = answer1.getText();
            String answerContent2 = answer2.getText();
            String answerContent3 = answer3.getText();
            String answerContent4 = answer4.getText();
            RadioButton correctAnswerBtn = (RadioButton) toggleGroup.getSelectedToggle(); //Temp
            String levelStr = String.valueOf(comboBox.getSelectionModel().getSelectedIndex()); //Temp

            //Check if one or more of the fields are empty
            if(questionContent.isEmpty() || answerContent1.isEmpty()
                    || answerContent2.isEmpty()  || toggleGroup.getSelectedToggle() == null
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
//                Boolean questionExists = false;
//                for (Question q: allQuestions) {
//                    if(q.getContent().equals(questionContent)){
//                        questionExists = true;
//                    }
//                }
//                if(questionExists){
//                    msgLabel.setText("This question already exists! Change its content.");
//                }
                //Check if the question was changed
                Boolean isChanged = false;

                //Check if all the answers are unique
                if(!answerDuplicates)
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

                    //Difficulty Level
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

                    //Updated Question
                    Question updatedQuestion = new Question(questionContent, answers, correctAnswer, level, "panther");

                    if (allQuestions.contains(updatedQuestion)){
                        msgLabel.setText("Question already exists!");
                    }
                    else {
                        //Remove the Question-To-Update from the list
                        allQuestions.remove(selectedId);
                        //Add the updated question to the list
                        allQuestions.add(updatedQuestion);

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
                            showSuccessAlert("A new question was edited successfully!");
                        }
                        //Update the list view
                        items.remove(selectedId);
                        items.add(selectedId, updatedQuestion.getContent());

                        //Update question in sysData
                        jsonQuestionRead.readQuestions("/json/QuestionsFormat.json");
                        allQuestions = sysData.getQuestions();

                    }
                }
            }
    }

    @FXML
    void goBack(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(QuestionsController.class.getResource("/view/login.fxml"));
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
    void newQuestion(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(QuestionsController.class.getResource("/view/addQuestion.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) newQuestion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Chess");
            stage.show();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    void loadQuestions() {
        //Load the data in the arraylist to the list view

        ArrayList<String> questionContent = new ArrayList<>();

        //Getting all the questions' contents
        for (int i = 0; i < allQuestions.size(); i++) {
            Question currentQuestion = allQuestions.get(i);

            String content = currentQuestion.getContent();

            questionContent.add(content);
        }

        for (String t: questionContent) {
            items.add(t);
        }
        questionsList.setItems(items);

    }

    void loadQuestionDetails(){
        questionsList.getSelectionModel().getSelectedItem();

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
    }

    public void showSuccessAlert(String content)
    {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
            a.show();
        });
    }
}
