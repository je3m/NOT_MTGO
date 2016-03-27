package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
			z.addCard(new Card(this.names[i]), i);
		}
	}

	@Test
	public void testAddToZone(){
		
		try {
			Zone.BATTLE_FIELD.addCard(new Card(this.names[2]), 10000);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Index 10000 is not valid for the BATTLE_FIELD zone");
		}
	}
	
	@Test
	public void testClearZone(){
		this.clearZones();
		for (int i = 0; i < 5; i++){
			Zone.GRAVEYARD.addCard(new Card("Storm Crow"), i);
		}

		assertEquals(Zone.GRAVEYARD.getSize(), 5);

		Zone.GRAVEYARD.empty();
		assertEquals(Zone.GRAVEYARD.getSize(), 0);
	}

	@Test
	public void testContains(){
		this.clearZones();


		for (int i = 0; i < 5; i++){
			Zone.BATTLE_FIELD.addCard(new Card(this.names[i]), i);
		}

		for (int i = 0; i < 5; i++){
			assertTrue(Zone.BATTLE_FIELD.contains(this.names[i]));
		}
	}

	@Test
	public void testGetContentsFromMultipleZones(){
		this.clearZones();

		Card c = new Card("Storm crow");
		Zone.HAND.addCard(c, 0);

		Card c1 = new Card("island");
		Zone.BATTLE_FIELD.addCard(c1, 0);

		Card c2 = new Card("Scornful egotist");
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

		Card c = new Card("Storm crow");
		Zone.HAND.addCard(c, 0);

		Card c1 = new Card("island");
		Zone.HAND.addCard(c1, 1);

		Card c2 = new Card("Scornful egotist");
		Zone.HAND.addCard(c2, 2);

		assertEquals(c, Zone.HAND.getCards()[0]);
		assertEquals(c1, Zone.HAND.getCards()[1]);
		assertEquals(c2, Zone.HAND.getCards()[2]);
	}

	@Test
	public void testGetZoneSize(){
		this.clearZones();

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

	@Test
	public void testRemoveIndexFromZone(){
		this.clearZones();
		String[] names = {"Storm Crow",
				"Scornful Egotist", "One with Nothing", "Sorrow's Path",
		"Fleshmad Steed"};

		for (int i = 0; i < 5; i++){
			Zone.BATTLE_FIELD.addCard(new Card(names[i]), i);
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
}
