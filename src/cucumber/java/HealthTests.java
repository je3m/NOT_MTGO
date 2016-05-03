import back_end.Health;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class HealthTests {
	@When("^the first player's health is set to (\\d+)$")
	public void the_first_player_s_health_is_set_to(int health) throws Throwable {
	    Health.HEALTH1.set(health);
	}

	@Then("^the first player's health should be (\\d+)$")
	public void the_first_player_s_health_should_be(int health) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(Health.HEALTH1.get(),health);
	}
	
	@When("^the first player's health increases by (\\d+)$")
	public void the_first_player_s_health_increases_by(int change) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    Health.HEALTH1.add(change);
	}
	
	@When("^the first player's health decreases by (\\d+)$")
	public void the_first_player_s_health_decreases_by(int change) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Health.HEALTH1.remove(change);
	}
}
