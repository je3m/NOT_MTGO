package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import back_end.Card;
import back_end.MTGDuelDecks;
import back_end.Zone;

public class ZoneTest {
	String[] names = {"Storm Crow",
			"Scornful Egotist", "One with Nothing", "Sorrow's Path",
	"Fleshmad Steed"};

	public void clearZones(){
		Zone.BATTLE_FIELD.empty();
		Zone.EXILE.empty();
		Zone.GRAVEYARD.empty();
		Zone.HAND.empty();
		Zone.LIBRARY.empty();
	}
	public void populateZone(Zone z){
		for (int i = 0; i < 5; i++){
			z.addCard(new Card(this.names[i], "", "", "Basic Land- Mountain", "", 
					new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), i);
		}
	}

	@Test
	public void getPlayerFromZone(){
		for(int i = 0; i < 10; i++)
			assertEquals((i < 5), Zone.getPlayerFromZone(Zone.values()[i]));
	}


	@Test
	public void testGetZoneFromString(){
		assertEquals(Zone.BATTLE_FIELD, Zone.getZoneFromString("BATTLE_FIELD"));

		try {
			Zone.getZoneFromString(null);
			fail("IllegalArgumentException expected");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Zone name string cannot be null.");
		}
		
		try{
			Zone.getZoneFromString("lawl");
			fail("no exception thrown");
		} catch (IllegalArgumentException e){
			assertEquals("invalid zone lawl", e.getMessage());
		}
	}

	@Test
	public void testGetZoneFromStringPlayer(){
		assertEquals(Zone.HAND, Zone.getZoneFromString("HAND", true));
		assertEquals(Zone.HAND1, Zone.getZoneFromString("HAND", false));
	}
	
	@Test
	public void testGetZoneFromStringPlayerNull() {
		try {
			Zone.getZoneFromString(null, false);
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Zone name string cannot be null.");
		}
	}

	@Test
	public void testAddToZone(){
		this.clearZones();

		Card c = EasyMock.niceMock(Card.class);

		Zone.HAND.addCard(c, 0);

		assert(Zone.HAND.getCards()[0] == c);

		try {
			Zone.HAND.addCard(c, -1);
			fail("did not throw error");
		} catch (IndexOutOfBoundsException e){
			assertEquals(e.getMessage(), "Index -1 is not valid for the HAND zone");
		}

		Zone.HAND.addCard(c, Zone.HAND.getSize());
		try {
			Zone.HAND.addCard(c, Zone.HAND.getSize() + 1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Index 3 is not valid for the HAND zone");
		}
	}

	@Test
	public void testClearZone(){
		this.clearZones();
		for (int i = 0; i < 5; i++){
			Zone.GRAVEYARD.addCard(new Card("Storm Crow", "", "", "Basic Land- Mountain", "", 
					new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), i);
		}

		assertEquals(Zone.GRAVEYARD.getSize(), 5);

		Zone.GRAVEYARD.empty();
		assertEquals(Zone.GRAVEYARD.getSize(), 0);
	}

	@Test
	public void testContains(){
		this.clearZones();


		for (int i = 0; i < 5; i++){
			Zone.BATTLE_FIELD.addCard(new Card(this.names[i], "", "", "Basic Land- Mountain", "", 
					new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), i);
		}

		for (int i = 0; i < 5; i++){
			assertTrue(Zone.BATTLE_FIELD.contains(this.names[i]));
		}
	}

	@Test
	public void testGetContentsFromMultipleZones(){
		this.clearZones();

		Card c = new Card("Storm crow", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Zone.HAND.addCard(c, 0);

		Card c1 = new Card("island", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Zone.BATTLE_FIELD.addCard(c1, 0);

		Card c2 = new Card("Scornful egotist", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Zone.GRAVEYARD.addCard(c2, 0);

		assertEquals(c, Zone.HAND.getCards()[0]);
		assertEquals(c1, Zone.BATTLE_FIELD.getCards()[0]);
		assertEquals(c2, Zone.GRAVEYARD.getCards()[0]);

	}

	@Test
	public void testGetIndex(){
		this.clearZones();
		this.populateZone(Zone.LIBRARY);

		for(int i = 0; i < 5; i++){
			assertEquals(i, Zone.LIBRARY.getIndex(this.names[i]));
		}

		assertEquals(-1, Zone.LIBRARY.getIndex("Break Open"));
	}


	@Test
	public void testGetManyZoneContents(){
		this.clearZones();

		Card c = new Card("Storm crow", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Zone.HAND.addCard(c, 0);

		Card c1 = new Card("island", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Zone.HAND.addCard(c1, 1);

		Card c2 = new Card("Scornful egotist", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Zone.HAND.addCard(c2, 2);

		assertEquals(c, Zone.HAND.getCards()[0]);
		assertEquals(c1, Zone.HAND.getCards()[1]);
		assertEquals(c2, Zone.HAND.getCards()[2]);
	}

	@Test
	public void testGetZoneSize(){
		this.clearZones();

		assertEquals(Zone.BATTLE_FIELD.getSize(), 0);

		Zone.BATTLE_FIELD.addCard(new Card("Storm Crow", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), 0);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 1);

		Zone.BATTLE_FIELD.addCard(new Card("Scornful Egotist", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), 1);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 2);

		Zone.BATTLE_FIELD.addCard(new Card("One With Nothing", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), 2);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 3);

		Zone.BATTLE_FIELD.addCard(new Card("Sorrow's Path", "", "", "Basic Land- Mountain", "", 
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), 3);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 4);

	}

	@Test
	public void testRemoveIndexFromZone(){
		this.clearZones();
		String[] names = {"Storm Crow",
				"Scornful Egotist", "One with Nothing", "Sorrow's Path",
		"Fleshmad Steed"};

		for (int i = 0; i < 5; i++){
			Zone.BATTLE_FIELD.addCard(new Card(names[i], "", "", "Basic Land- Mountain", "", 
					new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false), i);
		}

		assertEquals(Zone.BATTLE_FIELD.getSize(), 5);

		assertTrue(Zone.BATTLE_FIELD.contains(names[1]));

		Zone.BATTLE_FIELD.remove(0);
		assertFalse(Zone.BATTLE_FIELD.contains(names[0]));

		assertEquals(Zone.BATTLE_FIELD.getSize(), 4);

		try {
			Zone.BATTLE_FIELD.remove(10000);;
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "No object exists in the BATTLE_FIELD zone at index 10000");
		}
	}
	
	@Test
	public void testRemoveCardFromZone(){
		Zone.BATTLE_FIELD.empty();
		Card c = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 0);
		Zone.BATTLE_FIELD.addCard(c, 0);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 1);
		Zone.BATTLE_FIELD.remove(c);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 0);
	}
}
