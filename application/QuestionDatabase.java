package application;

import java.io.File;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;

public class QuestionDatabase implements QuestionDatabaseADT {

	Map<String, List<Question>> topics;
	
	@Override
	public void addQuestion(String str, Question question) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveQuestionsToFile(File file) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Question> getQuestions(String topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadQuestionsFromJSON(File file) {
		// TODO Auto-generated method stub
	}

	@Override
	public ObservableList<String> getTopics() {
		// TODO Auto-generated method stub
		return null;
	}

}
