package devops.hw1.core;

import java.util.StringTokenizer;

/**
 * Backend object that handles a lot of game logic and information
 *
 */
public class Backend {
	Phase phase;
	Boolean turn;
	Boolean priority;
	Boolean passed;
	
	/**
	 * Constructs a backend object
	 */
	public Backend(){
		this.phase = Phase.UNTAP1;
		this.turn = true;
		this.priority = true;
		this.passed = false;
	}
	
	//!# Eliminate because irrelevant? otherwise ADD ERROR-HANDLING FOR INDEX
	/**
	 * Handles logic for moving a card when it's clicked (prototype code)
	 * @param z the zone the card's being moved from
	 * @param currIndex the index that that card currently occupies in that zone
	 * @param c the card that's being moved from its current zone
	 */
	public static void handleCardClicked(Zone z, int currIndex, Card c) {
		z.remove(currIndex);
		switch(z) {
		case HAND:
			Zone.BATTLE_FIELD.addCard(c, 0);
			break;
		case HAND1:
			Zone.BATTLE_FIELD1.addCard(c, 0);
			break;
		case BATTLE_FIELD:
			Zone.GRAVEYARD.addCard(c, 0);
			break;
		case BATTLE_FIELD1:
			Zone.GRAVEYARD1.addCard(c, 0);
			break;
		default:
			throw new IllegalArgumentException(z + " zone is not a valid zone for card click events.");
		}
	}

	//!# Refactor? add error-handling for invalid i
	/**
	 * Prototype function. Moves a card from its current zone to a new one when its ability is activated
	 * @param c the card whose ability is being activated
	 * @param z the zone the card is leaving
	 * @param i the index the card currently occupies in that zone
	 */
	public static void activateAbility(Card c, Zone z, int i) {
		z.remove(i);

		if(z == Zone.HAND)
			addCard(Zone.BATTLE_FIELD, c);

		if(z == Zone.HAND1)
			addCard(Zone.BATTLE_FIELD1, c);

	}

	/**
	 * Simply adds the card to the given zone at the end of that zone's list
	 * @param z the zone being added to
	 * @param c the card being added
	 */
	public static void addCard(Zone z, Card c) {
		z.addCard(c, z.getSize());
	}

	/**
	 * Adds the given card to the given zone
	 * @param z Zone to add card to
	 * @param c Card to add
	 * @param i	position of the zone to add card
	 */
	void addCard(Zone z, Card c, int i){
		try {
			z.addCard(c, i);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Backend: " + e.getMessage());
		}
	}

	/**
	 * This method will get the contents (in order) of all cards of a given zone
	 * @param zone Zone enum that you want cards from
	 * @return Array of Card's in the given zone
	 */
	Card[] getZoneContents(Zone zone){
		return zone.getCards();
	}


	/**
	 * Removes the card at the given index from the zone
	 * @param index of card to remove
	 */
	void removeCard(Zone z, int i){
		try {
			z.remove(i);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Backend: " + e.getMessage());
		}
		
	}
	

	/**
	 * Gets the current phase
	 * @return the current phase
	 */
	public Phase getPhase() {
		return this.phase;
	}

	/**
	 * Changes the current phase to the next phase
	 */
	public void changePhase() {
		ManaPool.emptyMana();
		switch(this.phase) {
		case UNTAP1:
			this.phase = Phase.UPKEEP1;
			break;
		case UPKEEP1:
			this.phase = Phase.DRAW1;
			break;
		case DRAW1:
			this.phase = Phase.FIRST_MAIN1;
			break;
		case FIRST_MAIN1:
			this.phase = Phase.START_COMBAT1;
			break;
		case START_COMBAT1:
			this.phase = Phase.DECLARE_ATTACKERS1;
			break;
		case DECLARE_ATTACKERS1:
			this.phase = Phase.DECLARE_BLOCKERS1;
			break;
		case DECLARE_BLOCKERS1:
			this.phase = Phase.COMBAT_DAMAGE1;
			break;
		case COMBAT_DAMAGE1:
			this.phase = Phase.END_OF_COMBAT1;
			break;
		case END_OF_COMBAT1:
			this.phase = Phase.SECOND_MAIN1;
			break;
		case SECOND_MAIN1:
			this.phase = Phase.END1;
			break;
		case END1:
			this.phase = Phase.CLEANUP1;
			break;
		case CLEANUP1:
			this.phase = Phase.UNTAP2;
			this.turn = false;
			break;
		case UNTAP2:
			this.phase = Phase.UPKEEP2;
			break;
		case UPKEEP2:
			this.phase = Phase.DRAW2;
			break;
		case DRAW2:
			this.phase = Phase.FIRST_MAIN2;
			break;
		case FIRST_MAIN2:
			this.phase = Phase.START_COMBAT2;
			break;
		case START_COMBAT2:
			this.phase = Phase.DECLARE_ATTACKERS2;
			break;
		case DECLARE_ATTACKERS2:
			this.phase = Phase.DECLARE_BLOCKERS2;
			break;
		case DECLARE_BLOCKERS2:
			this.phase = Phase.COMBAT_DAMAGE2;
			break;
		case COMBAT_DAMAGE2:
			this.phase = Phase.END_OF_COMBAT2;
			break;
		case END_OF_COMBAT2:
			this.phase = Phase.SECOND_MAIN2;
			break;
		case SECOND_MAIN2:
			this.phase = Phase.END2;
			break;
		case END2:
			this.phase = Phase.CLEANUP2;
			break;
		case CLEANUP2:
			this.phase = Phase.UNTAP1;
			this.turn = true;
			break;
		default:
			break;
		}
	}

	/**
	 * Returns the current player whose turn it is
	 * @return true if it is the first player's turn, false if it is the second player's
	 */
	public Boolean getTurn() {
		return this.turn;
	}

	/**
	 * Returns the current player who had priority
	 * @return true if it is the first player's priority, false if it is the second player's
	 */
	public boolean getPriority() {
		return this.priority;
	}

	/**
	 * Passes priority for players
	 */
	public void passPriority() {
		this.priority = !this.priority;
		if(this.passed){
			changePhase();
		}
		this.passed = true;
	}

	/**
	 * Activates the mana ability of a card
	 * @param c
	 */
	public void activateManaAbility(Card c, Boolean player) {
		StringTokenizer strToken = new StringTokenizer(c.getManaAbility(),":");
		if(strToken.nextToken().equals("T")){
			c.tap();
		}
		if(player){
			switch (strToken.nextToken()) {
			case "G":
				ManaPool.GREEN1.add(1);
			}
		} else {	
		}
	}
}
