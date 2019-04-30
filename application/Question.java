package application;

import java.util.List;

public class Question {
	private String metadata;
	private String question;
	private String topic;
	private String image;
	private List<Choice> choices;
	private String answer;
	
	public Question(String metadata, String question, String topic, String image, 
			List<Choice> choices, String answer) {
		this.metadata = metadata;
		this.question = question;
		this.topic = topic;
		this.image = image;
		this.choices = choices;
		this.answer = answer;
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
	
	String getImage() {
		return image;
	}
}
