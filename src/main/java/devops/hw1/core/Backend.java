package devops.hw1.core;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Backend object that handles a lot of game logic and information
 *
 */
public class Backend {
	private Phase phase;
	private Boolean turn;
	private Boolean priority;
	private Boolean passed;
	private Stack<ItemOnStack> stack;

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
	 * temporary fix to different ways to call activate ability
	 * @param c the card whose ability is being activated
	 * @param z the zone the card is leaving
	 * @param i the index the card currently occupies in that zone
	 * @param abInd index of the card's ability
	 * TODO: this is weird
	 */
	public static void activateAbility(Card c, Zone z, int i, int abInd) {
		activateAbility(c, z, i);
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

		if((this.phase == Phase.CLEANUP1) || (this.phase == Phase.CLEANUP2))
			this.turn = !this.turn;

		this.phase = Phase.values()[(((this.phase.ordinal() + 1) % (Phase.CLEANUP2.ordinal() + 1)))];

		//handle untap-step case
		if((this.phase == Phase.UNTAP1) || (this.phase == Phase.UNTAP2)) {
			for(Card c : Zone.BATTLE_FIELD.getCards()) {
				if(c.getTapped()) {
					c.untap();
				}
			}
			for(Card c : Zone.BATTLE_FIELD1.getCards()) {
				if(c.getTapped()) {
					c.untap();
				}
			}
			this.phase = Phase.values()[(((this.phase.ordinal() + 1) % (Phase.CLEANUP2.ordinal() + 1)))];
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
	 * @param player which player is trying to pass priority
	 */
	public void passPriority(boolean player) {
		if(this.priority == player) {
			
			if (this.passed && !this.stack.empty()) {
				ItemOnStack item = this.stack.pop();
				if(item.getTarget() != null) {
					Card[] tZone = item.getTargetZone().getCards();
					for(int i = 0; i < tZone.length; i++) {
						if(tZone[i].equals(item.getTarget())) {
							item.getTargetZone().remove(i);
						}
					}
				} else {
					if(item.getPlayer()){
						Zone.BATTLE_FIELD.addCard(item.getCard(), 0);
					} else {
						Zone.BATTLE_FIELD1.addCard(item.getCard(), 0);
					}
				}
				this.passed = false;
				this.priority = this.turn;
			} else if(this.passed){
				this.changePhase();
				this.passed = false;
				this.priority= this.turn;
			} else {
				this.passed = true;
				this.priority = !this.priority;
			}
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
				break;
			case "U":
				ManaPool.BLUE1.add(1);
				break;
			case "B":
				ManaPool.BLACK1.add(1);
				break;
			case "R":
				ManaPool.RED1.add(1);
				break;
			case "G":
				ManaPool.GREEN1.add(1);
				break;
			case "1":
				ManaPool.COLORLESS1.add(1);
				break;
			}
		} else {
			switch (strToken.nextToken()) {
			case "W":
				ManaPool.WHITE2.add(1);
				break;
			case "U":
				ManaPool.BLUE2.add(1);
				break;
			case "B":
				ManaPool.BLACK2.add(1);
				break;
			case "R":
				ManaPool.RED2.add(1);
				break;
			case "G":
				ManaPool.GREEN2.add(1);
				break;
			case "1":
				ManaPool.COLORLESS2.add(1);
				break;
			}
		}
	}

	/**
	 * Parses a cost string and returns an array of costs
	 * @param s String of cost
	 * @return array of cost amounts in WUBRGC
	 */
	public int[] parseCost(String s){
		int cost[] = new int[6];

		int i = 0;

		while(isInteger(s.substring(i, i+1))){
			i++;
		}

		if(i != 0){
			cost[5] = Integer.parseInt(s.substring(0,i));
		}

		for(int g = i;g < s.length(); g++){
			switch (s.substring(g, g+1)){
			case "W":
				cost[0]++;
				break;
			case "U":
				cost[1]++;
				break;
			case "B":
				cost[2]++;
				break;
			case "R":
				cost[3]++;
				break;
			case "G":
				cost[4]++;
				break;
			}
		}
		return cost;
	}
	/**
	 * Casts a spell, removing mana from the players mana pool accordingly
	 * @param zone the zone the spell is being cast from
	 * @param c the spell being cast
	 * @param index the index the spell is in
	 * @param player the player that owns the spell
	 * @return true if the spell is cast, false if not
	 */
	public boolean castSpell(Zone zone, Card c, int index, Boolean player, Card target, Zone targetZone) {
		if((this.turn != player) || (!this.stack.isEmpty())){
			return false;
		}
		if((target != null) && !canTarget(c, zone, target, targetZone)) {
			return false;
		}
		
		int[] costs = this.parseCost(c.getCost());


		if((costs[0] <= ManaPool.getPool('w', player).getAmount()) &&
				(costs[1] <= ManaPool.getPool('u', player).getAmount()) &&
				(costs[2] <= ManaPool.getPool('b', player).getAmount()) &&
				(costs[3] <= ManaPool.getPool('r', player).getAmount()) &&
				(costs[4] <= ManaPool.getPool('g', player).getAmount()) &&
				((costs[0] + costs[1] + costs[2] + costs[3] + costs[4] + costs[5])<=
				(ManaPool.getPool('w', player).getAmount() + ManaPool.getPool('u', player).getAmount()
						+ ManaPool.getPool('b', player).getAmount() + ManaPool.getPool('r', player).getAmount()
						+ ManaPool.getPool('g', player).getAmount() + ManaPool.getPool('c', player).getAmount()))){

			ManaPool.getPool('w', player).remove(costs[0]);
			ManaPool.getPool('u', player).remove(costs[1]);
			ManaPool.getPool('b', player).remove(costs[2]);
			ManaPool.getPool('r', player).remove(costs[3]);
			ManaPool.getPool('g', player).remove(costs[4]);

			costs[5] = this.handleGeneric(ManaPool.getPool('c', player), costs[5]);
			costs[5] = this.handleGeneric(ManaPool.getPool('u', player), costs[5]);
			costs[5] = this.handleGeneric(ManaPool.getPool('b', player), costs[5]);
			costs[5] = this.handleGeneric(ManaPool.getPool('w', player), costs[5]);
			costs[5] = this.handleGeneric(ManaPool.getPool('r', player), costs[5]);
			costs[5] = this.handleGeneric(ManaPool.getPool('g', player), costs[5]);

			this.stack.push(new ItemOnStack(c, player, target, targetZone));

			zone.remove(index);
			this.passed = false;
			return true;
		} else {
			return false;
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
		return s.matches("\\d+");
	}
	
	/**
	 * Determines if a card can target another card
	 * @param sourceC the card that is doing the targeting
	 * @param sourceZ the zone that the targeting card is from
	 * @param targetC the card that is being targeting
	 * @param targetZ the zone that the targeted card is in
	 * @return whether the source card in the source zone can target the target card in the target zone
	 */
	public static boolean canTarget(Card sourceC, Zone sourceZ, Card targetC, Zone targetZ) {
		boolean h1ToB2 = (sourceZ == Zone.HAND)  && (targetZ == Zone.BATTLE_FIELD1);
		boolean h1ToB1 = (sourceZ == Zone.HAND)  && (targetZ == Zone.BATTLE_FIELD);
		boolean h2ToB1 = (sourceZ == Zone.HAND1)  && (targetZ == Zone.BATTLE_FIELD);
		boolean h2ToB2 = (sourceZ == Zone.HAND1)  && (targetZ == Zone.BATTLE_FIELD1);
		
		
		return h1ToB2 || h1ToB1 || h2ToB1 || h2ToB2;
	}
}
