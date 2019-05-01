package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class QuestionDatabaseTest {

	@Test
	void test00_add_question() {
		try {
			List<Choice> choices = new ArrayList<Choice>();
			choices.add(new Choice(true, "yes"));
			choices.add(new Choice(false, "no"));
			choices.add(new Choice(false, "maybe"));
			choices.add(new Choice(false, "never"));
			Question q1 = new Question("used", "Is the sky blue?", "Math", new File("btree.png"), choices);
			QuestionDatabase qd = new QuestionDatabase();
			qd.addQuestion("Math", q1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("unknown exception");
		}
	}
	
	@Test
	void test01_save_to_JSON_file() {
		try {
			QuestionDatabase qd = new QuestionDatabase();
			List<Choice> choices = new ArrayList<Choice>();
			choices.add(new Choice(true, "yes"));
			choices.add(new Choice(false, "no"));
			choices.add(new Choice(false, "maybe"));
			choices.add(new Choice(false, "never"));
			Question q1 = new Question("used", "Is the sky blue?", "Math", new File("btree.png"), choices);
			Question q2 = new Question("used", "Is the sky red?", "Math", new File("btree.png"), choices);
			qd.addQuestion("Math", q1);
			qd.addQuestion("Math", q2);
			qd.saveQuestionsToFile(new File("example.json"));
		} catch (Exception f) {
			fail("file not found");
		}
	}

}
