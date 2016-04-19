package devops.hw1.core;

/**
 * Represents an object on the stack
 *
 */
public class ItemOnStack {
	private Card c;
	private Card target;
	private Boolean player;
	
	/**
	 * Constructs an item on the stack object
	 * @param c card on the stack
	 * @param player player that owns the card (true if first player)
	 */
	ItemOnStack(Card c, Boolean player, Card target){
		this.c = c;
		this.player = player;
		this.target = target;
	}
	
	/**
	 * Gets the card from the item on stack
	 * @return the card on the stack
	 */
	public Card getCard() {
		return c;
	}
	
	/**
	 * Gets the player that owns the item on stack
	 * @return true if the first player owns it, false otherwise
	 */
	public Boolean getPlayer() {
		return player;
	}
	
	/**
	 * Gets the card that this item on the stack is targeting
	 * @return the card being targeted by this stack item
	 */
	public Card getTarget() {
		return target;
	}
}
