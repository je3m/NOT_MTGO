package devops.hw1.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;

import org.easymock.EasyMock;
import org.junit.Test;

import back_end.Ability;
import back_end.AbilityType;
import back_end.Backend;
import back_end.Card;
import back_end.MTGDuelDecks;
import back_end.ManaPool;
import back_end.Phase;
import back_end.Zone;

public class BackendTest {
	@Test
	public void testCastSpellNoCost(){
		ManaPool.BLUE1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();

		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn(null);
		EasyMock.expect(c.getName()).andReturn("Storm Crow");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		try {
			bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"),c, 0, null, true, null, null, null);
			fail("Expected IllegalArgumentException");
		}catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("Illegal card Storm Crow: card cost is null"));
		}

		ManaPool.BLUE1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
	}

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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

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
	public void testActivateManaAbilityTap(){
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:U");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		new Backend().activateManaAbility(c, true);
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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		assertFalse(bknd.castSpell(Zone.HAND, new Ability("TYPE {CAST} COST {2U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "2U", true, null,null, null));
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

	@Test
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		for (int i = 0; i < 24; i++)
			bknd.passPriority(((i % 2) ==0)?(true):(false));

		ManaPool.BLUE2.add(1);
		ManaPool.COLORLESS2.add(1);
		bknd.addCard(Zone.HAND1, c, 0);
		c.setType("Creature- bird");



		assertFalse(bknd.getTurn());
		assertTrue(bknd.castSpell(Zone.HAND1,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", false, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND1),bknd.getZoneContents(Zone.HAND));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD1),bknd.getZoneContents(Zone.HAND));

		bknd.passPriority(true);
		bknd.passPriority(false);

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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		assertFalse(bknd.castSpell(Zone.HAND1,new Ability("TYPE {CAST} COST {2U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "2U", true, null,null, null));
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
	public void testBasicCastSpell4(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(2);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());

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
	public void testBasicCastSpell5(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.RED1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.RED1.getAmount());

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
	public void testBasicCastSpell6(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.WHITE1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.WHITE1.getAmount());

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
	public void testBasicCastSpell7(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.GREEN1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.GREEN1.getAmount());

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
	public void testBasicCastSpell8(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.BLACK1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.BLACK1.getAmount());

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
	public void testBasicCastSpell9(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.expect(c.getCost()).andReturn("2U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.RED1.add(1);
		ManaPool.GREEN1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {2U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "2U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.RED1.getAmount());
		assertEquals(0, ManaPool.GREEN1.getAmount());

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.RED1.getAmount());
		assertEquals(0, ManaPool.GREEN1.getAmount());

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
	public void testBasicCastSpell10(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.expect(c.getCost()).andReturn("1U");
		EasyMock.replay(c);

		ManaPool.BLUE1.add(1);
		ManaPool.RED1.add(1);
		ManaPool.GREEN1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLUE1.getAmount());
		assertEquals(0, ManaPool.RED1.getAmount());
		assertEquals(1, ManaPool.GREEN1.getAmount());

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
	public void testActivatedAbility(){
		String imperiousPerfect = "TYPE{ACTIVATED} COST {G,TAP} ZONE {BATTLE_FIELD} EFFECT {ELF_TOKEN} RESOLVE {BATTLEFIELD} TEXT {Put a 1/1 Elf Warrior token on the battlefield}";
		ArrayList<String> abilities = new ArrayList<>();
		abilities.add(imperiousPerfect);

		Card c = new Card("Imperious Perfect", null, "G", "Creature- Elf Warrior", null, abilities, 2, 2, MTGDuelDecks.FOREST_PATH, false);

		Backend bk = new Backend();
		this.resetGame();
		ManaPool.GREEN1.add(1);
		bk.addCard(Zone.BATTLE_FIELD, c, 0);

		bk.activateAbility(c, Zone.BATTLE_FIELD, 0, 0,null, null, null);

		bk.passPriority(Backend.PLAYER_ONE);
		bk.passPriority(Backend.PLAYER_TWO);
		assertEquals(2, bk.getZoneContents(Zone.BATTLE_FIELD).length);
		assertTrue(c.getTapped());

	}

	@Test
	public void testIsInteger(){
		String s = "5";
		assert(Backend.isInteger(s));

		s = "w";
		assertFalse(Backend.isInteger(s));

		s = "5w";
		assertFalse(Backend.isInteger(s));

		s = "3e";
		assertFalse(Backend.isInteger(s));
	}
	@Test
	public void testBasicCastSpell11(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("W");
		EasyMock.expect(c.getCost()).andReturn("W");
		EasyMock.expect(c.getCost()).andReturn("W");
		EasyMock.expect(c.getCost()).andReturn("W");
		EasyMock.replay(c);

		ManaPool.WHITE1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {W} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "W", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.WHITE1.getAmount());

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
	public void testBasicCastSpell12(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("B");
		EasyMock.expect(c.getCost()).andReturn("B");
		EasyMock.expect(c.getCost()).andReturn("B");
		EasyMock.expect(c.getCost()).andReturn("B");
		EasyMock.replay(c);

		ManaPool.BLACK1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {B} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "B", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.BLACK1.getAmount());

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
	public void testBasicCastSpell13(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("R");
		EasyMock.expect(c.getCost()).andReturn("R");
		EasyMock.expect(c.getCost()).andReturn("R");
		EasyMock.expect(c.getCost()).andReturn("R");
		EasyMock.replay(c);

		ManaPool.RED1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {R} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "R", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.RED1.getAmount());

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
	public void testBasicCastSpell14(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("G");
		EasyMock.expect(c.getCost()).andReturn("G");
		EasyMock.expect(c.getCost()).andReturn("G");
		EasyMock.expect(c.getCost()).andReturn("G");
		EasyMock.replay(c);

		ManaPool.GREEN1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "G", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.GREEN1.getAmount());

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
	public void testBasicCastSpell15(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.replay(c);

		ManaPool.GREEN1.add(1);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertFalse(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {GG} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "GG", true, null,null, null));
		assertEquals(1, ManaPool.GREEN1.getAmount());

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
	public void testBasicCastSpell16(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getType()).andReturn("Creature- bird");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.replay(c);

		ManaPool.GREEN1.add(2);
		bknd.addCard(Zone.HAND, c, 0);
		c.setType("Creature- bird");

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {GG} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "GG", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertEquals(c, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(0, ManaPool.GREEN1.getAmount());

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
	public void testAbilityCast(){
		Backend.getInstance().reset();
		Zone.HAND.empty();
		Zone.BATTLE_FIELD.empty();


		Card c = EasyMock.niceMock(Card.class);
		Ability castAbility = EasyMock.niceMock(Ability.class);

		EasyMock.expect(c.isFlash()).andReturn(false);
		EasyMock.expect(c.getCost()).andReturn("GG");
		EasyMock.expect(c.getCost()).andReturn("GG");

		EasyMock.expect(castAbility.getCost()).andReturn("GG");
		EasyMock.expect(castAbility.getZone()).andReturn("HAND");
		EasyMock.expect(castAbility.getResolveZone()).andReturn("BATTLE_FIELD");
		EasyMock.expect(castAbility.getResolveZone()).andReturn("BATTLE_FIELD");
		EasyMock.expect(castAbility.getType()).andReturn(AbilityType.CAST);
		EasyMock.expect(castAbility.getType()).andReturn(AbilityType.CAST);

		Ability[] abilities= new Ability[]{castAbility};

		EasyMock.expect(c.getAbilities()).andReturn(abilities);
		EasyMock.replay(castAbility);

		EasyMock.replay(c);

		ManaPool.GREEN1.add(2);
		Backend.addCard(Zone.HAND, c);
		Backend.getInstance().activateAbility(c, Zone.HAND, 0, 0,null, null, null);

		Backend.getInstance().passPriority(Backend.PLAYER_ONE);
		Backend.getInstance().passPriority(Backend.PLAYER_TWO);
		assertEquals(Backend.getInstance().getZoneContents(Zone.BATTLE_FIELD)[0], c);
	}

	@Test
	public void testCastAbilityIntegration(){
		Backend.getInstance().setPhase(Phase.FIRST_MAIN1);
		Backend.getInstance().setPriority(true);
		String cast = "COST {G}"
				+ "ZONE { HAND } " +
				"RESOLVE { BATTLEFIELD }" +
				"TYPE {CAST}";
		String mana = "COST { TAP }" +
				"EFFECT { MANA G }" +
				"ZONE {BATTLEFIELD}";

		ArrayList<String> abilities = new ArrayList<String>();
		abilities.add(cast);
		abilities.add(mana);

		Card llanowarElves = new Card("Llanowar elves", "G", "G", "Creature- Elf Druid",
				null, abilities, 1, 1, MTGDuelDecks.LLANOWAR_ELVES_PATH, false);

		Backend.addCard(Zone.HAND, llanowarElves);
		ManaPool.GREEN1.add(1);

		Backend.getInstance().activateAbility(llanowarElves, Zone.HAND, 0, 0,null, null, null);
		Backend.getInstance().passPriority(true);
		Backend.getInstance().passPriority(false);
		assertEquals(Backend.getInstance().getZoneContents(Zone.BATTLE_FIELD)[0], llanowarElves);

	}

	@Test
	public void testParseCost1(){
		try{
			int[] ans = new int[]{0,0,0,0,0,0};
			assertArrayEquals(ans, Backend.getInstance().parseCost(""));
		} catch (Exception e){
			fail("Threw exception");
		}

	}

	@Test
	public void testParseCost2() {
		try {
			int[] ans = new int[]{1, 0, 1, 0, 0, 1};
			assertArrayEquals(ans, Backend.getInstance().parseCost("1WB"));

		} catch (Exception e) {
			fail("Threw exception: " + e.getMessage());
		}
	}

	@Test
	public void resetGame(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		Zone.HAND.empty();
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD.empty();
		Zone.BATTLE_FIELD1.empty();
	}

	@Test
	public void testManaAbility(){
		String forest = "ZONE {BATTLE_FIELD} EFFECT {G} COST {TAP} TYPE {MANA}";
		ArrayList<String> abilities = new ArrayList<String>();
		abilities.add(forest);
		this.resetGame();

		Card forestCard = new Card("forest", "", "C", "Land", null, abilities, 0, 0, MTGDuelDecks.FOREST_PATH, false);
		Zone.BATTLE_FIELD.addCard(forestCard, 0);

		Backend.getInstance().activateAbility(forestCard, Zone.BATTLE_FIELD, 0, 0,null, null, null);

		assertEquals(1, ManaPool.getPool('g', Backend.PLAYER_ONE).getAmount());

		String mountain = "ZONE {BATTLE_FIELD} EFFECT {R} COST {TAP} TYPE {MANA}";
		abilities = new ArrayList<>();
		abilities.add(mountain);
		//		Backend.getInstance().setTurn(Backend.PLAYER_TWO);
		Card mountainCard = new Card("mountain", "", "C", "Land", null, abilities, 0, 0, MTGDuelDecks.MOUNTAIN_PATH, false);
		Zone.BATTLE_FIELD1.addCard(mountainCard, Zone.BATTLE_FIELD1.getSize());

		Backend.getInstance().passPriority(true);
		Backend.getInstance().activateAbility(mountainCard, Zone.BATTLE_FIELD1, 0, 0,null, null, null);
		assertEquals(1, ManaPool.getPool('r', Backend.PLAYER_TWO).getAmount());
	}

	@Test
	public void testPlayLand(){
		String forest = "ZONE {HAND} RESOLVE {BATTLE_FIELD} TYPE {PLAY}";
		ArrayList<String> abilities = new ArrayList<String>();
		abilities.add(forest);
		this.resetGame();

		Card forestCard = new Card("forest", "", "C", "Land", null, abilities, 0, 0, MTGDuelDecks.FOREST_PATH, false);

		Backend.addCard(Zone.HAND, forestCard);
		Backend.getInstance().activateAbility(forestCard, Zone.HAND, 0, 0,null, null, null);
		assertEquals(Backend.getInstance().getZoneContents(Zone.BATTLE_FIELD)[0], forestCard);
	}
	@Test
	public void testCastAbilityIntegrationPlayer2(){
		Backend.getInstance().setPhase(Phase.FIRST_MAIN1);
		String cast = "COST {R}"
				+ "ZONE { HAND } " +
				"RESOLVE { BATTLE_FIELD }" +
				"TYPE {CAST}";


		ArrayList<String> abilities = new ArrayList<String>();
		abilities.add(cast);

		Card skirkProspector = new Card("Skirk Prospector", "R", "R", "Creature- Goblin",
				null, abilities, 1, 1, MTGDuelDecks.TARFIRE_PATH, false);

		Backend.addCard(Zone.HAND1, skirkProspector);


		for (int i = 0; i < 23; i++)
			Backend.getInstance().passPriority(((i % 2) ==0)?(true):(false));

		ManaPool.RED2.add(1);

		Backend.getInstance().activateAbility(skirkProspector, Zone.HAND1, 0, 0,null, null,null);

		Backend.getInstance().passPriority(false);
		Backend.getInstance().passPriority(true);
		Backend.getInstance().passPriority(false);

		assertEquals(Backend.getInstance().getZoneContents(Zone.BATTLE_FIELD1)[0], skirkProspector);
	}

	@Test
	public void testActivateManaAbility(){
		ManaPool.GREEN1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:G");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:G");
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
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:R");
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
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:W");
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
		EasyMock.expect(c.getManaAbility()).andReturn("T:G");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:G");
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
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:U");
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
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:B");
		bknd.activateManaAbility(c, false);

		assertEquals(ManaPool.BLACK2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.BLACK2.empty();
	}

	@Test
	public void testActivateManaAbility6(){
		ManaPool.RED1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:R");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:R");
		bknd.activateManaAbility(c, true);

		assertEquals(ManaPool.RED1.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.RED1.empty();
	}

	@Test
	public void testActivateManaAbility7(){
		ManaPool.WHITE1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:W");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:W");
		bknd.activateManaAbility(c, true);

		assertEquals(ManaPool.WHITE1.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.WHITE1.empty();
	}

	@Test
	public void testActivateManaAbility8(){
		ManaPool.BLUE1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:U");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:U");
		bknd.activateManaAbility(c, true);

		assertEquals(ManaPool.BLUE1.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.BLUE1.empty();
	}

	@Test
	public void testActivateManaAbility9(){
		ManaPool.BLACK1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:B");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:B");
		bknd.activateManaAbility(c, true);

		assertEquals(ManaPool.BLACK1.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.BLACK1.empty();
	}

	@Test
	public void testActivateManaAbility10(){
		ManaPool.COLORLESS1.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:1");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:1");
		bknd.activateManaAbility(c, true);

		assertEquals(ManaPool.COLORLESS1.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.COLORLESS1.empty();
	}

	@Test
	public void testActivateManaAbility11(){
		ManaPool.COLORLESS2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:1");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.replay(c);

		c.addManaAbility("T:1");
		bknd.activateManaAbility(c, false);

		assertEquals(ManaPool.COLORLESS2.getAmount(), 1);
		EasyMock.verify(c);
		ManaPool.COLORLESS2.empty();
	}

	@Test
	public void testActivateManaAbilityAlreadyTapped(){
		ManaPool.COLORLESS2.empty();
		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		EasyMock.expect(c.getManaAbility()).andReturn("T:1");
		EasyMock.expect(c.getTapped()).andReturn(false);
		EasyMock.expect(c.getManaAbility()).andReturn("T:1");
		EasyMock.expect(c.getTapped()).andReturn(true);
		EasyMock.expect(c.getName()).andReturn("Storm Crow");
		EasyMock.replay(c);

		c.addManaAbility("T:1");
		bknd.activateManaAbility(c, false);

		assertEquals(ManaPool.COLORLESS2.getAmount(), 1);

		try{
			bknd.activateManaAbility(c, false);
			fail("Expected RejectedExecutionException");
		} catch(RejectedExecutionException e){
			assertTrue(e.getMessage().equals("Mana ability of Storm Crow cannot be activated: cannot pay cost"));
		}
	}

	@Test
	public void testAdd(){

		Backend bknd = new Backend();
		Card c = EasyMock.niceMock(Card.class);
		Card c1 = EasyMock.niceMock(Card.class);

		Backend.addCard(Zone.HAND, c);
		Backend.addCard(Zone.HAND, c1);

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
		
		try {
			bknd.addCard(Zone.HAND, c2, -1);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Backend: Index -1 is not valid for the HAND zone");
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
	public void testRemoveCardOutOfBounds1() {
		Backend bknd = new Backend();

		try {
			bknd.removeCard(Zone.HAND, -1);
			fail("Expected IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Backend: No object exists in the HAND zone at index -1");
		}
	}
	
	@Test
	public void testGetContentsFromMultipleZones(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow", "", "", "Creature- Bird", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		bknd.addCard(Zone.HAND, c, 0);

		Card c1 = new Card("island", "", "", "Basic Land- Island", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		bknd.addCard(Zone.BATTLE_FIELD, c1, 0);

		Card c2 = new Card("Scornful egotist", "", "", "Creature- Human Wizard", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		bknd.addCard(Zone.GRAVEYARD, c2, 0);

		assertEquals(c, (bknd.getZoneContents(Zone.HAND)[0]));
		assertEquals(c1, (bknd.getZoneContents(Zone.BATTLE_FIELD)[0]));
		assertEquals(c2, (bknd.getZoneContents(Zone.GRAVEYARD)[0]));
	}

	@Test
	public void testGetManyZoneContents(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow", "", "", "Creature- Bird", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		bknd.addCard(Zone.HAND, c, 0);

		Card c1 = new Card("island", "", "", "Basic Land- Island", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		bknd.addCard(Zone.HAND, c1, 1);

		Card c2 = new Card("Scornful egotist", "", "", "Creature- Human Wizard", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		bknd.addCard(Zone.HAND, c2, 2);

		assertEquals(c, (bknd.getZoneContents(Zone.HAND)[0]));
		assertEquals(c1, (bknd.getZoneContents(Zone.HAND)[1]));
		assertEquals(c2, (bknd.getZoneContents(Zone.HAND)[2]));
	}

	@Test
	public void testGetZoneContents(){
		Backend bknd = new Backend();
		Card c = new Card("Storm crow", "", "", "Creature- Bird", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
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
		bknd.passPriority(true);
		assertFalse(bknd.getPriority());
	}

	@Test
	public void testBasicPriorityPassing3(){
		Backend bknd = new Backend();
		bknd.passPriority(true);
		bknd.passPriority(false);
		assertTrue(bknd.getPriority());
	}

	@Test
	public void testComplexPriorityPassing1(){
		Backend bknd = new Backend();
		bknd.passPriority(true);
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
		assertFalse(bknd.getPriority());
	}

	@Test
	public void testComplexPriorityPassing2(){
		Backend bknd = new Backend();
		bknd.passPriority(true);
		bknd.passPriority(false);
		assertEquals(bknd.getPhase(), Phase.START_COMBAT1);
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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));

		bknd.passPriority(true);
		bknd.passPriority(false);

		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));


		bknd.passPriority(true);
		bknd.passPriority(false);
		bknd.passPriority(true);


		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
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
	public void testComplexPriorityPassing5(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));

		bknd.passPriority(true);
		bknd.passPriority(false);
		bknd.passPriority(true);
		bknd.passPriority(false);

		assertEquals(bknd.getPhase(), Phase.START_COMBAT1);
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
	public void testCastingCreatureSpell(){
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
		EasyMock.expect(c.isFlash()).andReturn(false);
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

		bknd.passPriority(true);
		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));

		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
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

	//	@Test
	//	public void testCastingCreatureSpell1(){
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//
	//		Backend bknd = new Backend();
	//		Card c = EasyMock.niceMock(Card.class);
	//		EasyMock.expect(c.isFlash()).andReturn(false);
	//		EasyMock.expect(c.getType()).andReturn("Creature- bird");
	//		EasyMock.expect(c.getCost()).andReturn("1U");
	//		EasyMock.expect(c.getCost()).andReturn("1U");
	//		EasyMock.expect(c.getCost()).andReturn("1U");
	//		EasyMock.expect(c.getCost()).andReturn("1U");
	//		EasyMock.expect(c.getCost()).andReturn("1U");
	//		EasyMock.expect(c.getCost()).andReturn("1U");
	//		EasyMock.replay(c);
	//
	//		Card c1 = EasyMock.niceMock(Card.class);
	//		EasyMock.expect(c1.isFlash()).andReturn(false);
	//		EasyMock.expect(c1.getType()).andReturn("Creature- bird");
	//		EasyMock.expect(c1.getCost()).andReturn("1U");
	//		EasyMock.expect(c1.getCost()).andReturn("1U");
	//		EasyMock.expect(c1.getCost()).andReturn("1U");
	//		EasyMock.expect(c1.getCost()).andReturn("1U");
	//		EasyMock.expect(c1.getCost()).andReturn("1U");
	//		EasyMock.expect(c1.getCost()).andReturn("1U");
	//		EasyMock.replay(c1);
	//
	//		ManaPool.BLUE1.add(1);
	//		ManaPool.COLORLESS1.add(1);
	//		bknd.addCard(Zone.HAND, c, 0);
	//		c.setType("Creature- bird");
	//
	//		ManaPool.BLUE1.add(1);
	//		ManaPool.COLORLESS1.add(1);
	//		bknd.addCard(Zone.HAND, c1, 0);
	//		c1.setType("Creature- bird");
	//
	//		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
	//		assertFalse(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c1, 0, "1U", true, null,null, null));
	//
	//		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
	//		assertTrue(bknd.getPriority());
	//
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//	}

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
		assertTrue(bknd.getTurn());
	}

	@Test
	public void testPhase1(){
		Backend bknd = new Backend();
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
	}

	@Test
	public void testPhase2(){
		Backend bknd = new Backend();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.START_COMBAT1);
	}

	@Test
	public void testPhase3(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DECLARE_ATTACKERS1);
	}

	@Test
	public void testPhase4(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.DECLARE_BLOCKERS1);
	}

	@Test
	public void testPhase5(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.COMBAT_DAMAGE1);
	}

	@Test
	public void testPhase6(){
		Backend bknd = new Backend();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		bknd.changePhase();
		assertEquals(bknd.getPhase(), Phase.END_OF_COMBAT1);
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
		assertEquals(bknd.getPhase(), Phase.SECOND_MAIN1);
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
		assertEquals(bknd.getPhase(), Phase.END1);
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
		assertEquals(bknd.getPhase(), Phase.CLEANUP1);
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
		assertEquals(bknd.getPhase(), Phase.UPKEEP2);
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
		assertEquals(bknd.getPhase(), Phase.DRAW2);
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
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN2);
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
		assertEquals(bknd.getPhase(), Phase.START_COMBAT2);
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
		assertEquals(bknd.getPhase(), Phase.DECLARE_ATTACKERS2);
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
		assertEquals(bknd.getPhase(), Phase.DECLARE_BLOCKERS2);
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
		assertEquals(bknd.getPhase(), Phase.COMBAT_DAMAGE2);
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
		assertEquals(bknd.getPhase(), Phase.END_OF_COMBAT2);
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
		assertEquals(bknd.getPhase(), Phase.SECOND_MAIN2);
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
		assertEquals(bknd.getPhase(), Phase.END2);
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
		assertEquals(bknd.getPhase(), Phase.CLEANUP2);
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
		assertEquals(bknd.getPhase(), Phase.UPKEEP1);
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
		assertEquals(bknd.getPhase(), Phase.DRAW1);
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
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
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
		assertEquals(bknd.getPhase(), Phase.START_COMBAT1);
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
		assertEquals(bknd.getPhase(), Phase.FIRST_MAIN1);
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
		Card c = new Card("Forest", "", "", "Basic Land- Forest", "T:G",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);

		Backend.addCard(Zone.BATTLE_FIELD, c);
		bknd.activateManaAbility(c, true);

		assertEquals(ManaPool.GREEN1.getAmount(), 1);
		assertTrue(c.getTapped());
		ManaPool.GREEN1.empty();
	}

	@Test
	public void testIntegrateBasicCastSpell(){
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
		Card c = new Card("Storm Crow", "1U", "U", "Creature- Bird", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);

		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

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
	public void isIntegerTest(){
		assertFalse(Backend.isInteger(""));
		assertFalse(Backend.isInteger("a"));
		assertTrue(Backend.isInteger("1"));
	}

	@Test
	public void handleGenericTest1(){
		ManaPool.WHITE1.empty();
		Backend bknd = new Backend();

		ManaPool.WHITE1.add(1);
		assertEquals(0,bknd.handleGeneric(ManaPool.WHITE1, 1));
		assertEquals(0,ManaPool.WHITE1.getAmount());
		ManaPool.WHITE1.empty();
	}

	@Test
	public void handleGenericTest2(){
		ManaPool.WHITE1.empty();
		Backend bknd = new Backend();

		ManaPool.WHITE1.add(2);
		assertEquals(0,bknd.handleGeneric(ManaPool.WHITE1, 1));
		assertEquals(1,ManaPool.WHITE1.getAmount());
		ManaPool.WHITE1.empty();
	}

	@Test
	public void handleGenericTest3(){
		ManaPool.WHITE1.empty();
		Backend bknd = new Backend();

		ManaPool.WHITE1.add(1);
		assertEquals(0,bknd.handleGeneric(ManaPool.WHITE1, 0));
		assertEquals(1,ManaPool.WHITE1.getAmount());
		ManaPool.WHITE1.empty();
	}

	@Test
	public void handleGenericTestError(){
		Backend bknd = new Backend();
		try{
			bknd.handleGeneric(ManaPool.WHITE1, -1);
			fail("Expected IllegalArguementException");
		} catch (IllegalArgumentException e){
			assertTrue(e.getMessage().equals("-1 is not a valid amount of generic mana cost."));
		}
	}

	@Test
	public void handleTargetTest1() {

		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();

		Backend bknd = new Backend();

		Card card1 = new Card("Vindicate", "1WB", "WB", "Sorcery", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Card card2 = new Card("Mountain", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);


		bknd.addCard(Zone.HAND, card1, 0);
		bknd.addCard(Zone.HAND1, card2, 0);




		ManaPool.WHITE1.add(1);
		ManaPool.BLACK1.add(1);
		ManaPool.COLORLESS1.add(1);


		assertFalse(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1WB} TARGET {PERMANENT} EFFECT {DESTROY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), card1, 0, "1WB", true, card2,null, Zone.HAND1));
		assertEquals(card1, bknd.getZoneContents(Zone.HAND)[0]);
		assertEquals(card2, bknd.getZoneContents(Zone.HAND1)[0]);
		assertEquals(1, ManaPool.WHITE1.getAmount());
		assertEquals(1, ManaPool.BLACK1.getAmount());
		assertEquals(1, ManaPool.COLORLESS1.getAmount());


		bknd.passPriority(true);
		bknd.passPriority(false);

		assertEquals(card1, bknd.getZoneContents(Zone.HAND)[0]);
		assertEquals(card2, bknd.getZoneContents(Zone.HAND1)[0]);
		assertEquals(0, ManaPool.WHITE1.getAmount());
		assertEquals(0, ManaPool.BLACK1.getAmount());
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
	public void testUntapStep() {
		Backend bknd = new Backend();

		Card c1 = new Card("Forest", "", "", "Basic Land- Forest", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Card c2 = new Card("Forest", "", "", "Basic Land- Forest", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Card c3 = new Card("Mountain", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		Card c4 = new Card("Mountain", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);

		bknd.addCard(Zone.BATTLE_FIELD, c1, 0);
		bknd.addCard(Zone.BATTLE_FIELD, c2, 0);
		bknd.addCard(Zone.BATTLE_FIELD1, c3, 0);
		bknd.addCard(Zone.BATTLE_FIELD1, c4, 0);

		c1.tap();
		c3.tap();

		for(int i = 0; i < 12; i++) {
			bknd.changePhase();
		}

		boolean anyTapped = false;
		for(Card c : Zone.BATTLE_FIELD.getCards()) {
			if(c.getTapped()) {
				anyTapped =true;
			}
		}
		for(Card c : Zone.BATTLE_FIELD1.getCards()) {
			if(c.getTapped()) {
				anyTapped =true;
			}
		}

		assertFalse(anyTapped);

	}

	@Test
	public void testCastInstant1() {

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
		Card c = new Card("Ashcoat Bear", "1U", "U", "Creature- Bear", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", true);
		bknd.changePhase();

		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

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
	public void testCastInstant2() {

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
		Card c = new Card("Ashcoat Bear", "1U", "U", "Creature- Bear", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", true);

		for(int i = 0; i <11; i++) {
			bknd.changePhase();
		}

		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

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
	public void testCastInstant3() {

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
		Card c = new Card("Ashcoat Bear", "1U", "U", "Creature- Bear", "",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", true);

		for(int i = 0; i <16; i++) {
			bknd.changePhase();
		}

		ManaPool.BLUE1.add(1);
		ManaPool.COLORLESS1.add(1);
		bknd.addCard(Zone.HAND, c, 0);

		assertTrue(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
		assertArrayEquals(bknd.getZoneContents(Zone.HAND),bknd.getZoneContents(Zone.HAND1));
		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.HAND1));

		bknd.passPriority(true);
		bknd.passPriority(false);

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

	//	@Test
	//	public void testCastSpellBadTiming1() {
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//		Zone.BATTLE_FIELD1.empty();
	//
	//		Backend bknd = new Backend();
	//		Card c = new Card("Storm Crow", "1U", "U", "Creature- Bird", "",
	//				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
	//
	//		bknd.changePhase();
	//
	//		ManaPool.BLUE1.add(1);
	//		ManaPool.COLORLESS1.add(1);
	//		bknd.addCard(Zone.HAND, c, 0);
	//
	//		assertFalse(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
	//		assertEquals(bknd.getZoneContents(Zone.HAND)[0],c);
	//		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.BATTLE_FIELD1));
	//
	//		bknd.passPriority(true);
	//		bknd.passPriority(false);
	//
	//		assertEquals(bknd.getZoneContents(Zone.HAND)[0], c);
	//		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD1), (bknd.getZoneContents(Zone.BATTLE_FIELD)));
	//		assertEquals(0, ManaPool.BLUE1.getAmount());
	//		assertEquals(0, ManaPool.COLORLESS1.getAmount());
	//
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//
	//
	//	}
	//
	//	@Test
	//	public void testCastSpellBadTiming2() {
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//
	//		Backend bknd = new Backend();
	//		Card c = new Card("Storm Crow", "1U", "U", "Creature- Bird", "",
	//				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
	//
	//		for(int i = 0; i <11; i++) {
	//			bknd.changePhase();
	//		}
	//
	//		ManaPool.BLUE1.add(1);
	//		ManaPool.COLORLESS1.add(1);
	//		bknd.addCard(Zone.HAND, c, 0);
	//
	//		assertFalse(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
	//		assertEquals(bknd.getZoneContents(Zone.HAND)[0],c);
	//		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.BATTLE_FIELD1));
	//
	//		bknd.passPriority(true);
	//		bknd.passPriority(false);
	//
	//		assertEquals(bknd.getZoneContents(Zone.HAND)[0], c);
	//		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD1), (bknd.getZoneContents(Zone.BATTLE_FIELD)));
	//		assertEquals(0, ManaPool.BLUE1.getAmount());
	//		assertEquals(0, ManaPool.COLORLESS1.getAmount());
	//
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//
	//
	//	}
	//
	//	@Test
	//	public void testCastSpellBadTiming3() {
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//
	//		Backend bknd = new Backend();
	//		Card c = new Card("Storm Crow", "1U", "U", "Creature- Bird", "",
	//				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
	//
	//		for(int i = 0; i <16; i++) {
	//			bknd.changePhase();
	//		}
	//
	//		ManaPool.BLUE1.add(1);
	//		ManaPool.COLORLESS1.add(1);
	//		bknd.addCard(Zone.HAND, c, 0);
	//
	//		assertFalse(bknd.castSpell(Zone.HAND,new Ability("TYPE {CAST} COST {1U} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), c, 0, "1U", true, null,null, null));
	//		assertEquals(bknd.getZoneContents(Zone.HAND)[0],c);
	//		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD),bknd.getZoneContents(Zone.BATTLE_FIELD1));
	//
	//		bknd.passPriority(true);
	//		bknd.passPriority(false);
	//
	//		assertEquals(bknd.getZoneContents(Zone.HAND)[0], c);
	//		assertArrayEquals(bknd.getZoneContents(Zone.BATTLE_FIELD1), (bknd.getZoneContents(Zone.BATTLE_FIELD)));
	//		assertEquals(0, ManaPool.BLUE1.getAmount());
	//		assertEquals(0, ManaPool.COLORLESS1.getAmount());
	//
	//		ManaPool.WHITE1.empty();
	//		ManaPool.BLUE1.empty();
	//		ManaPool.BLACK1.empty();
	//		ManaPool.RED1.empty();
	//		ManaPool.GREEN1.empty();
	//		ManaPool.COLORLESS1.empty();
	//		Zone.HAND.empty();
	//		Zone.HAND1.empty();
	//		Zone.BATTLE_FIELD.empty();
	//
	//
	//	}

	@Test
	public void testHandleCardClickedCase1() {
		Zone.HAND.empty();
		Zone.BATTLE_FIELD.empty();

		Card c = EasyMock.niceMock(Card.class);
		Zone.HAND.addCard(c, 0);

		assertEquals(Zone.HAND.getSize(), 1);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 0);

		Backend.handleCardClicked(Zone.HAND, 0, c);

		assertEquals(Zone.HAND.getSize(), 0);
		assertEquals(Zone.BATTLE_FIELD.getSize(), 1);

		Zone.HAND.empty();
		Zone.BATTLE_FIELD.empty();
	}

	@Test
	public void testHandleCardClickedCase2() {
		Zone.HAND1.empty();
		Zone.BATTLE_FIELD1.empty();

		Card c = EasyMock.niceMock(Card.class);
		Zone.HAND1.addCard(c, 0);

		assertEquals(Zone.HAND1.getSize(), 1);
		assertEquals(Zone.BATTLE_FIELD1.getSize(), 0);

		Backend.handleCardClicked(Zone.HAND1, 0, c);

		assertEquals(Zone.HAND1.getSize(), 0);
		assertEquals(Zone.BATTLE_FIELD1.getSize(), 1);

		Zone.HAND1.empty();
		Zone.BATTLE_FIELD1.empty();
	}

	@Test
	public void testHandleCardClickedCase3() {
		Zone.BATTLE_FIELD.empty();
		Zone.GRAVEYARD.empty();

		Card c = EasyMock.niceMock(Card.class);
		Zone.BATTLE_FIELD.addCard(c, 0);

		assertEquals(Zone.BATTLE_FIELD.getSize(), 1);
		assertEquals(Zone.GRAVEYARD.getSize(), 0);

		Backend.handleCardClicked(Zone.BATTLE_FIELD, 0, c);

		assertEquals(Zone.BATTLE_FIELD.getSize(), 0);
		assertEquals(Zone.GRAVEYARD.getSize(), 1);

		Zone.BATTLE_FIELD.empty();
		Zone.GRAVEYARD.empty();
	}

	@Test
	public void testHandleCardClickedCase4() {
		Zone.BATTLE_FIELD1.empty();
		Zone.GRAVEYARD1.empty();

		Card c = EasyMock.niceMock(Card.class);
		Zone.BATTLE_FIELD1.addCard(c, 0);

		assertEquals(Zone.BATTLE_FIELD1.getSize(), 1);
		assertEquals(Zone.GRAVEYARD1.getSize(), 0);

		Backend.handleCardClicked(Zone.BATTLE_FIELD1, 0, c);

		assertEquals(Zone.BATTLE_FIELD1.getSize(), 0);
		assertEquals(Zone.GRAVEYARD1.getSize(), 1);

		Zone.BATTLE_FIELD1.empty();
		Zone.GRAVEYARD1.empty();
	}

	@Test
	public void testHandleCardClickedInvalidZone() {
		Card c = EasyMock.niceMock(Card.class);
		try{
			Backend.handleCardClicked(Zone.EXILE, 0, c);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals(Zone.EXILE + " zone is not a valid zone for card click events."));
		}
	}

	@Test
	public void testHandleCardClickedInvalidIndex1() {
		Card c = EasyMock.niceMock(Card.class);
		try{
			Backend.handleCardClicked(Zone.HAND, -1, c);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals(-1 + " is not a valid index for card click events."));
		}
	}

	@Test
	public void testHandleCardClickedInvalidIndex2() {
		Card c = EasyMock.niceMock(Card.class);
		try{
			Backend.handleCardClicked(Zone.HAND, 1, c);
			//			fail("Expected IndexOutOfBoundsException");
		} catch(IndexOutOfBoundsException e){
			assertEquals("Card clicked could not remove card: No object exists in the HAND zone at index 1", e.getMessage());
		}
	}

	@Test
	public void testHandleCardClickedInvalidCard() {
		try{
			Backend.handleCardClicked(Zone.HAND, 0, null);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("Null is not a valid card for card click events."));
		}
	}
}
