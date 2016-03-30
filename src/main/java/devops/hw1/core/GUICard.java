package devops.hw1.core;

import java.awt.Rectangle;
/**
 * This class represents a card that is displayed in the GUI
 * @author malinocr
 *
 */
public class GUICard {
	private Rectangle rec;
	private Card card;
	private Boolean clickable;
	
	/**
	 * Constructs a GUI card object
	 * @param rec the rectangle that is displayed for the card
	 * @param card the card the GUI object represents
	 */
	public GUICard(Rectangle rec, Card card){
		this.setRec(rec);
		this.setCard(card);
		this.clickable = true;
	}

	/**
	 * Get the GUI card's rectangle
	 * @return the GUI card's rectangle
	 */
	public Rectangle getRec() {
		return rec;
	}

	/**
	 * Set the GUI card's rectangle
	 * @return the GUI card's rectangle
	 */
	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	/**
	 * Get the GUI card's card
	 * @return the GUI card's card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Set the GUI card's card
	 * @return the GUI card's card
	 */
	public void setCard(Card card) {
		this.card = card;
	}
}
