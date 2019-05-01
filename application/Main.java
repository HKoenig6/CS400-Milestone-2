package application;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	
	private File input;
	private File currentPicture;
	QuestionDatabase questionData = new QuestionDatabase();
	@Override
	public void start(Stage primaryStage) {
		try {

			Button load = new Button("Load Quiz");
			Button add = new Button("Add Question");
			Button save = new Button("Save to File");
			Button generate = new Button("Generate Quiz");
			HBox buttons = new HBox();

			TextField numQuestions = new TextField();
			Label numQuestionsPrompt = new Label("Enter Number of Questions");
			VBox textField = new VBox();

			Label questions = new Label("Number of Questions: N/A");
			Label topicPrompt = new Label("Select Topic Below");
			ObservableList<String> options = FXCollections.observableArrayList("Math", "Computer Science", "Statistics",
					"Phsics", "History", "English");
			final ComboBox comboBox = new ComboBox(options);
			VBox topicSelection = new VBox();
			
			
			textField.getChildren().addAll(numQuestionsPrompt,numQuestions);
			buttons.getChildren().addAll(load, add, save, generate);
			topicSelection.getChildren().addAll(questions, topicPrompt, comboBox);

			BorderPane root = new BorderPane();
			root.setPadding(new Insets(10));
			root.setLeft(topicSelection);
			root.setBottom(buttons);
			root.setRight(textField);
			Scene scene = new Scene(root, 400, 300);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Quiz Generator");
			primaryStage.show();
			
			//Opens create Question Dialogue
			add.setOnAction(e -> {new AddQuestionFormNode(questionData);
			});
			
			load.setOnAction(e -> {load();});
			
			generate.setOnAction(e -> {Quiz quiz = new Quiz(questionData, "default", primaryStage);
				//primaryStage.setScene(quiz.getScene());
				//primaryStage.setTitle("Quiz");
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * opens dialogue to open json file
	 */
	private void load() {
		FileChooser choose = new FileChooser();
		choose.getExtensionFilters().addAll(new ExtensionFilter("JSON files (*.json)", "*.json"));
		choose.setTitle("Select Quiz JSON File");
		input = choose.showOpenDialog(new Stage());
		questionData.loadQuestionsFromJSON(input);
	}
	public static void main(String[] args) {
		launch(args);
	}
}