Feature: Health
	As a player, I want to have health that can increase and decrease.
	
@health
Scenario: Setting health for player 1
	When the first player's health is set to 20
	Then the first player's health should be 20