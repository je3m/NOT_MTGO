package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import back_end.Ability;
import back_end.AbilityType;

public class AbilityTest {

	@Test
	public void testGetCost() {
		String smel = "COST {7U}";
		Ability parse = new Ability(smel);

		assertEquals(parse.getCost(), "7U");

		smel = "COST {B}";
		parse = new Ability(smel);

		assertEquals("B", parse.getCost());

		smel = "";
		parse = new Ability(smel);

		try{
			parse.getCost();
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}


		try{
			parse = new Ability(null);
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}
	}

	@Test
	public void testNulls(){
		String smel = "ZONE {HAND} RESOLVE {BATTLEFIELD}";
		Ability a = new Ability(smel);
		assertEquals(null, a.getCost());

	}

	@Test
	public void testCostEfectParse(){
		String LlanowarElves = "COST { TAP } EFFECT { MANA G}";
		Ability parse = new Ability(LlanowarElves);

		assertEquals("TAP", parse.getCost());
		assertEquals("MANAG", parse.getEffect());
	}

	@Test
	public void testTarget(){
		String giantGrowth = "COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE }";
		Ability parse = new Ability(giantGrowth);

		assertEquals("CREATURE", parse.getTarget());

	}

	@Test
	public void testZones(){
		String giantGrowth = "COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE } ZONE { HAND } RESOLVE { GRAVEYARD }";
		Ability parse = new Ability(giantGrowth);

		assertEquals("HAND", parse.getZone());
		assertEquals("GRAVEYARD", parse.getResolveZone());

	}

	@Test
	public void testType(){
		String giantGrowth = "COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE } ZONE { HAND } RESOLVE { GRAVEYARD } TYPE { CAST }";
		Ability parse = new Ability(giantGrowth);

		assertEquals(AbilityType.CAST, parse.getType());
	}

	@Test
	public void testDisplayText(){
		String giantGrowth = "TEXT { Cast } COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE } ZONE { HAND } RESOLVE { GRAVEYARD } TYPE { CAST }";
		Ability parse = new Ability(giantGrowth);

		assertEquals("Cast", parse.getText());

		String elvishWarrior = "COST { GG} ZONE {HAND} RESOLVE { BATTLEFIELD} TYPE {CAST}";
		parse = new Ability(elvishWarrior);

		assertEquals("Cast", parse.getText());

	}
}
