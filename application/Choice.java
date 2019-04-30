package application;

public class Choice {
	private boolean isCorrect;
	private String choice;
	
	public Choice(boolean isCorrect, String choice) {
		this.isCorrect = isCorrect;
		this.choice = choice;
	}
	
	String getChoice() {
		return choice;
	}
	
	boolean getIsCorrect() {
		return isCorrect;
	}
}
