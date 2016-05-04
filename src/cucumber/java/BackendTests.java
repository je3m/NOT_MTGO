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
	Backend bknd;
	@Given("^there is a new backend$")
	public void there_is_a_new_backend() throws Throwable {
	    bknd = new Backend();
	    bknd.reset();
	}
	
	@Given("^the first player's battlefield is empty$")
	public void the_first_player_s_battlefield_is_empty() throws Throwable {
	    Zone.BATTLE_FIELD.empty();
	}

	String imperiousPerfect = "TYPE{ACTIVATED} COST {G,TAP} ZONE {BATTLE_FIELD} EFFECT {ELF_TOKEN} RESOLVE {BATTLEFIELD}  TEXT {Put a 1/1 Elf Warrior token on the battlefield}";
	ArrayList<String> abilities;
	Card imperious;
	Card tarfire;
	Card beast;
	//TODO:Mocking?
	
	@Given("^an Imperious Perfect is put on the first player's battlefield$")
	public void an_Imperious_Perfect_is_put_on_the_first_player_s_battlefield() throws Throwable {
		abilities = new ArrayList<String>();
		abilities.add(imperiousPerfect);
		imperious = new Card("Imperious Perfect", null, "G", "Creature- Elf Warrior", null, abilities, 2, 2, MTGDuelDecks.FOREST_PATH, false);
		Zone.BATTLE_FIELD.addCard(imperious, 0);
	}

	@Given("^there is (\\d+) mana in the first player's green mana pool$")
	public void there_is_mana_in_the_first_player_s_green_mana_pool(int amount) throws Throwable {
	    ManaPool.GREEN1.empty();
	    ManaPool.GREEN1.add(amount);
	}

	@When("^I activate Imperious Perfect activated ability$")
	public void i_activate_Imperious_Perfect_activated_ability() throws Throwable {
		bknd.activateAbility(imperious, Zone.BATTLE_FIELD, 0, 0, null, null, null);
	}

	@Then("^Imperious Perfect should be tapped$")
	public void imperious_Perfect_should_be_tapped() throws Throwable {
	    assertTrue(imperious.getTapped());
	}

	@Then("^there should be (\\d+) mana in the first player's green mana pool$")
	public void there_should_be_mana_in_the_first_player_s_green_mana_pool(int mana) throws Throwable {
	    assertEquals(ManaPool.GREEN1.getAmount(),mana);
	}
	
	@Given("^there is a Tarfire on the stack targeting the first player$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_first_player() throws Throwable {
		tarfire = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		tarfire.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		bknd.putItemOnStack(new ItemOnStack(tarfire,tarfire.getAbilities()[0],Backend.PLAYER_TWO, null, Backend.PLAYER_ONE,null));
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
		bknd.passPriority(bknd.getPriority());
		bknd.passPriority(bknd.getPriority());
	}

	@Then("^Tarfire should be in the second player's graveyard$")
	public void tarfire_should_be_in_the_second_player_s_graveyard() throws Throwable {
	    Zone.GRAVEYARD1.contains("Tarfire");
	}
	
	@Given("^there is a Tarfire on the stack targeting the Imperious Perfect$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_Imperious_Perfect() throws Throwable {
		tarfire = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		tarfire.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		bknd.putItemOnStack(new ItemOnStack(tarfire,tarfire.getAbilities()[0],Backend.PLAYER_TWO, imperious, null,Zone.BATTLE_FIELD));
	}

	@Then("^Imperious Perfect should have (\\d+) damage$")
	public void imperious_Perfect_should_have_damage(int damage) throws Throwable {
	    assertEquals(imperious.getDamage(), damage);
	}

	@Then("^Imperious Perfect should be in the first player's graveyard$")
	public void imperious_Perfect_should_be_in_the_first_player_s_graveyard() throws Throwable {
		Zone.GRAVEYARD1.contains("Imperious Perfect");
	}
	
	@Given("^a Beast is put on the first player's battlefield$")
	public void a_Beast_is_put_on_the_first_player_s_battlefield() throws Throwable {
		beast = new Card("Beast", null, "G", "Creature- Beast", null, new ArrayList<String>(), 3, 3, MTGDuelDecks.FOREST_PATH, false);
	}

	@Given("^there is a Tarfire on the stack targeting the Beast$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_Beast() throws Throwable {
		tarfire = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		tarfire.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		bknd.putItemOnStack(new ItemOnStack(tarfire,tarfire.getAbilities()[0],Backend.PLAYER_TWO, beast, null,Zone.BATTLE_FIELD));
	}

	@Then("^Beast should have (\\d+) damage$")
	public void beast_should_have_damage(int damage) throws Throwable {
	    assertEquals(beast.getDamage(), damage);
	}
	
	@Then("^the first player's battlefield should have (\\d+) card in it$")
	public void the_first_player_s_battlefield_should_have_card_in_it(int cards) throws Throwable {
	    assertEquals(Zone.BATTLE_FIELD.getSize(), cards);
	}
	
	@Then("^the stack has (\\d+) item on it$")
	public void the_stack_has_item_on_it(int size) throws Throwable {
	    assertEquals(bknd.getStack().size(),size);
	}
}
