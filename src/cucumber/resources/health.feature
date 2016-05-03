Feature: Health
	As a player, I want to have health that can increase and decrease.
	
@health
Scenario: Setting health for player 1
	When the first player's health is set to 20
	Then the first player's health should be 20

@health
Scenario: Increasing health for player 1
	Given the first player's health is set to 20
	When the first player's health changes by 5
	Then the first player's health should be 25