package front_end;

import java.awt.Rectangle;
import java.util.ArrayList;

import back_end.Ability;
import back_end.Card;
import back_end.Zone;
/**
 * This class represents the currently displayed card for a player
 * @author malinocr
 *
 */
public class DispGUICard extends GUICard{
	String[] abilityStrings;
	Integer[] abilityIndecies;
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
		Ability[] a = card.getAbilities();

		ArrayList<String> abilityStr = new ArrayList<String>();
		ArrayList<Integer> abilityInt = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++){
			if(Zone.getZoneFromString(a[i].getZone()) == zone || Zone.getZoneFromString(a[i].getZone() + Messages.getString("DispGUICard.0")) == zone){ //$NON-NLS-1$
				abilityStr.add(a[i].getText());
				abilityInt.add(i);
			}
		}
		this.abilityStrings = abilityStr.toArray(new String[0]);
		this.abilityIndecies = abilityInt.toArray(new Integer[0]);

		if(index > zone.getSize()) {
			throw new IllegalArgumentException(Messages.getString("DispGUICard.1") + card.getName() + //$NON-NLS-1$
					Messages.getString("DispGUICard.2") + index + Messages.getString("DispGUICard.3") + zone); //$NON-NLS-1$ //$NON-NLS-2$
		}
		this.index = index;
		this.zone = zone;
		this.generateAbilityBoxes();
	}

	/**
	 * Generates ability boxes for the DispGUICards based on the ability strings and the cards size
	 */
	private void generateAbilityBoxes() {
		
		this.abilityBoxes = new Rectangle[this.abilityStrings.length];
		int height = Math.min((int)(this.rec.getHeight()/4), (int)(this.rec.getHeight()/this.abilityStrings.length));
		int width = (int)(this.rec.getWidth()/2);
		for(int i = 0; i < this.abilityStrings.length; i++){
			this.abilityBoxes[i] = new Rectangle((int)(this.rec.getX() + this.rec.getWidth()), (int)(this.rec.getY() + (height * i)), width, height);
		}
	}

	/**
	 * Gets the ability strings for the display GUI card
	 * @return the abilityStrings
	 */
	public String[] getAbilityStrings() {
		return this.abilityStrings;
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
		return this.abilityBoxes;
	}

	/**
	 * Gets the index of the card represented by the GUI card
	 * @return index of the card
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Gets the zone of the card represented by the GUI card
	 * @return index of the card
	 */
	public Zone getZone() {
		return this.zone;
	}

	/**
	 * Gets the index of the original ability given the index of a displayed ability
	 * @param i index of displayed ability
	 * @return index of original ability
	 */
	public int getAbilityIndex(int i) {
		return this.abilityIndecies[i];
	}
}
