package back_end;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.concurrent.RejectedExecutionException;


/**
 * Backend object that handles a lot of game logic and information
 *
 */
public class Backend {
	public static final boolean PLAYER_ONE = true;
	public static final boolean PLAYER_TWO = false;

	private Phase phase;
	private boolean turn;
	private boolean priority;
	private boolean passed;
	private boolean landPlayed;

	private Stack<ItemOnStack> stack;
	public static Backend bk;

	public static Backend getInstance(){
		if(bk == null)
			bk = new Backend();

		return bk;
	}

	//Functions Used For Testing
	/**
	 * Gets the stack in the backend
	 * @return the stack
	 */
	public Stack<ItemOnStack> getStack(){
		return this.stack;
	}

	/**
	 * Puts an item directly onto the stack
	 * @param stackItem item to be put on the stack
	 */
	public void putItemOnStack(ItemOnStack stackItem){
		this.stack.push(stackItem);
	}

	/**
	 * Sets the current phase
	 * @param p phase to be set to
	 */
	public void setPhase(Phase p){
		this.phase = p;
	}

	/**
	 * Sets the current turn
	 * @param t new turn
	 */
	public void setTurn(boolean t){
		this.turn = t;
		this.landPlayed = false;
	}

	/**
	 * Sets the priority
	 * @param priority new priority
	 */
	public void setPriority(boolean priority){
		this.priority = priority;
	}

	/**
	 * Resets the game
	 */
	public void reset(){
		this.setPhase(Phase.FIRST_MAIN1);
		this.setTurn(PLAYER_ONE);
		this.setPriority(PLAYER_ONE);
	}

	//Non testing functions
	/**
	 * Constructs a backend object
	 */
	public Backend(){
		this.phase = Phase.FIRST_MAIN1;
		this.turn = true;
		this.priority = true;
		this.passed = false;
		this.stack = new Stack<ItemOnStack>();
		this.landPlayed = false;
	}

	/**
	 * Handles logic for moving a card when it's clicked (prototype code)
	 * @param z the zone the card's being moved from
	 * @param currIndex the index that that card currently occupies in that zone
	 * @param c the card that's being moved from its current zone
	 */
	public static void handleCardClicked(Zone z, int currIndex, Card c) {
		if(currIndex < 0){
			throw new IllegalArgumentException(currIndex + " is not a valid index for card click events.");
		}
		if(c == null){
			throw new IllegalArgumentException("Null is not a valid card for card click events.");
		}
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
		try{
			z.remove(currIndex);
		} catch (IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException("Card clicked could not remove card: " + e.getMessage());
		}
	}



	/**
	 * temporary fix to different ways to call activate ability
	 * @param c the card whose ability is being activated
	 * @param z the zone the card is leaving
	 * @param i the index the card currently occupies in that zone
	 * @param abInd index of the card's ability
	 * @param target the card being targeted by the ability
	 * @param targetPlayer the player being targeted by the ability
	 * @param targetZone the zone of the card being targeted
	 */
	public void activateAbility(Card c, Zone z, int i, int abInd, Card target, Boolean targetPlayer, Zone targetZone) {
		Ability a =	c.getAbilities()[abInd];

		if((a.getType() != AbilityType.MANA)&& (!c.isFlash() &&
				((this.turn != Zone.getPlayerFromZone(z)) || (!this.stack.isEmpty()) ||
						((this.phase != Phase.FIRST_MAIN1) && (this.phase != Phase.SECOND_MAIN1) &&
								(this.phase != Phase.FIRST_MAIN2) && (this.phase != Phase.SECOND_MAIN2))))){
			throw new RuntimeException("cannot cast spell at this time");
		}
		if((target != null) && !canTarget(c, z, target, targetZone)) {
			throw new RuntimeException("invalid target");
		}

		boolean player = Zone.getPlayerFromZone(z);

		switch(a.getType()){
		case CAST:
			this.castSpell(z, a, c, i, a.getCost(), player, target, targetPlayer, Zone.getZoneFromString(a.getResolveZone(), player));
			break;

		case PLAY:
			if (this.landPlayed)
				throw new RuntimeException("Can only play one land per turn");

			this.landPlayed = true;
			Zone res = Zone.getZoneFromString(a.getResolveZone(), player);
			Zone.getZoneFromString(a.getZone(), player).remove(i);
			res.addCard(c, res.getSize());
			break;

		case MANA:
			c.addManaAbility("T:"+a.getEffect());
			this.activateManaAbility(c, player);
			break;
		case ACTIVATED:
			if(this.payAbilityCost(a.getCost(), player, c)){
				this.stack.push(new ItemOnStack(c,a,player,target,targetPlayer, targetZone));
				this.passed = false;
			}
			//TODO:Lol an exception or something
			break;
		case ETB:
			break;
		case STATIC:
			break;
		case TRIGGERED:
			break;
		default:
			break;
		}
	}

