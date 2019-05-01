package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuestionDatabase implements QuestionDatabaseADT {

	Map<String, List<Question>> topics;
	private ArrayList<String> allTopicsList = new ArrayList<String>();

	public QuestionDatabase() {
		topics = new HashMap<String, List<Question>>();
		//topics.put("Math", new ArrayList<Question>());
	}
	
	@Override

    public void addQuestion(String str, Question question) {
           if(topics.containsKey(str))
                  topics.get(str).add(question);
           else {
                  List<Question> newList = new ArrayList<Question>();
                  newList.add(question);
                  topics.put(str, newList);
           }

    }

	@Override
	public void saveQuestionsToFile(File file) {
		try {
			FileWriter json = new FileWriter(file);
			json.write("{\n\t\"questionArray\":\n\t[\n");
			for (String key : topics.keySet()) {
				List<Question> questionList = topics.get(key);
				int qCounter = 0;
				for (Question q : questionList) {
					qCounter++;
					int cCounter = 0;
					json.write("\t\t{\n");
					json.write("\t\t\t\"meta-data\":\"" + q.getMetadata() + "\",\n");
					json.write("\t\t\t\"questionText\":\"" + q.getQuestion() + "\",\n");
					json.write("\t\t\t\"topic\":\"" + q.getTopic() + "\",\n");
					String img;
					if (q.getImage() == null) {
						img = "none";
					} else {
						img = q.getImage().getName();
					}
					json.write("\t\t\t\"image\":\"" + img + "\",\n");
					json.write("\t\t\t\"choiceArray\":\n\t\t\t[\n");
					for (Choice c : q.getChoices()) {
						cCounter++;
						String tf;
						if (c.getIsCorrect()) {
							tf = "T";
						} else {
							tf = "F";
						}
						json.write("\t\t\t\t{\"isCorrect\":\"" + tf + "\",\"choice\":\"" + c.getChoice() + "\"}");
						if (cCounter == q.getChoices().size()) {
							json.write("\n");
						} else {
							json.write(",\n");
						}						
					}
					if (qCounter == questionList.size()) {
						json.write("\t\t\t],\n\t\t}\n");
					} else {
						json.write("\t\t\t],\n\t\t},\n");
					}					
				}
				json.write("\t]\n}");
			}
			json.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		}		
	}

	@Override
	public List<Question> getQuestions(String topic) {
		return topics.get(topic);
	}

	@Override
	public void loadQuestionsFromJSON(File file) {
		
	}

	@Override

    public ObservableList<String> getTopics() {
		ArrayList<String> allTopicsList = new ArrayList<String>();
		Set<String> set = topics.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext())
        	allTopicsList.add(it.next());
        	ObservableList<String> allTopics = FXCollections.observableArrayList(allTopicsList);
        	return allTopics;
    }
}
