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
	public void testGetContentsFromMultipleZones(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow");
		bknd.addCard(Zone.HAND, c, 0);

		Card c1 = new Card("island");
		bknd.addCard(Zone.BATTLE_FIELD, c1, 0);

		Card c2 = new Card("Scornful egotist");
		bknd.addCard(Zone.GRAVEYARD, c2, 0);

		assertEquals(c, (bknd.getZoneContents(Zone.HAND)[0]));
		assertEquals(c1, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(c2, (bknd.getZoneContents(Zone.GRAVEYARD)[0]));
	}

	@Test
	public void testGetManyZoneContents(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow");
		bknd.addCard(Zone.HAND, c, 0);

		Card c1 = new Card("island");
		bknd.addCard(Zone.HAND, c1, 1);

		Card c2 = new Card("Scornful egotist");
		bknd.addCard(Zone.HAND, c2, 2);

		assertEquals(c, (bknd.getZoneContents(Zone.HAND)[0]));
		assertEquals(c1, (bknd.getZoneContents(Zone.HAND)[1]));
		assertEquals(c2, (bknd.getZoneContents(Zone.HAND)[2]));
	}

	@Test
	public void testGetZoneContents(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow");
		bknd.addCard(Zone.HAND, c, 0);
		Card z = (bknd.getZoneContents(Zone.HAND)[0]);
		assertEquals(c, z);
	}

	@Test
	public void testGetZoneSize(){
		assertEquals(Zone.BATTLE_FIELD.getSize(), 0);

		Zone.BATTLE_FIELD.addCard(new Card("Storm Crow"), 0);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 1);

		Zone.BATTLE_FIELD.addCard(new Card("Scornful Egotist"), 1);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 2);

		Zone.BATTLE_FIELD.addCard(new Card("One With Nothing"), 2);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 3);

		Zone.BATTLE_FIELD.addCard(new Card("Sorrow's Path"), 3);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 4);

	}
}
