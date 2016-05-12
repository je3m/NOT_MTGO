package integration_test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import back_end.AbilityType;
import back_end.Card;
import back_end.MTGDuelDecks;

public class CardAbilityIntegration {

	@Test
	public void testManaAbility(){
		Card c = new Card("Forest", "", "", "Basic Land- Mountain", "T:G",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		assertEquals(c.getManaAbility(), "T:G");
	}

	@Test
	public void testConstructorWithAbility(){
		Card hand3 = new Card("Arbor Elf", "G", "G", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 1, 1, MTGDuelDecks.LLANOWAR_ELVES_PATH, false);
		hand3.addAbility("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}");

		assertEquals(AbilityType.CAST, hand3.getAbilities()[0].getType());

		assertEquals("G", hand3.getAbilities()[0].getCost());

		assertEquals("HAND", hand3.getAbilities()[0].getZone());

		assertEquals("BATTLE_FIELD", hand3.getAbilities()[0].getResolveZone());

		assertEquals("CAST", hand3.getAbilities()[0].getText());

	}

}
