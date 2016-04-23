package back_end;

/**
 * Represents an object on the stack
 *
 */
public class ItemOnStack {
	private Card c;
	private Card target;
	private Boolean player;
	private Zone targetZone;

	/**
	 * Constructs an item on the stack object
	 * @param c card on the stack
	 * @param player player that owns the card (true if first player)
	 */
	public ItemOnStack(Card c, Boolean player, Card target, Zone targetZone){
		this.c = c;
		this.player = player;
		this.target = target;
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
	 * Gets the zone that the targeted card is in
	 * @return the zone occupied by the card that this stack item is targeting
	 */
	public Zone getTargetZone() {
		return this.targetZone;
	}
}
