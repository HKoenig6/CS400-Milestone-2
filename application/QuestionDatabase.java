package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.ObservableList;

public class QuestionDatabase implements QuestionDatabaseADT {

	Map<String, List<Question>> topics;

	public QuestionDatabase() {
		topics = new HashMap<String, List<Question>>();
		topics.put("Math", new ArrayList<Question>());
	}
	
	@Override
	public void addQuestion(String topic, Question question) {
		List<Question> questionList = topics.get(topic);
		questionList.add(question);
		topics.replace(topic, questionList);
	}

	@Override
	public void saveQuestionsToFile(File file) {
		try {
			FileWriter json = new FileWriter(file);
			json.write("{\n\t\"questionArray\":\n\t[\n");
			for (String key : topics.keySet()) {
				List<Question> questionList = topics.get(key);
				
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		}		
	}

	@Override
	public List<Question> getQuestions(String topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadQuestionsFromJSON(File file) {
		
	}

	@Override
	public ObservableList<String> getTopics() {
		// TODO Auto-generated method stub
		return null;
	}

}
