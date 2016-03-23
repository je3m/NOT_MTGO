package devops.hw1.core;

import static org.junit.Assert.assertEquals;

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
