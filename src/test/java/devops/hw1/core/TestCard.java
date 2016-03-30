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
	public void testColor(){
		Card c;

		c = new Card("Storm crow");
		c.setColor("U");
		assertEquals(c.getColor(), "U");

		c = new Card("One with Nothing");
		c.setColor("B");
		assertEquals(c.getColor(), "B");
	}

	@Test
	public void testManaCostGetSet(){
		Card c;

		c = new Card("Storm crow");
		c.setCost("1U");
		assertEquals(c.getCost(), "1U");

		c = new Card("Scornful Egotist");
		c.setCost("7U");
		assertEquals(c.getCost(), "7U");

	}

	@Test
	public void testToString(){
		assertEquals("Storm Crow", new Card("Storm Crow").toString());
		assertEquals("Scornful Egotist", new Card("Scornful Egotist").toString());
		assertEquals("One With Nothing", new Card("One With Nothing").toString());
	}

	@Test
	public void testType(){
		Card c;

		c = new Card("Storm crow");
		c.setType("Creature- Bird");
		assertEquals(c.getType(), "Creature- Bird");

		c = new Card("Scornful Egotist");
		c.setType("Creature- Human Wizard");
		assertEquals(c.getType(), "Creature- Human Wizard");

	}
}
