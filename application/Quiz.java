package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
	private Button next = new Button("Next");
	private Button submit = new Button("Submit");
	private HBox buttons= new HBox();
	VBox options = new VBox();
	private Stage quizWindow = new Stage();
	
	private String topic;
	private int questionNumber = 0;
	
	private int numCorrect;
	
	
	
	public Quiz(QuestionDatabase data, String topic,Stage primaryStage) {
		this.topic = topic;
		primaryStage.close();
		questions = (ArrayList<Question>) data.getQuestions(topic);
		ArrayList<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice(false,"wrong"));
		choices.add(new Choice(false,"wrong"));
		choices.add(new Choice(false,"wrong"));
		choices.add(new Choice(true,"correct"));
		choices.add(new Choice(false,"wrong"));
		//question = new Question("", "What is question?","default",new File("C:/Users/Matthew Beyer/Pictures/2929857.jpg"),choices);
		initializeWindow();
		
		quizWindow.setOnCloseRequest(e ->{primaryStage.show();});
		
		submit.setOnAction(e -> {
			//Submit Quiz, get score
			primaryStage.show();
			quizWindow.close();
		});
		
//		previous.setOnAction(e -> {
//			//TODO decrement questionNumber if possible
//		});
		
		next.setOnAction(e -> {
			questionNumber++;
			if(questionNumber>questions.size() ) {
				next.setDisable(true);
				getScore();
			}
			//TODO increment questionNumber if possible
		});
	}

	//displays score of finished quiz
	private void getScore() {
		// TODO Auto-generated method stub
		//add label with score and topic name
		//add finished button
		//add padding to scene
		
		Scene scoreScene = new Scene(scoreRoot,400,300);
		quizWindow.setScene(scoreScene);
	}

	private void initializeWindow() {
		option1.setToggleGroup(OPTION_GROUP);
		option2.setToggleGroup(OPTION_GROUP);
		option3.setToggleGroup(OPTION_GROUP);
		option4.setToggleGroup(OPTION_GROUP);
		option5.setToggleGroup(OPTION_GROUP);
		
		Label startMessage = new Label(questions.get(questionNumber).getTopic() + " Quiz");
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
		buttons.getChildren().addAll(/*previous,*/next,submit);
		options.getChildren().addAll(option1,option2,option3,option4,option5);
		questionText.setText(questions.get(questionNumber).getQuestion());
		ArrayList<Choice> choices = (ArrayList<Choice>)questions.get(questionNumber).getChoices();
		
		option1.setText("1. " + choices.get(0).getChoice());
		option2.setText("2. " + choices.get(1).getChoice());
		option3.setText("3. " + choices.get(2).getChoice());
		option4.setText("4. " + choices.get(3).getChoice());
		option5.setText("5. " + choices.get(4).getChoice());
		BorderPane root = new BorderPane();
		root.setBottom(buttons);
		root.setCenter(options);
		root.setTop(questionText);
		
		scene = new Scene(root, 400, 300);
		quizWindow.setScene(scene);
		
	}
	
	
	public Scene getScene() {
		return scene;
	}
	

}
