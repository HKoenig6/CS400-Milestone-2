package application;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class QuestionNode implements NodeWrapperADT {
	VBox node;
	ToggleGroup choices;
	
	QuestionNode(Question question) {
		//TODO
	}
	
	VBox getNode() {
		return node;
	}
	
	ToggleGroup getChoices() {
		return choices;
	}
}
