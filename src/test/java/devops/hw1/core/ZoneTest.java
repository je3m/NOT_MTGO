package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ZoneTest {

	@Test
	public void testClearZone(){
		for (int i = 0; i < 5; i++){
			Zone.GRAVEYARD.addCard(new Card("Storm Crow"), i);
		}

		assertEquals(Zone.GRAVEYARD.getSize(), 5);

		Zone.GRAVEYARD.empty();
		assertEquals(Zone.GRAVEYARD.getSize(), 0);
	}

	@Test
	public void testContains(){
		Zone.BATTLE_FIELD.empty();
		String[] names = {"Storm Crow",
				"Scornful Egotist", "One with Nothing", "Sorrow's Path",
		"Fleshmad Steed"};

		for (int i = 0; i < 5; i++){
			Zone.BATTLE_FIELD.addCard(new Card(names[i]), i);
		}

		for (int i = 0; i < 5; i++){
			assertTrue(Zone.BATTLE_FIELD.contains(names[i]));
		}
	}


	//	@Test
	//	public void testRemoveIndexFromZone(){
	//		Zone.BATTLE_FIELD.empty();
	//
	//		for (int i = 0; i < 5; i++){
	//			Zone.BATTLE_FIELD.addCard(new Card("Storm Crow"), i);
	//		}
	//
	//		assertEquals(Zone.BATTLE_FIELD.getSize(), 5);
	//
	//		Zone.BATTLE_FIELD.remove(0);
	//		assertEquals(Zone.BATTLE_FIELD.getSize(), 4);
	//	}

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
