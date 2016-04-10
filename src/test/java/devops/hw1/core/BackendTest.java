package devops.hw1.core;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.Test;

public class BackendTest {
	@Test
	public void testBasicCastSpell(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
		
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);
		
		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");
		c.setColor("1U");
		
		assertTrue(bknd.castSpell(Zone.HAND, c, 0, true));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));
		
		bknd.passPriority();
		bknd.passPriority();
		
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.COLORLESS1.getAmount());
		
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
	}
	
	@Test
	public void testBasicCastSpell1(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
		
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.replay(c);
		
		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");
		c.setColor("2U");
		
		assertFalse(bknd.castSpell(Zone.HAND, c, 0, true));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.HAND)[0]));
		assertEquals(1, ManaPool.BLUE1.getAmount());
		assertEquals(1, ManaPool.COLORLESS1.getAmount());
		
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
	}
	
	public void testBasicCastSpell2(){
		ManaPool.WHITE2.empty();
		ManaPool.BLUE2.empty();
		ManaPool.BLACK2.empty();
		ManaPool.RED2.empty();
		ManaPool.GREEN2.empty();
		ManaPool.COLORLESS2.empty();
		Zone.HAND1.empty();
		Zone.HAND.empty();
		Zone.BATTLE_FIELD1.empty();
		
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);
		
		ManaPool.BLUE2.add(1);
		ManaPool.COLORLESS2.add(1);
		bknd.addCard(Zone.HAND1, c, 0);
		c.setType("Creature- bird");
		c.setColor("1U");
		
		assertTrue(bknd.castSpell(Zone.HAND1, c, 0, true));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND1),bknd.getZoneContents(Zone.HAND));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD1),bknd.getZoneContents(Zone.HAND));
		
		bknd.passPriority();
		bknd.passPriority();
		
		assertArrayEquals(bknd.getZoneContents(Zone.HAND1),bknd.getZoneContents(Zone.HAND));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD1)[0]));
		assertEquals(0, ManaPool.BLUE2.getAmount());
		assertEquals(0, ManaPool.COLORLESS2.getAmount());
		
		ManaPool.WHITE2.empty();
		ManaPool.BLUE2.empty();
		ManaPool.BLACK2.empty();
		ManaPool.RED2.empty();
		ManaPool.GREEN2.empty();
		ManaPool.COLORLESS2.empty();
		Zone.HAND1.empty();
		Zone.HAND.empty();
		Zone.BATTLE_FIELD1.empty();
	}
	
	@Test
	public void testBasicCastSpell3(){
		ManaPool.WHITE2.empty();
		ManaPool.BLUE2.empty();
		ManaPool.BLACK2.empty();
		ManaPool.RED2.empty();
		ManaPool.GREEN2.empty();
		ManaPool.COLORLESS2.empty();
		Zone.HAND1.empty();
		Zone.HAND.empty();
		Zone.BATTLE_FIELD1.empty();
		
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.replay(c);
		
		ManaPool.BLUE2.add(1);
		ManaPool.COLORLESS2.add(1);
		bknd.addCard(Zone.HAND1, c, 0);
		c.setType("Creature- bird");
		c.setColor("2U");
		
		assertFalse(bknd.castSpell(Zone.HAND1, c, 0, true));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD1),bknd.getZoneContents(Zone.HAND));
		assertEquals(c, (bknd.getZoneContents(Zone.HAND1)[0]));
		assertEquals(1, ManaPool.BLUE2.getAmount());
		assertEquals(1, ManaPool.COLORLESS2.getAmount());
		
		ManaPool.WHITE2.empty();
		ManaPool.BLUE2.empty();
		ManaPool.BLACK2.empty();
		ManaPool.RED2.empty();
		ManaPool.GREEN2.empty();
		ManaPool.COLORLESS2.empty();
		Zone.HAND1.empty();
		Zone.HAND.empty();
		Zone.BATTLE_FIELD1.empty();
	}
	
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
	public void testActivateManaAbility(){
		ManaPool.GREEN1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:G");
		EasyMock.replay(c);
		
		c.addManaAbility("T:G");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, true);
		
		assertEquals(ManaPool.GREEN1.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.GREEN1.empty();
	}
	
	@Test
	public void testActivateManaAbility1(){
		ManaPool.RED2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:R");
		EasyMock.replay(c);
		
		c.addManaAbility("T:R");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, false);
		
		assertEquals(ManaPool.RED2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.RED2.empty();
	}
	
	@Test
	public void testActivateManaAbility2(){
		ManaPool.WHITE2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:W");
		EasyMock.replay(c);
		
		c.addManaAbility("T:W");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, false);
		
		assertEquals(ManaPool.WHITE2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.WHITE2.empty();
	}
	
	@Test
	public void testActivateManaAbility3(){
		ManaPool.GREEN2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("G:R");
		EasyMock.replay(c);
		
		c.addManaAbility("G:R");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, false);
		
		assertEquals(ManaPool.GREEN2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.GREEN2.empty();
	}
	
	@Test
	public void testActivateManaAbility4(){
		ManaPool.BLUE2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:U");
		EasyMock.replay(c);
		
		c.addManaAbility("T:U");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, false);
		
		assertEquals(ManaPool.BLUE2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.BLUE2.empty();
	}
	
	@Test
	public void testActivateManaAbility5(){
		ManaPool.BLACK2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:B");
		EasyMock.replay(c);
		
		c.addManaAbility("T:B");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, false);
		
		assertEquals(ManaPool.BLACK2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.BLACK2.empty();
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
	public void testComplexPriorityPassing3(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
		
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);
		
		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");
		c.setColor("1U");
		
		bknd.passPriority();
		
		assertTrue(bknd.castSpell(Zone.HAND, c, 0, true));
		
		bknd.passPriority();
		
		assertEquals(bknd.getPhase(), Phase.UNTAP1);
		assertTrue(bknd.getPriority());
		
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
	}
	
	@Test
	public void testComplexPriorityPassing4(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
		
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);
		
		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");
		c.setColor("1U");
		
		bknd.passPriority();
		
		assertTrue(bknd.castSpell(Zone.HAND, c, 0, true));
		
		bknd.passPriority();
		bknd.passPriority();
		
		assertEquals(bknd.getPhase(), Phase.UNTAP1);
		assertFalse(bknd.getPriority());
		
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
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
	
	/**
	 * Integration Test
	 */
	@Test
	public void testEmptyManaPhases(){
		Backend bknd = new Backend();
		ManaPool.RED1.add(2);
		bknd.changePhase();
		assertEquals(ManaPool.RED1.getAmount(), 0);
	}
	
	@Test
	public void testEmptyManaPhases1(){
		Backend bknd = new Backend();
		ManaPool.RED1.add(2);
		ManaPool.RED2.add(2);
		bknd.changePhase();
		assertEquals(ManaPool.RED1.getAmount(), 0);
		assertEquals(ManaPool.RED2.getAmount(), 0);
	}
	
	@Test
	public void testIntegrateActivateManaAbility(){
		ManaPool.GREEN1.empty();
		Backend bknd = new Backend();
		Card c = new Card("Forest");
		
		c.addManaAbility("T:G");
		bknd.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, true);
		
		assertEquals(ManaPool.GREEN1.getAmount(), 1);
		assertTrue(c.getTapped());
		ManaPool.GREEN1.empty();
	}
}
