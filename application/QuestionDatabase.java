package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuestionDatabase implements QuestionDatabaseADT {

	Map<String, List<Question>> topics;

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

					
					json.write("\t\t\t\"image\":\"" + q.getImage().getName() + "\",\n");
					json.write("\t\t\t\"choiceArray\":\n\t\t\t[\n");
					int cIndex = 0;
					for (Choice c : q.getChoices()) {
						if (!c.getChoice().equals("")) {
							cIndex++;
						}
					}
					for (int i = 0; i < cIndex; i++) {
						cCounter++;
						String tf;
						if (q.getChoices().get(i).getIsCorrect()) {
							tf = "T";
						} else {
							tf = "F";
						}
						json.write("\t\t\t\t{\"isCorrect\":\"" + tf + "\",\"choice\":\"" + q.getChoices().get(i).getChoice() + "\"}");
						if (cCounter == cIndex) {
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
    public void loadQuestionsFromJSON(File file) throws FileNotFoundException, IOException, ParseException {
           Object obj = new JSONParser().parse(new FileReader(file));
           JSONObject jo = (JSONObject) obj;
           JSONArray question = (JSONArray) jo.get("questionArray");
           for (int i = 0; i < question.size(); i++) {
                  JSONObject jsonPackage = (JSONObject) question.get(i);
                  String metadata = (String) jsonPackage.get("meta-data");
                  String questions = (String) jsonPackage.get("questionText");
                  String topic = (String) jsonPackage.get("topic");
                  File image = new File((String) jsonPackage.get("image"));
                  JSONArray choiceArray = (JSONArray) jsonPackage.get("choiceArray");
                  List<Choice> choices = new ArrayList<Choice>();
                  for (int j = 0; j < choiceArray.size(); j++) {
                        JSONObject jsonArray = (JSONObject) choiceArray.get(j);
                        String isCorrect = (String) jsonArray.get("isCorrect");
                        String choice = (String) jsonArray.get("choice");
                        if (isCorrect.equals("T")) {
                               choices.add(new Choice(true, choice));
                        } else {
                               choices.add(new Choice(false, choice));
                        }
                  }
                  addQuestion(topic, new Question(metadata, questions, topic, image, choices));
           }
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