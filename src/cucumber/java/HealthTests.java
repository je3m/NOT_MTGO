import back_end.Health;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class HealthTests {
	@When("^the (first|second) player's health is set to (\\d+)$")
	public void the_player_s_health_is_set_to(String player, int health) throws Throwable {
		if(player.equals("first")){
			Health.HEALTH.set(health);
		} else {
			Health.HEALTH1.set(health);
		}
	}

	@Then("^the (first|second) player's health should be (\\d+)$")
	public void the_player_s_health_should_be(String player, int health) throws Throwable {
		if(player.equals("first")){
			assertEquals(Health.HEALTH.get(),health);
		} else {
			assertEquals(Health.HEALTH1.get(),health);
		}
	}
	
	@When("^the (first|second) player's health increases by (\\d+)$")
	public void the_player_s_health_increases_by(String player, int change) throws Throwable {
		if(player.equals("first")){
			Health.HEALTH.add(change);
		} else {
			Health.HEALTH1.add(change);
		}
	}
	
	@When("^the (first|second) player's health decreases by (\\d+)$")
	public void the_player_s_health_decreases_by(String player, int change) throws Throwable {
		if(player.equals("first")){
			Health.HEALTH.remove(change);
		} else {
			Health.HEALTH1.remove(change);
		}
	}
}
