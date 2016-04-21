package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.junit.Assert;
import org.junit.Test;

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
	public void makeCardGetName(){
		Card c = new Card("Storm Crow");
		assertEquals(c.getName(), "Storm Crow");
	}

	@Test
	public void testTapTwice(){
		Card c = new Card("Scornful Egotist");
		assert(c.tap());
		assert(!c.tap());
	}

	@Test
	public void testAbilities(){
		Card c;

		c = new Card("Scornful Egotist");
		c.addAbility("Morph U");
		assertEquals(c.getAbilities()[0], "Morph U");

		c = new Card("Storm Crow");
		c.addAbility("flying");
		assertEquals(c.getAbilities()[0], "flying");

		String s[] = {"FirstStrike", "attackingCreaturesHaveFirstStrike RT"};
		c = new Card("Akki Coalflinger");
		c.addAbility(s[0]);
		c.addAbility(s[1]);
		Assert.assertArrayEquals(c.getAbilities(), s);

	}

	@Test
	public void testTap1(){
		Card c = new Card("Forest");
		assertFalse(c.getTapped());
	}

	@Test
	public void testTap2(){
		Card c = new Card("Forest");
		c.tap();
		assertTrue(c.getTapped());
	}

	@Test
	public void testTap3(){
		Card c = new Card("Forest");
		c.tap();
		c.untap();
		assertFalse(c.getTapped());
	}

	@Test
	public void testManaAbility(){
		Card c = new Card("Forest");
		c.addManaAbility("T:G");
		assertEquals(c.getManaAbility(), "T:G");
	}

	@Test
	public void testColor(){
		Card c;

		c = new Card("Storm Crow");
		c.setColor("U");
		assertEquals(c.getColor(), "U");

		c = new Card("One with Nothing");
		c.setColor("B");
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
		c = new Card("Storm Crow");
		c.setImage("res/storm_crow.jpg");
		assertEquals(c.getImage(), "res/storm_crow.jpg");

		c = new Card("Scornful Egotist");
		c.setImage("res/scornful_egotist.jpg");
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

		c = new Card("Storm Crow");
		c.setCost("1U");
		assertEquals(c.getCost(), "1U");

		c = new Card("Scornful Egotist");
		c.setCost("7U");
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

		c = new Card("Storm Crow");
		c.setPower(1);
		c.setToughness(2);

		assertEquals(c.getPower(), 1);
		assertEquals(c.getToughness(), 2);

		c = new Card("Scornful Egotist");
		c.setPower(1);
		c.setToughness(1);

		assertEquals(c.getPower(), 1);
		assertEquals(c.getToughness(), 1);

		c = new Card("Fleshmad Steed");
		c.setPT(2, 2);

		assertEquals(c.getPower(), 2);
		assertEquals(c.getToughness(), 2);


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

		c = new Card("Storm Crow");
		c.setType("Creature- Bird");
		assertEquals(c.getType(), "Creature- Bird");

		c = new Card("Scornful Egotist");
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
	}
	
	@Test 
	public void testIsFlash() {
		Card c;
		
		c= new Card("Ashcoat Bear");
		c.setType("Creature- Bear");
		c.setFlash(true);
		
		assertTrue(c.isFlash());
		
		c= new Card("Storm Crow");
		c.setType("Creature- Bird");
		
		assertFalse(c.isFlash());
		
		c = new Card("Vindicate");
		c.setType("Sorcery");
		c.setFlash(true);
		
		assertTrue(c.isFlash());
		
		c= new Card("Lightning Bolt");
		c.setType("Instant");
		
		assertTrue(c.isFlash());
		
		c = new Card("Ludicrous Speed!");
		c.setType("Instant");
		c.setFlash(true);
		
		assertTrue(c.isFlash());
	}
}
