package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
			build(primaryStage);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * opens dialogue to open json file
	 */
	private void load(Stage primaryStage) {
		FileChooser choose = new FileChooser();
		choose.getExtensionFilters().addAll(new ExtensionFilter("JSON files (*.json)", "*.json"));
		choose.setTitle("Select Quiz JSON File");
		input = choose.showOpenDialog(new Stage());
		try {
			questionData.loadQuestionsFromJSON(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		build(primaryStage);
	}
	
	public static void main(String[] args) {	
		launch(args);
	}
	
	public void build(Stage primaryStage) {
		Button load = new Button("Load Quiz");
		Button add = new Button("Add Question");
		Button save = new Button("Save to File");
		Button generate = new Button("Generate Quiz");
		HBox buttons = new HBox();

		TextField numQuestions = new TextField();
		Label numQuestionsPrompt = new Label("Enter Number of Questions");
		VBox textField = new VBox();

		ObservableList<String> topics = questionData.getTopics();
		ArrayList<Question> allQuestions = new ArrayList<Question>();
		for (String s : topics) {
			List<Question> tQuestions = questionData.getQuestions(s);
			for (Question q : tQuestions) {
				allQuestions.add(q);
			}
		}
		
		
		Label questions = new Label("Number of Questions: " + allQuestions.size());
		Label topicPrompt = new Label("Select Topic Below");
		ObservableList<String> options = questionData.getTopics();
		
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
		
		comboBox.setOnMouseClicked(e -> {comboBox.setItems(questionData.getTopics());});
		//Opens create Question Dialogue
		add.setOnAction(e -> {new AddQuestionFormNode(questionData, primaryStage, input);
		});
		
		load.setOnAction(e -> {load(primaryStage);});
		
		generate.setOnAction(e -> {				
			String topic = (String)comboBox.getValue();
			if(topic == null) {
				
			}else {
				Quiz quiz = new Quiz(questionData, topic, primaryStage, numQuestions);
			}
			});		
		
			save.setOnAction(e -> {
				FileChooser choose = new FileChooser();
				choose.getExtensionFilters().addAll(new ExtensionFilter("Text File (*.txt)", "*.txt"));
				choose.setTitle("Select Save Location");
				try {
					questionData.saveQuestionsToFile(choose.showSaveDialog(new Stage()));
				}catch(Exception ex) {
					
				}
			});
	}
}