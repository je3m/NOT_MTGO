package devops.hw1.core;

public class ItemOnStack {
	private Card c;
	private Boolean player;
	
	ItemOnStack(Card c, Boolean player){
		this.c = c;
		this.player = player;
	}
	
	/**
	 * @return the c
	 */
	public Card getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(Card c) {
		this.c = c;
	}
	/**
	 * @return the player
	 */
	public Boolean getPlayer() {
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Boolean player) {
		this.player = player;
	}
	
	
}