	//TODO:Test this more
	/**
	 * Handles paying the cost for an ability
	 * @param cost cost of the ability
	 * @param player player casting the ability
	 * @param c card the ability belongs to
	 * @return true if the ability cost could be payed, false if not
	 */
	private boolean payAbilityCost(String cost, Boolean player, Card c) {
		String[] costSplit = cost.split(",");
		for(int i = 0; i < costSplit.length; i++){
			if(costSplit[i].equals("TAP")){
				if(c.getTapped()){
					return false;
				}
			} else {
				//TODO:refactor this
				int[] costs = this.parseCost(cost);

				if(!((costs[0] <= ManaPool.getPool('w', player).getAmount()) &&
						(costs[1] <= ManaPool.getPool('u', player).getAmount()) &&
						(costs[2] <= ManaPool.getPool('b', player).getAmount()) &&
						(costs[3] <= ManaPool.getPool('r', player).getAmount()) &&
						(costs[4] <= ManaPool.getPool('g', player).getAmount()) &&
						((costs[0] + costs[1] + costs[2] + costs[3] + costs[4] + costs[5])<=
						(ManaPool.getPool('w', player).getAmount() + ManaPool.getPool('u', player).getAmount()
								+ ManaPool.getPool('b', player).getAmount() + ManaPool.getPool('r', player).getAmount()
								+ ManaPool.getPool('g', player).getAmount() + ManaPool.getPool('c', player).getAmount())))){
					return false;
				}
			}
		}
		for(int i = 0; i < costSplit.length; i++){
			if(costSplit[i].equals("TAP")){
				c.tap();
			} else {
				int[] costs = this.parseCost(cost);

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
			}
		}
		return true;
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
	public void addCard(Zone z, Card c, int i){
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
	public Card[] getZoneContents(Zone zone){
		return zone.getCards();
	}


	/**
	 * Removes the card at the given index from the zone
	 * @param z zone to remove card from
	 * @param i index to remove the card
	 */
	public void removeCard(Zone z, int i){
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

		if((this.phase == Phase.CLEANUP1) || (this.phase == Phase.CLEANUP2)){
			this.turn = !this.turn;
			this.landPlayed = false;
		}

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
					String[] splitEffect = item.getAbility().getEffect().split("-");
					item.getTarget().addDamage(Integer.parseInt(splitEffect[1]));
					if(item.getTarget().getDamage() >= item.getTarget().getToughness()){
						Zone.getZoneFromString("BATTLE_FIELD", Zone.getPlayerFromZone(item.getTargetZone())).remove(item.getTarget());
						Zone.getZoneFromString("GRAVEYARD", Zone.getPlayerFromZone(item.getTargetZone())).addCard(item.getTarget(),0);
						item.getTarget().resetDamage();
					}
					Zone.getZoneFromString(item.getAbility().getResolveZone()).addCard(item.getCard(),0);
				} else if (item.getTargetPlayer() != null) {
					String[] splitEffect = item.getAbility().getEffect().split("-");
					if(item.getTargetPlayer()){
						Health.HEALTH.remove(Integer.parseInt(splitEffect[1]));
					} else {
						Health.HEALTH1.remove(Integer.parseInt(splitEffect[1]));
					}
					Zone.getZoneFromString(item.getAbility().getResolveZone()).addCard(item.getCard(),0);

				} else {
					if((item.getAbility().getEffect() != null) && item.getAbility().getEffect().equals("ELF_TOKEN")){
						ArrayList<String> abilities = new ArrayList<String>();
						Card token = new Card("Elf Warrior", "", "G", "Creature- Elf Warrior", null, abilities, 1, 1, MTGDuelDecks.ELF_WARRIOR_TOKEN_PATH, false);
						if(item.getPlayer()){
							Zone.BATTLE_FIELD.addCard(token, 0);
						} else {
							Zone.BATTLE_FIELD1.addCard(token, 0);
						}
					} else {
						//TODO:This should probably actually check if the ability is a "CAST" effect
						if(item.getPlayer()){
							Zone.BATTLE_FIELD.addCard(item.getCard(), 0);
						} else {
							Zone.BATTLE_FIELD1.addCard(item.getCard(), 0);
						}
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
			if(c.getTapped()){
				throw new RejectedExecutionException("Mana ability of " + c.getName() + " cannot be activated: cannot pay cost");
			}
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

		if(s.length() == 0){
			return cost;
		}
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

	//TODO:more tests and improved checking for targeting
	/**
	 * Casts a spell, removing mana from the players mana pool accordingly
	 * @param zone the zone the spell is being cast from
	 * @param a the ability the spell is being cast with
	 * @param c the spell being cast
	 * @param index the index the spell is in
	 * @param player the player that owns the spell
	 * @param target the card being targeted
	 * @param targetPlayer the player being targeted
	 * @param targetZone the zone the targeted card is in
	 * @return true if the spell is cast, false if not
	 */
	public boolean castSpell(Zone zone, Ability a, Card c, int index, String cost, Boolean player, Card target, Boolean targetPlayer, Zone targetZone) {

		if(!c.isFlash() &&
				((this.turn != player) || (!this.stack.isEmpty()) ||
						((this.phase != Phase.FIRST_MAIN1) && (this.phase != Phase.SECOND_MAIN1) &&
								(this.phase != Phase.FIRST_MAIN2) && (this.phase != Phase.SECOND_MAIN2)))){
			return false;
		}
		if((target != null) && !canTarget(c, zone, target, targetZone)) {
			return false;
		}

		if(cost == null){
			throw new IllegalArgumentException("Illegal card " + c.getName() + ": card cost is null");
		}
		int[] costs = this.parseCost(cost);


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

			this.stack.push(new ItemOnStack(c, a, player, target, targetPlayer, targetZone));

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
