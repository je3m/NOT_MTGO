package devops.hw1.core;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Test;

public class BackendTest {



	@Test
	public void testActivateAbility(){
		Backend bknd = new Backend();
		Card c = new Card("Storm Crow");
		bknd.addCard(Zone.HAND, c);


		bknd.activateAbility(c, Zone.HAND, 0);

		assert(Zone.BATTLE_FIELD.contains(c.getName()));

		Card c1 = new Card("Scornful Egotist");

		bknd.addCard(Zone.HAND1, c1);
		bknd.activateAbility(c1, Zone.HAND1, 0);
		assert(Zone.BATTLE_FIELD1.contains(c1.getName()));
	}

	@Test
	public void testAdd(){

		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		Card c1 = EasyMock.niceMock(Card.class);

		bknd.addCard(Zone.HAND, c);
		bknd.addCard(Zone.HAND, c1);

		Zone.HAND.addCard(c, 0);
		Zone.HAND.addCard(c, 0);



		assertEquals(c, bknd.getZoneContents(Zone.HAND)[0]);
		assertEquals(c, bknd.getZoneContents(Zone.HAND)[1]);
		
		Card c2 = EasyMock.niceMock(Card.class);
		
		try {
			bknd.addCard(Zone.HAND, c2, 10000);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Backend: Index 10000 is not valid for the HAND zone");
		}
	}
	
	
	@Test
	public void testRemoveCardOutOfBounds() {
		Backend bknd = new Backend();
		
		try {
			bknd.removeCard(Zone.HAND, 10000);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Backend: No object exists in the HAND zone at index 10000");
		}
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
	public void testBasicPriorityPassing1(){
		Backend bknd = new Backend();
		assertTrue(bknd.getPriority());
	}
	
	@Test
	public void testBasicPriorityPassing2(){
		Backend bknd = new Backend();
		bknd.passPriority();
		assertFalse(bknd.getPriority());
	}
	
	@Test
	public void testBasicPriorityPassing3(){
		Backend bknd = new Backend();
		bknd.passPriority();
		bknd.passPriority();
		assertTrue(bknd.getPriority());
	}
	
	@Test
	public void testComplexPriorityPassing1(){
		Backend bknd = new Backend();
		bknd.passPriority();
		assertEquals(bknd.getPhase(), Phase.UNTAP1);
		assertFalse(bknd.getPriority());
	}
	
	@Test
	public void testComplexPriorityPassing2(){
		Backend bknd = new Backend();
		bknd.passPriority();
		bknd.passPriority();
		assertEquals(bknd.getPhase(), Phase.UPKEEP1);
		assertTrue(bknd.getPriority());
	}
	
	@Test
	public void testTurn1(){
		Backend bknd = new Backend();
		assertTrue(bknd.getTurn());
	}
	
	@Test
	public void testTurn2(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertTrue(bknd.getTurn());
	}
	
	@Test
	public void testTurn3(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertFalse(bknd.getTurn());
	}
	
	@Test
	public void testTurn4(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertFalse(bknd.getTurn());
	}
	
	@Test
	public void testTurn5(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertTrue(bknd.getTurn());
	}
	
	@Test
	public void testPhase1(){
		Backend bknd = new Backend();
		assertEquals(bknd.getPhase(), Phase.UNTAP1);
	}
	
	@Test
	public void testPhase2(){
		Backend bknd = new Backend();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.UPKEEP1);
	}
	
	@Test
	public void testPhase3(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DRAW1);
	}
	
	@Test
	public void testPhase4(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
	}
	
	@Test
	public void testPhase5(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.START_COMBAT1);
	}
	
	@Test
	public void testPhase6(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DECLARE_ATTACKERS1);
	}
	
	@Test
	public void testPhase7(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DECLARE_BLOCKERS1);
	}
	
	@Test
	public void testPhase8(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.COMBAT_DAMAGE1);
	}
	
	@Test
	public void testPhase9(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.END_OF_COMBAT1);
	}
	
	@Test
	public void testPhase10(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.SECOND_MAIN1);
	}
	
	@Test
	public void testPhase11(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.END1);
	}
	
	@Test
	public void testPhase12(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.CLEANUP1);
	}
	
	@Test
	public void testPhase13(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.UNTAP2);
	}
	
	@Test
	public void testPhase14(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.UPKEEP2);
	}
	
	@Test
	public void testPhase15(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DRAW2);
	}
	
	@Test
	public void testPhase16(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN2);
	}
	
	@Test
	public void testPhase17(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.START_COMBAT2);
	}
	
	@Test
	public void testPhase18(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DECLARE_ATTACKERS2);
	}
	
	@Test
	public void testPhase19(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DECLARE_BLOCKERS2);
	}
	
	@Test
	public void testPhase20(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.COMBAT_DAMAGE2);
	}
	
	@Test
	public void testPhase21(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.END_OF_COMBAT2);
	}
	
	@Test
	public void testPhase22(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.SECOND_MAIN2);
	}
	
	@Test
	public void testPhase23(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.END2);
	}
	
	@Test
	public void testPhase24(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.CLEANUP2);
	}
		
		@Test
		public void testPhaseReset(){
			Backend bknd = new Backend();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			bknd.changePhase();
			assertEquals(bknd.getPhase(), Phase.UNTAP1);
	}
}
