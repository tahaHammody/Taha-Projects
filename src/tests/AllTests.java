package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.Answer;


public class AllTests {
	@Test
	public void test_Constructor() {
	
		Answer Ans = new Answer("Green", true);
		
		boolean expectedResult = true;
		boolean actualResult = Ans.isCorrect() ;
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void testIDAnswerClass() {
	
		Answer B = new Answer("Green", true);
		B.setId(10);
		int expectedResult = 10;
		int actualResult = B.getId() ;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void test_AnswerClass() {
	
		Answer C = new Answer("Green", true);
		C.setContent("This is Answer.java Class");
		String expectedResult = "This is Answer.java Class";
		String actualResult = C.getContent() ;
		assertEquals(expectedResult, actualResult);
	}
	

}
