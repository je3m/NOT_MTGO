package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCard {

	@Test
	public void makeCardGetName(){
		Card c = new Card("Storm crow");
		assertEquals(c.getName(), "Storm crow");
	}

	@Test
	public void testToString(){
		assertEquals("Storm Crow", new Card("Storm Crow").toString());
		assertEquals("Scornful Egotist", new Card("Scornful Egotist").toString());
		assertEquals("One With Nothing", new Card("One With Nothing").toString());
	}
}
