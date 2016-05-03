import java.util.ArrayList;

import back_end.Backend;
import back_end.Card;
import back_end.Health;
import back_end.ItemOnStack;
import back_end.MTGDuelDecks;
import back_end.ManaPool;
import back_end.Zone;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class BackendTests {
	Backend bknd = Backend.getInstance();
	@Given("^the first player's battlefield is empty$")
	public void the_first_player_s_battlefield_is_empty() throws Throwable {
	    Zone.BATTLE_FIELD.empty();
	}

	String imperiousPerfect = "TYPE{ACTIVATED} COST {G,TAP} ZONE {BATTLE_FIELD} EFFECT {TOKEN ELFWARRIOR} RESOLVE {BATTLEFIELD}";
	ArrayList<String> abilities;
	Card c;
	//TODO:Mocking?
	
	@Given("^an Imperious Perfect is put on the first player's battlefield$")
	public void an_Imperious_Perfect_is_put_on_the_first_player_s_battlefield() throws Throwable {
		abilities = new ArrayList<String>();
		abilities.add(imperiousPerfect);
		c = new Card("Imperious Perfect", null, "G", "Creature- Elf Warrior", null, abilities, 2, 2, MTGDuelDecks.FOREST_PATH, false);
		Zone.BATTLE_FIELD.addCard(c, 0);
	}

	@Given("^there is (\\d+) mana in the first player's green mana pool$")
	public void there_is_mana_in_the_first_player_s_green_mana_pool(int amount) throws Throwable {
	    ManaPool.GREEN1.empty();
	    ManaPool.GREEN1.add(amount);
	}

	@When("^I activate Imperious Perfect activated ability$")
	public void i_activate_Imperious_Perfect_activated_ability() throws Throwable {
		bknd.activateAbility(c, Zone.BATTLE_FIELD, 0, 0, null);
	}

	@Then("^Imperious Perfect should be tapped$")
	public void imperious_Perfect_should_be_tapped() throws Throwable {
	    assertTrue(c.getTapped());
	}

	@Then("^there should be (\\d+) mana in the first player's green mana pool$")
	public void there_should_be_mana_in_the_first_player_s_green_mana_pool(int arg1) throws Throwable {
	    assertEquals(ManaPool.GREEN1.getAmount(),0);
	}
	
	@Given("^there is a Tarfire on the stack targeting the first player$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_first_player() throws Throwable {
		bknd = Backend.getInstance();
		c = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		c.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		bknd.putItemOnStack(new ItemOnStack(c,c.getAbilities()[0],Backend.PLAYER_TWO, null, Backend.PLAYER_TWO,null));
	}

	@Given("^the second player has priority$")
	public void the_second_player_has_priority() throws Throwable {
	    bknd.setPriority(Backend.PLAYER_TWO);
	}

	@Given("^it is the second player's turn$")
	public void it_is_the_second_player_s_turn() throws Throwable {
		bknd.setTurn(Backend.PLAYER_TWO);
	}

	@When("^priority is passed twice$")
	public void priority_is_passed_twice() throws Throwable {
		bknd.passPriority(Backend.PLAYER_TWO);
		bknd.passPriority(Backend.PLAYER_ONE);
	}

	@Then("^Tarfire should be in the second player's graveyard$")
	public void tarfire_should_be_in_the_second_player_s_graveyard() throws Throwable {
	    Zone.GRAVEYARD1.contains("Tarfire");
	}
}
