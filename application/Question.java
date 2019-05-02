package application;

import java.io.File;
import java.util.List;

public class Question {
	private String metadata;
	private String question;
	private String topic;
	private File image;
	private List<Choice> choices;
	private String answer;
	
	public Question(String metadata, String question, String topic, File image, 
			List<Choice> choices) {
		this.metadata = metadata;
		this.question = question;
		this.topic = topic;
		this.image = image;
		this.choices = choices;
		//TODO find answer from choices
		for(Choice choice: choices) {
			if(choice.getIsCorrect())
				this.answer = choice.getChoice();
		}
	}
	
	String getQuestion() {
		return question;
	}
	
	List<Choice> getChoices() {
		return choices; 
	}
	
	String getAnswer() {
		return answer;
	}
	
	String getMetadata() {
		return metadata;
	}
	
	String getTopic() {
		return topic;
	}
	
	File getImage() {
		return image;
	}
	
	public String toString() {
		String out = "";
		out += "Question: " +question;
		out += "\nOption 1: " + choices.get(0).getChoice();
		out += "\nOption 2: " + choices.get(1).getChoice();;
		out += "\nOption 3: " + choices.get(2).getChoice();;
		out += "\nOption 4: " + choices.get(3).getChoice();;
		out += "\nCorrect Option: " + answer;
		out += "\nPicture Directory: " + image.getName().toString();
		return out;
	}
}
