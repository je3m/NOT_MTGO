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
}
