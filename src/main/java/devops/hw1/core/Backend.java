package devops.hw1.core;

import java.util.Stack;
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
	Stack<ItemOnStack> stack;
	
	/**
	 * Constructs a backend object
	 */
	public Backend(){
		this.phase = Phase.UNTAP1;
		this.turn = true;
		this.priority = true;
		this.passed = false;
		this.stack = new Stack<ItemOnStack>();
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
		if (this.passed && !this.stack.empty()) {
			ItemOnStack item = this.stack.pop();
			if(item.getPlayer()){
				Zone.BATTLE_FIELD.addCard(item.getCard(), 0);
			} else {
				Zone.BATTLE_FIELD1.addCard(item.getCard(), 0);
			}
			this.passed = false;
		} else if(this.passed){
			changePhase();
			this.passed = true;
		} else {
			this.passed = true;
		}
	}

	/**
	 * Activates the mana ability of a card
	 * @param c card with the mana ability
	 * @param player player who activated the mana ability
	 */
	public void activateManaAbility(Card c, Boolean player) {
		StringTokenizer strToken = new StringTokenizer(c.getManaAbility(),":");
		if(strToken.nextToken().equals("T")){
			c.tap();
		}
		if(player){
			switch (strToken.nextToken()) {
			case "W":
				ManaPool.WHITE1.add(1);
			case "U":
				ManaPool.BLUE1.add(1);
			case "B":
				ManaPool.BLACK1.add(1);
			case "R":
				ManaPool.RED1.add(1);
			case "G":
				ManaPool.GREEN1.add(1);
			case "1":
				ManaPool.COLORLESS1.add(1);
			}
		} else {
			switch (strToken.nextToken()) {
			case "W":
				ManaPool.WHITE2.add(1);
			case "U":
				ManaPool.BLUE2.add(1);
			case "B":
				ManaPool.BLACK2.add(1);
			case "R":
				ManaPool.RED2.add(1);
			case "G":
				ManaPool.GREEN2.add(1);
			case "1":
				ManaPool.COLORLESS2.add(1);
			}
		}
	}

	/**
	 * Casts a spell, removing mana from the players mana pool acordingly
	 * @param zone the zone the spell is being cast from
	 * @param c the spell being cast
	 * @param index the index the spell is in
	 * @param player the player that owns the spell
	 * @return true if the spell is cast, false if not
	 */
	public boolean castSpell(Zone zone, Card c, int index, Boolean player) {
		int whiteCost = 0;
		int blueCost = 0;
		int blackCost = 0;
		int redCost = 0;
		int greenCost = 0;
		int genericCost = 0;
		int i = 0;
		
		while(isInteger(c.getCost().substring(i, i+1))){
			i++;
		}
		
		if(i != 0){
			genericCost = Integer.parseInt(c.getCost().substring(0,i));
		}
		
		for(int g = i;g < c.getCost().length(); g++){
			switch (c.getCost().substring(g, g+1)){
			case "W":
				whiteCost++;
				break;
			case "U":
				blueCost++;
				break;
			case "B":
				blackCost++;
				break;
			case "R":
				redCost++;
				break;
			case "G":
				greenCost++;
				break;
			}
		}
		if((this.turn != player) || (!this.stack.isEmpty())){
			return false;
		}
		if(player){
			if((whiteCost <= ManaPool.WHITE1.getAmount()) &&
					(blueCost <= ManaPool.BLUE1.getAmount()) &&
					(blackCost <= ManaPool.BLACK1.getAmount()) &&
					(redCost <= ManaPool.RED1.getAmount()) &&
					(greenCost <= ManaPool.GREEN1.getAmount()) &&
					((whiteCost + blueCost + blackCost + redCost + greenCost + genericCost)<=
							(ManaPool.WHITE1.getAmount() + ManaPool.BLUE1.getAmount() + ManaPool.BLACK1.getAmount() + ManaPool.RED1.getAmount() + ManaPool.GREEN1.getAmount() + ManaPool.COLORLESS1.getAmount()))){
				ManaPool.WHITE1.remove(whiteCost);
				ManaPool.BLUE1.remove(blueCost);
				ManaPool.BLACK1.remove(blackCost);
				ManaPool.RED1.remove(redCost);
				ManaPool.GREEN1.remove(greenCost);
				
				genericCost = handleGeneric(ManaPool.COLORLESS1, genericCost);
				genericCost = handleGeneric(ManaPool.BLUE1, genericCost);
				genericCost = handleGeneric(ManaPool.BLACK1, genericCost);
				genericCost = handleGeneric(ManaPool.WHITE1, genericCost);
				genericCost = handleGeneric(ManaPool.RED1, genericCost);
				genericCost = handleGeneric(ManaPool.GREEN1, genericCost);
				
				stack.push(new ItemOnStack(c, true));
				
				zone.remove(index);
				this.passed = false;
				return true;
			} else {
				return false;
			}
		} else {
			if((whiteCost <= ManaPool.WHITE2.getAmount()) &&
					(blueCost <= ManaPool.BLUE2.getAmount()) &&
					(blackCost <= ManaPool.BLACK2.getAmount()) &&
					(redCost <= ManaPool.RED2.getAmount()) &&
					(greenCost <= ManaPool.GREEN2.getAmount()) &&
					((whiteCost + blueCost + blackCost + redCost + greenCost + genericCost)<=
							(ManaPool.WHITE2.getAmount() + ManaPool.BLUE2.getAmount() + ManaPool.BLACK2.getAmount() + ManaPool.RED2.getAmount() + ManaPool.GREEN2.getAmount() + ManaPool.COLORLESS2.getAmount()))){
				ManaPool.WHITE2.remove(whiteCost);
				ManaPool.BLUE2.remove(blueCost);
				ManaPool.BLACK2.remove(blackCost);
				ManaPool.RED2.remove(redCost);
				ManaPool.GREEN2.remove(greenCost);
				
				genericCost = handleGeneric(ManaPool.COLORLESS2, genericCost);
				genericCost = handleGeneric(ManaPool.BLUE2, genericCost);
				genericCost = handleGeneric(ManaPool.BLACK2, genericCost);
				genericCost = handleGeneric(ManaPool.WHITE2, genericCost);
				genericCost = handleGeneric(ManaPool.GREEN2, genericCost);
				genericCost = handleGeneric(ManaPool.RED2, genericCost);
				
				stack.push(new ItemOnStack(c, false));
				
				zone.remove(index);
				this.passed = false;
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Handles paying generic costs with mana from a specific mana pool
	 * @param manaPool mana pool to play mana with
	 * @param genericCost amount of generic mana to pay
	 * @return amount of mana left to pay
	 */
	public int handleGeneric(ManaPool manaPool, int genericCost){
		if(genericCost > 0){
			if(manaPool.getAmount() >= genericCost){
				manaPool.remove(genericCost);
				return 0;
			} else {
				int temp = genericCost - manaPool.getAmount();
				manaPool.empty();
				return temp;
			}
		} else if (genericCost == 0){
			return 0;
		} else {
			//Needs tested
			throw new IllegalArgumentException(genericCost + " is not a valid amount of generic mana cost.");
		}
	}
	
	/**
	 * Determines if a string is an integer
	 * @param s string to check
	 * @return true if the string is an integer
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(Exception e) { 
	        return false; 
	    }
	    return true;
	}
}
