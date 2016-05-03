
public class HealthTests {
	@When("^the first player's health is set to (\\d+)$")
	public void the_first_player_s_health_is_set_to(int health) throws Throwable {
	    Health.Health1.set(health);
	}

	@Then("^the first player's health should be (\\d+)$")
	public void the_first_player_s_health_should_be(int health) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    assertEqual(Health.Health1.get(),health);
	}
}
