package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.junit.Test;

import back_end.Card;

public class TestCard {

	@Test
	public void makeCardSimpleTest(){
		Card c = new Card("Storm Crow","1U", "U", "Creature- Bird", null, new ArrayList<String>(), 1, 1, "res/Mountain.jpg", null);
		assertTrue(c.getName().equals("Storm Crow"));
		assertTrue(c.getCost().equals("1U"));
		assertTrue(c.getColor().equals("U"));
		assertTrue(c.getType().equals("Creature- Bird"));
		assertEquals(c.getManaAbility(),null);
		assertEquals(c.getAbilities().length,0);
		assertEquals(c.getPower(),1);
		assertEquals(c.getToughness(),1);
		assertTrue(c.getImage().equals("res/Mountain.jpg"));
	}

	@Test
	public void makeCardSimpleTest1(){
		try{
			Card c = new Card("Storm Crow","1U", "U", "Creature- Bird", null, null, 1, 1, "res/Mountain.jpg", null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Error creating card Storm Crow: null is not a valid ability list");
		}
	}

	@Test
	public void makeCardSimpleTest2(){
		try{
			Card c = new Card("Storm Crow","1U", "U", null, null, new ArrayList<String>(), 1, 1, "res/Mountain.jpg", null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Error creating card Storm Crow: Card Storm Crow: null is not a valid card type");
		}
	}

	@Test
	public void makeCardGetName(){
		Card c = new Card("Storm Crow", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertEquals(c.getName(), "Storm Crow");
	}

	@Test
	public void testTapTwice(){
		Card c = new Card("Scornful Egotist", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertTrue(c.tap());
		assertTrue(!c.tap());
	}

	@Test
	public void testAbilities(){
		//		Card c;
		//
		//		c = new Card("Scornful Egotist", "", "", "Basic Land- Mountain", "",
		//				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		//		c.addAbility("Morph U");
		//		assertEquals(c.getAbilities()[0], "Morph U");
		//
		//		c = new Card("Storm Crow", "", "", "Basic Land- Mountain", "",
		//				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		//		c.addAbility("flying");
		//		assertEquals(c.getAbilities()[0], "flying");
		//
		//		String s[] = {"FirstStrike", "attackingCreaturesHaveFirstStrike RT"};
		//		c = new Card("Akki Coalflinger", "", "", "Basic Land- Mountain", "",
		//				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		//		c.addAbility(s[0]);
		//		c.addAbility(s[1]);
		//		Assert.assertArrayEquals(c.getAbilities(), s);

	}

	@Test
	public void testTap1(){
		Card c = new Card("Forest", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertFalse(c.getTapped());
	}

	@Test
	public void testTap2(){
		Card c = new Card("Forest", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		c.tap();
		assertTrue(c.getTapped());
	}

	@Test
	public void testTap3(){
		Card c = new Card("Forest", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		c.tap();
		c.untap();
		assertFalse(c.getTapped());
	}

	@Test
	public void testManaAbility(){
		Card c = new Card("Forest", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertEquals(c.getManaAbility(), "T:G");
	}

	@Test
	public void testColor(){
		Card c;

		c = new Card("Storm Crow", "", "U", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertEquals(c.getColor(), "U");

		c = new Card("One with Nothing", "", "B", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertEquals(c.getColor(), "B");

		try {
			c.setColor("O");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card One with Nothing: O is not a valid color");
		}

		try {
			c.setColor("BW");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card One with Nothing: BW is not a valid color");
		}

		try {
			c.setColor("BB");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card One with Nothing: BB is not a valid color");
		}
	}

	@Test
	public void testImage(){
		Card c;
		c = new Card("Storm Crow", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/storm_crow.jpg", false);
		assertEquals(c.getImage(), "res/storm_crow.jpg");

		c = new Card("Scornful Egotist", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		assertEquals(c.getImage(), "res/scornful_egotist.jpg");


		try{
			c.setImage("saeiwqrowd");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Card Scornful Egotist: saeiwqrowd is not a valid file name");
		}
	}

	@Test
	public void testManaCostGetSet(){
		Card c;

		c = new Card("Storm Crow", "1U", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		assertEquals(c.getCost(), "1U");

		c = new Card("Scornful Egotist", "7U", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		assertEquals(c.getCost(), "7U");

		c.setCost("7GG");
		assertEquals(c.getCost(), "7GG");

		try {
			c.setCost("G2");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: G2 is not a valid mana cost");
		}

		try {
			c.setCost("GW");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: GW is not a valid mana cost");
		}

	}

	@Test
	public void testPowerToughness(){
		Card c;

		c = new Card("Storm Crow", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 1, 2, "res/scornful_egotist.jpg", false);

		assertEquals(c.getPower(), 1);
		assertEquals(c.getToughness(), 2);

		c = new Card("Scornful Egotist", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 1, 1, "res/scornful_egotist.jpg", false);

		assertEquals(c.getPower(), 1);
		assertEquals(c.getToughness(), 1);

		c = new Card("Fleshmad Steed", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		c.setPT(2, 2);

		assertEquals(c.getPower(), 2);
		assertEquals(c.getToughness(), 2);


	}

	@Test
	public void testToString(){
		assertEquals("Storm Crow", new Card("Storm Crow", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false).toString());
		assertEquals("Scornful Egotist", new Card("Scornful Egotist", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false).toString());
		assertEquals("One With Nothing", new Card("One With Nothing", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false).toString());
	}

	@Test
	public void testType(){
		Card c;

		c = new Card("Storm Crow", "", "", "Creature- Bird", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		assertEquals(c.getType(), "Creature- Bird");

		c = new Card("Scornful Egotist", "", "", "Creature- Human Wizard", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		c.setType("Creature- Human Wizard");
		assertEquals(c.getType(), "Creature- Human Wizard");

		try {
			c.setType("ewonddl--");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: ewonddl-- is not a valid card typeline");
		}

		try {
			c.setType("Creature-");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: Creature- is not a valid card typeline");
		}

		try {
			c.setType("Creature- ");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: Creature-  is not a valid card typeline");
		}

		try {
			c.setType("Creature -");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: Creature - is not a valid card typeline");
		}

		try {
			c.setType("");
			fail("Expected PatternSyntaxException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist:  is not a valid card typeline");
		}

		try {
			c.setType(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Card Scornful Egotist: null is not a valid card type");
		}

		try {
			c.setType("c");
			fail("Expected IllegalArgumentException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: c is not a valid card typeline");
		}

		try {
			c.setType("Creature- goblin");
			fail("Expected IllegalArgumentException");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "Card Scornful Egotist: Creature- goblin is not a valid card typeline");
		}

		c = new Card("Forest", "", "", "Basic Land", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);
		assertEquals(c.getType(), "Basic Land");
	}

	@Test
	public void testIsFlash() {
		Card c;

		c= new Card("Ashcoat Bear", "", "", "Creature- Bear", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", true);

		assertTrue(c.isFlash());

		c= new Card("Storm Crow", "", "", "Creature- Bird", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);

		assertFalse(c.isFlash());

		c = new Card("Vindicate", "", "", "Sorcery", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", true);

		assertTrue(c.isFlash());

		c= new Card("Lightning Bolt", "", "", "Instant", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", false);

		assertTrue(c.isFlash());

		c = new Card("Ludicrous Speed!", "", "", "Instant", "T:G",
				new ArrayList<String>(), 0, 0, "res/scornful_egotist.jpg", true);

		assertTrue(c.isFlash());
	}
}
