package application;

	
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	QuestionDatabase questionDB;
	List<Question> questions;
	Question currQuestion;
	int currQuestionNum;
	int totalNumQuestions;
	int numIncorrect;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Button load = new Button("Load");
			Button add = new Button("Add Question");
			Button save = new Button("Save to File");
			Button generate = new Button("Generate Quiz");
			HBox buttons = new HBox();
			
			TextField numQuestions = new TextField();
			Label numQuestionsPrompt = new Label("Enter Number of Questions");
			Label topics = new Label("Choose a Topic: ");
			VBox textField = new VBox();
			
			
			Label questions = new Label("Number of Questions: N/A");
			ObservableList<String> options = 
					FXCollections.observableArrayList(
							"Math",
							"Computer Science",
							"Statistics",
							"Physics",
							"History",
							"English");
			final ComboBox comboBox = new ComboBox(options);
			
			
			VBox selection = new VBox();
			
			
			//good work
			textField.getChildren().addAll(numQuestionsPrompt,numQuestions);
			buttons.getChildren().addAll(load, add, save, generate);
			selection.getChildren().addAll(questions,topics, comboBox);
			
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(10));
			root.setLeft(selection);
			root.setBottom(buttons);
			root.setRight(textField);
			Scene scene = new Scene(root,400,300);
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Quiz Generator");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void setup() {
		//TODO
	}
	
	void displayAddQuestionForm() {
		//TODO
	}
	
	void displayQuiz() {
		//TODO
	}
	
	void displayQuestion() {
		//TODO
	}
	
	void displaySumbit(QuestionNode node) {
		//TODO
	}
	
	void displayResults() {
		//TODO
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
