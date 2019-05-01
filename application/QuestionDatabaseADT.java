package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javafx.collections.ObservableList;

public interface QuestionDatabaseADT {
	void addQuestion(String topic, Question question);
	void saveQuestionsToFile(File file);
	List<Question> getQuestions(String topic); //unsure about whether String is topic or not
	void loadQuestionsFromJSON(File file) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException;
	ObservableList<String> getTopics();
}
