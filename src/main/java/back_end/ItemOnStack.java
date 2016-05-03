package back_end;

/**
 * Represents an object on the stack
 *
 */
public class ItemOnStack {
	private Card c;
	private Ability a;
	private Boolean player;
	private Card target;
	private Boolean targetPlayer;
	private Zone targetZone;

	/**
	 * Constructs an item on stack object
	 * @param c card the ability is from
	 * @param a ability on the stack
	 * @param player the player that owns the ability
	 * @param target the target card of the ability (null if the target is a player)
	 * @param targetPlayer the target player of an ability
	 * @param targetZone the zone of the targeted card
	 */
	public ItemOnStack(Card c, Ability a, Boolean player, Card target, Boolean targetPlayer, Zone targetZone){
		this.c = c;
		this.a = a;
		this.player = player;
		this.target = target;
		this.targetPlayer = targetPlayer;
		this.targetZone = targetZone;
	}

	/**
	 * Gets the card from the item on stack
	 * @return the card on the stack
	 */
	public Card getCard() {
		return this.c;
	}
	
	/**
	 * Gets the ability from the item on stack
	 * @return the ability on the stack
	 */
	public Ability getAbility() {
		return this.a;
	}

	/**
	 * Gets the player that owns the item on stack
	 * @return true if the first player owns it, false otherwise
	 */
	public Boolean getPlayer() {
		return this.player;
	}

	/**
	 * Gets the card that this item on the stack is targeting
	 * @return the card being targeted by this stack item
	 */
	public Card getTarget() {
		return this.target;
	}
	
	/**
	 * Gets the player that this item on the stack is targeting
	 * @return the player being targeted by this stack item
	 */
	public Boolean getTargetPlayer() {
		return this.targetPlayer;
	}

	/**
	 * Gets the zone that the targeted card is in
	 * @return the zone occupied by the card that this stack item is targeting
	 */
	public Zone getTargetZone() {
		return this.targetZone;
	}
}
