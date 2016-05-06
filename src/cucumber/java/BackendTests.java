import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import back_end.Backend;
import back_end.Card;
import back_end.ItemOnStack;
import back_end.MTGDuelDecks;
import back_end.ManaPool;
import back_end.Zone;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BackendTests {
	Backend bknd;
	@Given("^there is a new backend$")
	public void there_is_a_new_backend() throws Throwable {
		this.bknd = new Backend();
		this.bknd.reset();
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
		this.abilities = new ArrayList<String>();
		this.abilities.add(this.imperiousPerfect);
		this.imperious = new Card("Imperious Perfect", null, "G", "Creature- Elf Warrior", null, this.abilities, 2, 2, MTGDuelDecks.FOREST_PATH, false);
		Zone.BATTLE_FIELD.addCard(this.imperious, 0);
	}

	@Given("^there is (\\d+) mana in the first player's green mana pool$")
	public void there_is_mana_in_the_first_player_s_green_mana_pool(int amount) throws Throwable {
		ManaPool.GREEN1.empty();
		ManaPool.GREEN1.add(amount);
	}

	@When("^I activate Imperious Perfect activated ability$")
	public void i_activate_Imperious_Perfect_activated_ability() throws Throwable {
		this.bknd.activateAbility(this.imperious, Zone.BATTLE_FIELD, 0, 0, null, null, null);
	}

	@Then("^Imperious Perfect should be tapped$")
	public void imperious_Perfect_should_be_tapped() throws Throwable {
		assertTrue(this.imperious.getTapped());
	}

	@Then("^there should be (\\d+) mana in the first player's green mana pool$")
	public void there_should_be_mana_in_the_first_player_s_green_mana_pool(int mana) throws Throwable {
		assertEquals(ManaPool.GREEN1.getAmount(),mana);
	}

	@Given("^there is a Tarfire on the stack targeting the first player$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_first_player() throws Throwable {
		this.tarfire = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		this.tarfire.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		this.bknd.putItemOnStack(new ItemOnStack(this.tarfire,this.tarfire.getAbilities()[0],Backend.PLAYER_TWO, null, Backend.PLAYER_ONE,null));
	}

	@Given("^the second player has priority$")
	public void the_second_player_has_priority() throws Throwable {
		this.bknd.setPriority(Backend.PLAYER_TWO);
	}

	@Given("^it is the second player's turn$")
	public void it_is_the_second_player_s_turn() throws Throwable {
		this.bknd.setTurn(Backend.PLAYER_TWO);
	}

	@When("^priority is passed twice$")
	public void priority_is_passed_twice() throws Throwable {
		this.bknd.passPriority(this.bknd.getPriority());
		this.bknd.passPriority(this.bknd.getPriority());
	}

	@When("^I play a mountain$")
	public void play_mountain(){
		Card mountain = new Card("Mountain", "", "", "Basic Land- Mountain", "T:R",
				new ArrayList<String>(), 0, 0, "res/Mountain.jpg", false);
		mountain.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");

		this.bknd.addCard(Zone.HAND1, mountain, 0);

		try{
			this.bknd.activateAbility(mountain, Zone.HAND1, 0, 0, null, true, Zone.BATTLE_FIELD1);

		} catch (Exception e){

		}

	}

	@Then("^there is nothing in the second players battlefield$")
	public void battlefield_one_empty(){
		assertEquals(0, Zone.BATTLE_FIELD1.getSize());
	}


	@Then("^Tarfire should be in the second player's graveyard$")
	public void tarfire_should_be_in_the_second_player_s_graveyard() throws Throwable {
		Zone.GRAVEYARD1.contains("Tarfire");
	}

	@Given("^there is a Tarfire on the stack targeting the Imperious Perfect$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_Imperious_Perfect() throws Throwable {
		this.tarfire = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		this.tarfire.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		this.bknd.putItemOnStack(new ItemOnStack(this.tarfire,this.tarfire.getAbilities()[0],Backend.PLAYER_TWO, this.imperious, null,Zone.BATTLE_FIELD));
	}

	@Then("^Imperious Perfect should have (\\d+) damage$")
	public void imperious_Perfect_should_have_damage(int damage) throws Throwable {
		assertEquals(this.imperious.getDamage(), damage);
	}

	@Then("^Imperious Perfect should be in the first player's graveyard$")
	public void imperious_Perfect_should_be_in_the_first_player_s_graveyard() throws Throwable {
		Zone.GRAVEYARD1.contains("Imperious Perfect");
	}

	@Given("^a Beast is put on the first player's battlefield$")
	public void a_Beast_is_put_on_the_first_player_s_battlefield() throws Throwable {
		this.beast = new Card("Beast", null, "G", "Creature- Beast", null, new ArrayList<String>(), 3, 3, MTGDuelDecks.FOREST_PATH, false);
	}

	@Given("^there is a Tarfire on the stack targeting the Beast$")
	public void there_is_a_Tarfire_on_the_stack_targeting_the_Beast() throws Throwable {
		this.tarfire = new Card("Tarfire", "R", "R", "Instant- Goblin", "", new ArrayList<String>(), 0, 0, MTGDuelDecks.TARFIRE_PATH, true);
		this.tarfire.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE-2} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");
		this.bknd.putItemOnStack(new ItemOnStack(this.tarfire,this.tarfire.getAbilities()[0],Backend.PLAYER_TWO, this.beast, null,Zone.BATTLE_FIELD));
	}

	@Then("^Beast should have (\\d+) damage$")
	public void beast_should_have_damage(int damage) throws Throwable {
		assertEquals(this.beast.getDamage(), damage);
	}

	@Then("^the first player's battlefield should have (\\d+) card in it$")
	public void the_first_player_s_battlefield_should_have_card_in_it(int cards) throws Throwable {
		assertEquals(Zone.BATTLE_FIELD.getSize(), cards);
	}

	@When("^I play a forest$")
	public void i_play_a_forest() throws Throwable {
		Card forest = new Card("Forest", "", "", "Basic Land- Forest", "T:G",
				new ArrayList<String>(), 0, 0, "res/Forest.jpg", false);
		forest.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");

		this.bknd.addCard(Zone.HAND, forest, 0);

		try{
			this.bknd.activateAbility(forest, Zone.HAND, 0, 0, null, true, Zone.BATTLE_FIELD);

		} catch (Exception e){

		}
	}

	@Then("^the stack has (\\d+) item on it$")
	public void the_stack_has_item_on_it(int size) throws Throwable {
		assertEquals(this.bknd.getStack().size(),size);
	}
}
