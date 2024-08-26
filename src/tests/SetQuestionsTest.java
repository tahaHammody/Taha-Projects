package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import model.SysData;
import model.Question;
import utils.DifficultyLevel;

public class SetQuestionsTest {
	@Test
	public void test() {
		ArrayList<Question> A = new ArrayList<>();
		Question testQ2=new Question("this is a test?", null, null, DifficultyLevel.EASY, null);
		Question testQ1=new Question("this is a test?", null, null, DifficultyLevel.EASY, null);
	     A.add(testQ1);
	     A.add(testQ2);
		Boolean Status=SysData.getInstance().setQuestionsT(A);
		//check if add questions will return true if the object that is passed includes null ;
		assertTrue(Status);
	}
}
