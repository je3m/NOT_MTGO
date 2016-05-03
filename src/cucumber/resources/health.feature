Feature: Health
	As a player, I want to have health that can increase and decrease.
	
@health
Scenario: Setting health for player 1
	When the first player's health is set to 20
	Then the first player's health should be 20

@health
Scenario: Increasing health for player 1
	Given the first player's health is set to 20
	When the first player's health increases by 5
	Then the first player's health should be 25

@health
Scenario: Decreasing health for player 1
	Given the first player's health is set to 20
	When the first player's health decreases by 5
	Then the first player's health should be 15
	
@health
Scenario: Setting health for player 2
	When the second player's health is set to 20
	Then the second player's health should be 20

@health
Scenario: Increasing health for player 2
	Given the second player's health is set to 20
	When the second player's health increases by 5
	Then the second player's health should be 25

@health
Scenario: Decreasing health for player 2
	Given the second player's health is set to 20
	When the second player's health decreases by 5
	Then the second player's health should be 15