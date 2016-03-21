package devops.hw1.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BackendTest {

	@Test
	public void makeCardGetName(){
		Card c = new Card("Storm crow");
		assertEquals(c.getName(), "Storm crow");
	}

	@Test
	public void testMakeCardWithName() {
		Card c = new Card("Storm Crow");
	}
}
