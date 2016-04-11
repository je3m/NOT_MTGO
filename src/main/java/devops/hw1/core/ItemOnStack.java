package devops.hw1.core;

/**
 * Represents an object on the stack
 *
 */
public class ItemOnStack {
	private Card c;
	private Boolean player;
	
	/**
	 * Constructs an item on the stack object
	 * @param c card on the stack
	 * @param player player that owns the card (true if first player)
	 */
	ItemOnStack(Card c, Boolean player){
		this.c = c;
		this.player = player;
	}
	
	/**
	 * Gets the card from the item on stack
	 * @return the card on the stack
	 */
	public Card getCard() {
		return c;
	}
	/**
	 * Sets the card for the item on stack
	 * @param c new card
	 */
	public void setCard(Card c) {
		this.c = c;
	}
	
	/**
	 * Gets the player that owns the item on stack
	 * @return true if the first player owns it, false otherwise
	 */
	public Boolean getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player that owns the item on the stack
	 * @param player the new player
	 */
	public void setPlayer(Boolean player) {
		this.player = player;
	}
	
	
}
