Feature: Backend
	As a player, I want the backend to pay costs for activated abilities
	
@backend
Scenario: Paying costs for an activated ability
	Given the first player's battlefield is empty
	And an Imperious Perfect is put on the first player's battlefield
	And there is 1 mana in the first player's green mana pool
	When I activate Imperious Perfect activated ability
	Then Imperious Perfect should be tapped
	And there should be 0 mana in the first player's green mana pool
