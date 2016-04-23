package front_end;

import java.awt.Rectangle;

import back_end.Card;
import back_end.Zone;
/**
 * This class represents the currently displayed card for a player
 * @author malinocr
 *
 */
public class DispGUICard extends GUICard{
		String[] abilityStrings;
		Rectangle[] abilityBoxes;
		int index;
		Zone zone;

	/**
	 * Constructs a displayed GUI card
	 * @param rec the rectangle that is drawn for the GUI card
	 * @param card the card the GUI card represents
	 * @param str array of the ability strings of the card
	 * @param index index of the card in its original zone
	 * @param zone zone of the card
	 */
	public DispGUICard(Rectangle rec, Card card, int index, Zone zone) {
		super(rec, card);
		this.abilityStrings = card.getAbilities();
		
		if(index > zone.getSize()) {
			throw new IllegalArgumentException("DispGUICard " + card.getName() +
					": " + index + " is not a valid index for zone " + zone);
		}
		this.index = index;
		this.zone = zone;
		generateAbilityBoxes();
	}

	/**
	 * Generates ability boxes for the DispGUICards based on the ability strings and the cards size
	 */
	private void generateAbilityBoxes() {
		abilityBoxes = new Rectangle[this.abilityStrings.length];
		int height = Math.min((int)(this.rec.getHeight()/4), (int)(this.rec.getHeight()/this.abilityStrings.length));
		int width = (int)(this.rec.getWidth()/2);
		for(int i = 0; i < this.abilityStrings.length; i++){
			abilityBoxes[i] = new Rectangle((int)(this.rec.getX() + this.rec.getWidth()), (int)(this.rec.getY() + (height * i)), width, height);
		}
	}

	/**
	 * Gets the ability strings for the display GUI card
	 * @return the abilityStrings
	 */
	public String[] getAbilityStrings() {
		return abilityStrings;
	}

	/**
	 * Sets the ability strings for the display GUI card
	 * @param abilityStrings the abilityStrings to set
	 */
	public void setAbilityStrings(String[] abilityStrings) {
		this.abilityStrings = abilityStrings;
	}

	/**
	 * Gets the ability boxes for the display GUI card
	 * @return the abilityBoxes
	 */
	public Rectangle[] getAbilityBoxes() {
		return abilityBoxes;
	}
	
	/**
	 * Gets the index of the card represented by the GUI card
	 * @return index of the card
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Gets the zone of the card represented by the GUI card
	 * @return index of the card
	 */
	public Zone getZone() {
		return zone;
	}
}
