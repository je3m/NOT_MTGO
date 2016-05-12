package integration_test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import back_end.AbilityType;
import back_end.Backend;
import back_end.Card;
import back_end.MTGDuelDecks;
import back_end.ManaPool;
import back_end.Zone;

public class IntegrateBackend {

	@Test
	public void backendWithZone(){
		Backend b = Backend.getInstance();

		Card hand3 = new Card("Arbor Elf", "G", "G", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 1, 1, MTGDuelDecks.LLANOWAR_ELVES_PATH, false);

		hand3.addAbility("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}");

		b.addCard(Zone.BATTLE_FIELD, hand3, 0);


		assertEquals(AbilityType.CAST, b.getZoneContents(Zone.BATTLE_FIELD)[0].getAbilities()[0].getType());

		assertEquals("G", b.getZoneContents(Zone.BATTLE_FIELD)[0].getAbilities()[0].getCost());

		assertEquals("HAND", b.getZoneContents(Zone.BATTLE_FIELD)[0].getAbilities()[0].getZone());

		assertEquals("BATTLE_FIELD", b.getZoneContents(Zone.BATTLE_FIELD)[0].getAbilities()[0].getResolveZone());

		assertEquals("CAST", b.getZoneContents(Zone.BATTLE_FIELD)[0].getAbilities()[0].getText());

	}

	@Test
	public void backendWithManaZoneStack(){
		Backend b = new Backend();

		Card hand3 = new Card("Arbor Elf", "G", "G", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 1, 1, MTGDuelDecks.LLANOWAR_ELVES_PATH, false);

		hand3.addAbility("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}");

		b.addCard(Zone.HAND, hand3, 0);
		ManaPool.getPool('G', true).add(1);

		b.activateAbility(b.getZoneContents(Zone.HAND)[0], Zone.HAND, 0, 0, null, null, Zone.BATTLE_FIELD);

		b.passPriority(true);
		b.passPriority(false);



		assertEquals(hand3, b.getZoneContents(Zone.BATTLE_FIELD)[0]);
	}

}
