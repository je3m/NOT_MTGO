import java.util.ArrayList;

import back_end.Backend;
import back_end.Card;
import back_end.Health;
import back_end.MTGDuelDecks;
import back_end.ManaPool;
import back_end.Zone;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class CardTests {
	Card c;
	@When("^the card is created$")
	public void the_card_is_created() throws Throwable {
	    c =  new Card("Imperious Perfect", null, "G", "Creature- Elf Warrior", null, new ArrayList<String>(), 2, 2, MTGDuelDecks.FOREST_PATH, false);
	}

	@Then("^the card should have (\\d+) damage$")
	public void the_card_should_have_damage(int damage) throws Throwable {
	    assertEquals(c.getDamage(), damage);
	}
}
