Feature: Card
	As a player, I want the card to keep track of its damage
	
@backend
Scenario: Initial card has no damage
	When the card is created
	Then the card should have 0 damage
	
@backend
Scenario: Damaging a card
	Given the card is created
	When the card takes 5 damage
	Then the card should have 5 damage
