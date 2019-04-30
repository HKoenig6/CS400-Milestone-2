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
<<<<<<< HEAD
			VBox topicSelection = new VBox();

			textField.getChildren().addAll(numQuestionsPrompt, numQuestions);
=======
			
			
			VBox selection = new VBox();
			
			
			//good work
			textField.getChildren().addAll(numQuestionsPrompt,numQuestions);
>>>>>>> 0f02b65d4f349b44d666494f38ce61a25e296daf
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
			add.setOnAction(e -> {createQuestion(1);});
			
			load.setOnAction(e -> {load();});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * creates window that prompts user for question input
	 * 
	 * @param questionNumber
	 */
	public void createQuestion(int questionNumber) {
		final ToggleGroup OPTION_GROUP = new ToggleGroup();
		try {
			
			Label pictureDirectory = new Label();
			
			//Enter Question
			Label questionPrompt = new Label("Enter question " + questionNumber  + "   ");
			TextField questionText = new TextField();
			HBox question = new HBox();
			question.getChildren().addAll(questionPrompt,questionText);
			
			//Select Topic
			Label topicPrompt = new Label("Enter Topic Name ");
			TextField topicText = new TextField();
			HBox topic = new HBox();
			topic.getChildren().addAll(topicPrompt,topicText);
			
			VBox questionTopicPrompts = new VBox();
			questionTopicPrompts.getChildren().addAll(question,topic,pictureDirectory);
			
			
			
			
			/**
			 * option radio buttons and text fields
			 */
			Label optionPrompt = new Label("Enter Question Options and Select Correct Answer\n");
			
			
			RadioButton option1Button = new RadioButton("1.  ");
			option1Button.setToggleGroup(OPTION_GROUP);
			TextField option1Text = new TextField("Enter Option 1");
			HBox option1 = new HBox();
			option1.getChildren().addAll(option1Button,option1Text);
			
			RadioButton option2Button = new RadioButton("2.  ");
			option2Button.setToggleGroup(OPTION_GROUP);
			TextField option2Text = new TextField("Enter Option 2");
			HBox option2 = new HBox();
			option2.getChildren().addAll(option2Button,option2Text);
			
			RadioButton option3Button = new RadioButton("3.  ");
			option3Button.setToggleGroup(OPTION_GROUP);
			TextField option3Text = new TextField("Enter Option 3");
			HBox option3 = new HBox();
			option3.getChildren().addAll(option3Button,option3Text);
			
			RadioButton option4Button = new RadioButton("4.  ");
			option4Button.setToggleGroup(OPTION_GROUP);
			TextField option4Text = new TextField("Enter Option 4");
			HBox option4 = new HBox();
			option4.getChildren().addAll(option4Button,option4Text);
			
			VBox options = new VBox();
			options.getChildren().addAll(new Label(),optionPrompt,new Label(),option1 , option2, option3,option4);
			
			
			/**
			 * Submit Button
			 * Add Picture Button
			 */
			
			Button submit = new Button("Submit Question");
			Button addPic = new Button("Add Picture");
			HBox buttons = new HBox();
			buttons.getChildren().addAll(addPic,submit);
			
			Stage questionPromptWindow = new Stage();
			BorderPane root = new BorderPane();
			root.setTop(questionTopicPrompts);
			root.setCenter(options);
			root.setBottom(buttons);
			root.setPadding(new Insets(10));
			Scene scene = new Scene(root, 400, 300);
			questionPromptWindow.setScene(scene);
			questionPromptWindow.setTitle("Create Question");
			questionPromptWindow.show();
			
			
			//Clear textField when clicked first time or start typing in (only done if user tabs into text field)
			option1Text.setOnMouseClicked(e -> {if(option1Text.getText().equals("Enter Option 1")) option1Text.clear();});
			option1Text.setOnKeyPressed(e -> {if(option1Text.getText().equals("Enter Option 1")) option1Text.clear();});
			option2Text.setOnMouseClicked(e -> {if(option2Text.getText().equals("Enter Option 2")) option2Text.clear();});
			option2Text.setOnKeyPressed(e -> {if(option2Text.getText().equals("Enter Option 2")) option2Text.clear();});
			option3Text.setOnMouseClicked(e -> {if(option3Text.getText().equals("Enter Option 3")) option3Text.clear();});
			option3Text.setOnKeyPressed(e -> {if(option3Text.getText().equals("Enter Option 3")) option3Text.clear();});
			option4Text.setOnMouseClicked(e -> {if(option4Text.getText().equals("Enter Option 4")) option4Text.clear();});
			option4Text.setOnKeyPressed(e -> {if(option4Text.getText().equals("Enter Option 4")) option4Text.clear();});
			
			//Submit Button Event
			submit.setOnAction(e -> {
				if(option1Button.isSelected())
					submit(questionText.getText(),option1Text.getText(),option2Text.getText(),option3Text.getText(),option4Text.getText(),1);
				else if(option2Button.isSelected())
					submit(questionText.getText(),option1Text.getText(),option2Text.getText(),option3Text.getText(),option4Text.getText(),2);
				else if(option3Button.isSelected())
					submit(questionText.getText(),option1Text.getText(),option2Text.getText(),option3Text.getText(),option4Text.getText(),3);
				else if(option4Button.isSelected())
					submit(questionText.getText(),option1Text.getText(),option2Text.getText(),option3Text.getText(),option4Text.getText(),4);
				
				questionPromptWindow.close();
				});
			
			//addPic button event
			//TODO: fix if person press cancel in FileChooser
			addPic.setOnAction(e -> {
				FileChooser choose = new FileChooser();
				choose.getExtensionFilters().addAll(new ExtensionFilter("Picture File (*.jpg, *.jpeg, *.png)", "*.jpg","*.jpeg","*.png"));
				choose.setTitle("Select Question Picture");
				currentPicture = choose.showOpenDialog(new Stage());
				pictureDirectory.setText("Picture: " + currentPicture.toString());
			});
			
		} catch (Exception e) {
		}
	}
	
	/**
	 * Creates a new Question object and adds to ADT
	 * 
	 * @param quest question
	 * @param op1 option 1
	 * @param op2 option 2
	 * @param op3 option 3
	 * @param op4 option 4
	 * @param crct correct option number
	 */
	private void submit(String quest,String op1,String op2,String op3,String op4,int crct) {
		Question question = new Question(quest,op1,op2,op3,op4,crct,currentPicture);
		//Eventually add question to ADT
		currentPicture = null;
		System.out.println(question);
	}

	/**
	 * opens dialogue to open json file
	 */
	private void load() {
		FileChooser choose = new FileChooser();
		choose.getExtensionFilters().addAll(new ExtensionFilter("JSON files (*.json)", "*.json"));
		choose.setTitle("Select Quiz JSON File");
		input = choose.showOpenDialog(new Stage());
		
		//TODO:
		//process input
		//parse JSON file
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
