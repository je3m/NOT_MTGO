Feature: Card
	As a player, I want the card to keep track of its damage
	
@backend
Scenario: Initial card has no damage
	When the card is created
	Then the card should have 0 damage
