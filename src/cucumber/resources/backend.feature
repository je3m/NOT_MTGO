Feature: Backend
	As a player, I want the backend to pay costs for activated abilities.
	As a player, I want spells effects to be handled when they are cast.
	As a player, I want creatures to die when a spell deals enough damage to kill them.

@backend
Scenario: Paying costs for an activated ability
	Given there is a new backend 
	And the first player's battlefield is empty
	And an Imperious Perfect is put on the first player's battlefield
	And there is 1 mana in the first player's green mana pool
	When I activate Imperious Perfect activated ability
	Then Imperious Perfect should be tapped
	And there should be 0 mana in the first player's green mana pool

@backend
Scenario: Tarfire resolves
	Given there is a new backend
	And there is a Tarfire on the stack targeting the first player
	And the second player has priority
	And it is the second player's turn
	And the first player's health is set to 20
	When priority is passed twice
	Then the first player's health should be 18
	And Tarfire should be in the second player's graveyard
	
@backend
Scenario: Tarfire resolves on a creature that lives
	Given there is a new backend
	And the first player's battlefield is empty
	And a Beast is put on the first player's battlefield
	And there is a Tarfire on the stack targeting the Beast
	And the second player has priority
	And it is the second player's turn
	When priority is passed twice
	Then Beast should have 2 damage
	And Tarfire should be in the second player's graveyard
	
@backend
Scenario: Tarfire resolves on a creature that dies
	Given there is a new backend
	And the first player's battlefield is empty
	And an Imperious Perfect is put on the first player's battlefield
	And there is a Tarfire on the stack targeting the Imperious Perfect
	And the second player has priority
	And it is the second player's turn
	When priority is passed twice
	Then Imperious Perfect should have 0 damage
	And Imperious Perfect should be in the first player's graveyard
	And Tarfire should be in the second player's graveyard
	
@backend
Scenario: Activated ability puts an item on the stack
	Given there is a new backend
	And the first player's battlefield is empty
	And an Imperious Perfect is put on the first player's battlefield
	And there is 1 mana in the first player's green mana pool
	When I activate Imperious Perfect activated ability
	Then the first player's battlefield should have 1 card in it
	And the stack has 1 item on it
	
@backend
Scenario: Activated ability resolves correctly
	Given there is a new backend
	And the first player's battlefield is empty
	And an Imperious Perfect is put on the first player's battlefield
	And there is 1 mana in the first player's green mana pool
	And I activate Imperious Perfect activated ability
	When priority is passed twice
	Then the stack has 0 item on it
	And the first player's battlefield should have 2 card in it