package integration_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import back_end.Ability;
import back_end.AbilityType;
import back_end.Card;
import back_end.ItemOnStack;
import back_end.MTGDuelDecks;

public class ItemOnStackCardAbilityIntegration {

	@Test
	public void IntegrateWithAbility(){
		Card hand3 = new Card("Arbor Elf", "G", "G", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 1, 1, MTGDuelDecks.LLANOWAR_ELVES_PATH, false);

		hand3.addAbility("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}");

		ItemOnStack item = new ItemOnStack(null, new Ability("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}"), true, null, true, null);

		assertEquals(AbilityType.CAST,item.getAbility().getType());

		assertEquals("G", item.getAbility().getCost());

		assertEquals("HAND", item.getAbility().getZone());

		assertEquals("BATTLE_FIELD", item.getAbility().getResolveZone());

		assertEquals("CAST", item.getAbility().getText());


	}

	@Test
	public void IntegrateWtihCard(){
		Card c = new Card("Arbor Elf", "G", "G", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 1, 1, MTGDuelDecks.LLANOWAR_ELVES_PATH, false);

		ItemOnStack item = new ItemOnStack(c, null, true, null, false, null);

		assertTrue(item.getCard().getName().equals("Arbor Elf"));
		assertTrue(item.getCard().getCost().equals("G"));
		assertTrue(item.getCard().getColor().equals("G"));
		assertTrue(item.getCard().getType().equals("Creature- Elf Druid"));
		assertEquals(item.getCard().getManaAbility(), "T:G");
		assertEquals(item.getCard().getAbilities().length, 0);
		assertEquals(item.getCard().getPower(),1);
		assertEquals(item.getCard().getToughness(),1);
		assertTrue(item.getCard().getImage().equals(MTGDuelDecks.LLANOWAR_ELVES_PATH));
	}

}
