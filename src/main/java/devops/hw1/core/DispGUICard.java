package devops.hw1.core;

import java.awt.Rectangle;
/**
 * This class represents the currently displayed card for a player
 * @author malinocr
 *
 */
public class DispGUICard extends GUICard{

	/**
	 * Constructs a displayed GUI card
	 * @param rec the rectangle that is drawn for the GUI card
	 * @param card the card the GUI card represents
	 */
	public DispGUICard(Rectangle rec, Card card) {
		super(rec, card);
	}
}
