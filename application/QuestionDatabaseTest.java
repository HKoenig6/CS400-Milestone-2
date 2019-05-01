package application;

import static org.junit.jupiter.api.Assertions.*;
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
			Question q1 = new Question("used", "Is the sky blue?", "Math", "btree.png", choices, "yes");
			QuestionDatabase qd = new QuestionDatabase();
			qd.addQuestion("Math", q1);
			
			
			
		} catch (Exception e) {
			fail("unknown exception");
		}
	}

}
