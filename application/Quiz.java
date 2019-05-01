package application;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Quiz {
	private QuestionDatabase questionData;
	private Scene scene;
	private ArrayList<Question> questions;
	final ToggleGroup OPTION_GROUP = new ToggleGroup();
	
	private Label questionText = new Label();
	private RadioButton option1 = new RadioButton();
	private RadioButton option2 = new RadioButton();
	private RadioButton option3 = new RadioButton();
	private RadioButton option4 = new RadioButton();
	private RadioButton option5 = new RadioButton();
	private Button finished = new Button("Finish");
	private Button next = new Button("Next Question");
	private Button submit = new Button("Submit");
	private ImageView imageView = new ImageView();
	private HBox buttons= new HBox();
	VBox options = new VBox();
	
	
	private Stage quizWindow = new Stage();
	
	private String topic;
	private int questionNumber = 0;
	
	private int numCorrect;
	
	
	
	public Quiz(QuestionDatabase data, String topic,Stage primaryStage,TextField size) {
		this.topic = topic;
		primaryStage.close();
		
		questions = new ArrayList<Question>();
		
		ArrayList<Question> existingQuestions = (ArrayList<Question>) data.getQuestions(topic);
		Random rnd = new Random();
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		int qnum = Integer.parseInt(size.getText());	
		if(qnum >= existingQuestions.size()) {
			questions = existingQuestions;
		}else {
			for(qnum = Integer.parseInt(size.getText());qnum>0;qnum--) {
				int next = rnd.nextInt(existingQuestions.size()-1);
				if(chosen.contains(next)) {
					qnum--;
				}else {
					questions.add(existingQuestions.get(next));
				}
			}
		}
		if(questions.size() == 1)
			next.setDisable(true);
		
		initializeWindow();
		
		quizWindow.setOnCloseRequest(e ->{primaryStage.show();});
		
		finished.setOnAction(e -> {
			primaryStage.show();
			quizWindow.close();
		});
		submit.setOnAction(e -> {
			String userAnswer = "";
			String answer = questions.get(questionNumber).getAnswer();
			if(option1.isSelected())
				userAnswer = option1.getText();
			else if(option2.isSelected())
				userAnswer = option2.getText();
			else if(option3.isSelected())
				userAnswer = option3.getText();
			else if(option4.isSelected())
				userAnswer = option4.getText();
			else if(option5.isSelected())
				userAnswer = option5.getText();
			
			if(userAnswer.equals(answer))
				numCorrect++;
			//Submit Quiz, get score
			getScore();
		});
		
		next.setOnAction(e -> {
			String userAnswer = "";
			String answer = questions.get(questionNumber).getAnswer();
			if(option1.isSelected())
				userAnswer = option1.getText();
			else if(option2.isSelected())
				userAnswer = option2.getText();
			else if(option3.isSelected())
				userAnswer = option3.getText();
			else if(option4.isSelected())
				userAnswer = option4.getText();
			else if(option5.isSelected())
				userAnswer = option5.getText();
			
			if(userAnswer.equals(answer))
				numCorrect++;
			questionNumber++;
			if((questionNumber + 1)>=questions.size()) {
				next.setDisable(true);
			}else {
				showNextQuestion();
			}
			//TODO increment questionNumber if possible
		});
	}

	//displays score of finished quiz
	private void getScore() {
		BorderPane scoreRoot = new BorderPane();
		Label finishedLbl = new Label("               Quiz Complete!");
		finishedLbl.setFont(new Font(24));
		Label yourScore = new Label("Your Score:\n       " + numCorrect + "/" + questions.size());
		yourScore.setFont(new Font(18));
		yourScore.setPadding(new Insets(10));
		Label padding = new Label("");

		padding.setFont(new Font(36));

		scoreRoot.setTop(finishedLbl);
		scoreRoot.setCenter(yourScore);
		scoreRoot.setRight(padding);
		scoreRoot.setBottom(finished);
		scoreRoot.setPadding(new Insets(10));
		Scene scoreScene = new Scene(scoreRoot,400,300);
		quizWindow.setScene(scoreScene);
	}

	private void initializeWindow() {
		option1.setToggleGroup(OPTION_GROUP);
		option2.setToggleGroup(OPTION_GROUP);
		option3.setToggleGroup(OPTION_GROUP);
		option4.setToggleGroup(OPTION_GROUP);
		option5.setToggleGroup(OPTION_GROUP);
		buttons.getChildren().addAll(/*previous,*/next,submit);
		options.getChildren().addAll(option1,option2,option3,option4,option5);
		
		Label startMessage = new Label(topic + " Quiz");
		startMessage.setFont(new Font(24));
		Button start = new Button("Start Quiz");
		
		BorderPane initRoot = new BorderPane();
		initRoot.setCenter(startMessage);
		initRoot.setBottom(start);
		initRoot.setPadding(new Insets(10));
		
		Scene initScene = new Scene(initRoot,400,300);
	
		quizWindow.setScene(initScene);
		quizWindow.setTitle(topic + " Quiz");
		quizWindow.show();
		
		
		start.setOnAction(e -> {
			showNextQuestion();
		});
	}
	
	private void showNextQuestion() {
		questionText.setText(questions.get(questionNumber).getQuestion());
		ArrayList<Choice> choices = (ArrayList<Choice>)questions.get(questionNumber).getChoices();
		Image image = new Image(questions.get(questionNumber).getImage().toURI().toString());
		imageView.setImage(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		option1.setText(choices.get(0).getChoice());
		option2.setText(choices.get(1).getChoice());
		option3.setText(choices.get(2).getChoice());
		option4.setText(choices.get(3).getChoice());
		option5.setText(choices.get(4).getChoice());
		BorderPane root = new BorderPane();
		root.setBottom(buttons);
		root.setCenter(options);
		root.setTop(questionText);
		root.setRight(imageView);
		root.setPadding(new Insets(10));
		
		scene = new Scene(root, 400, 300);
		quizWindow.setScene(scene);
	}
	
	
	public Scene getScene() {
		return scene;
	}
	

}
