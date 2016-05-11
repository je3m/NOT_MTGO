package integration_test;

import static org.junit.Assert.assertEquals;

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

}
