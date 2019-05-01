package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddQuestionFormNode {
	List<Choice> choices;
	Question finishedQuestion;
	//List<ToggleGroup> choiceGroups;
	VBox form;
	File currentPicture;
	boolean finished =false;
	//QuestionDatabase database;
	
	public AddQuestionFormNode(QuestionDatabase database) {
		final ToggleGroup OPTION_GROUP = new ToggleGroup();
		//this.database = newDatabase;
		try {
			
			Label pictureDirectory = new Label();
			
			//Enter Question
			Label questionPrompt = new Label("Enter question ");
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
			
			RadioButton option5Button = new RadioButton("5.  ");
			option5Button.setToggleGroup(OPTION_GROUP);
			TextField option5Text = new TextField("Enter Option 5");
			HBox option5 = new HBox();
			option5.getChildren().addAll(option5Button,option5Text);
			
			VBox options = new VBox();
			options.getChildren().addAll(new Label(),optionPrompt,new Label(),option1 , option2, option3, option4, option5);
			
			option1Button.setSelected(true);
			/**
			 * Submit Button
			 * Add Picture Button
			 */
			
			
			Button addPic = new Button("Add Picture");
			Button submit = new Button("Submit Question");
			Button cancel = new Button("Cancel");
			HBox buttons = new HBox();
			buttons.getChildren().addAll(addPic,submit,new Label(),cancel);
			
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
			option5Text.setOnMouseClicked(e -> {if(option5Text.getText().equals("Enter Option 5")) option5Text.clear();});
			option5Text.setOnKeyPressed(e -> {if(option5Text.getText().equals("Enter Option 5")) option5Text.clear();});
			
			//Submit Button Event
			submit.setOnAction(e -> {
				finished = true;
				int correctOption = 0;
				if(option1Button.isSelected())
					correctOption = 1;
				else if(option2Button.isSelected())
					correctOption = 2;
				else if(option3Button.isSelected())
					correctOption = 3;
				else if(option4Button.isSelected())
					correctOption = 4;
				else if(option5Button.isSelected())
					correctOption = 5;
				
				choices = new ArrayList<Choice>();
				choices.add(new Choice(correctOption == 1, option1Text.getText()));
				choices.add(new Choice(correctOption == 2, option2Text.getText()));
				choices.add(new Choice(correctOption == 3, option3Text.getText()));
				choices.add(new Choice(correctOption == 4, option4Text.getText()));
				choices.add(new Choice(correctOption == 5, option5Text.getText()));
				
				finishedQuestion = new Question("1",questionText.getText(),topicText.getText(),currentPicture,choices);
				System.out.println(finishedQuestion);
				database.addQuestion(finishedQuestion.getTopic(), finishedQuestion);
				System.out.println(database.getTopics());
				questionPromptWindow.close();
				});
			
			//addPic button event
			addPic.setOnAction(e -> {
				FileChooser choose = new FileChooser();
				choose.getExtensionFilters().addAll(new ExtensionFilter("Picture File (*.jpg, *.jpeg, *.png)", "*.jpg","*.jpeg","*.png"));
				choose.setTitle("Select Question Picture");
				try {
				currentPicture = choose.showOpenDialog(new Stage());
				pictureDirectory.setText("Picture: " + currentPicture.toString());
				}
				catch(Exception ex) {
				}
			});
			
			//cancel button event
			cancel.setOnAction(e -> {
				questionPromptWindow.close();
			});
			
		} catch (Exception e) {
		}
	}
	
//	private void submit(String quest,String op1,String op2,String op3,String op4,int crct) {
//		//TODO create question
//		
//	}
	
//	String getMetadata() {
//		return null; //TODO
//	}
//	
	Question getQuestion() {
		
		return finishedQuestion;
	}
	
	String getTopic() {
		return finishedQuestion.getTopic();
	}
	boolean isFinished() {
		
		return finished;
	}
	boolean finish() {
		while(!isFinished()) {
			
		}
		return true;
	}
//	QuestionDatabase getData() {
//		return database;
//	}
//	
//	File getImage() {
//		return currentPicture;
//	}
//	
//	List<Choice> getChoiceTexts() {
//		return choices;
//	}
//	
//	VBox getNode() {
//		return null; //TODO
//	}
}
