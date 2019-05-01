package application;

import java.io.File;
import java.util.List;

import javafx.collections.ObservableList;

public interface QuestionDatabaseADT {
	void addQuestion(String topic, Question question);
	void saveQuestionsToFile(File file);
	List<Question> getQuestions(String topic); //unsure about whether String is topic or not
	void loadQuestionsFromJSON(File file);
	ObservableList<String> getTopics();
}
