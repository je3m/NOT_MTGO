package devops.hw1.core;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class Card {
	private String name;
	private String manaCost;
	private String color;
	private String type;
	private String manaAbility;
	private ArrayList<String> abilities = new ArrayList<String>();
	private int power, toughness;
	private String image;
	private Boolean tapped;
	private Boolean flash;

	/**
	 * Constructs a card object with the given name
	 * @param s name of the card
	 */
	@Deprecated
	public Card(String s){
		this.name = s;
		this.tapped = false;
		this.manaAbility = null;
		this.flash = false;
	}
	
	public Card(String name, String manaCost, String color, String type,
			String manaAbility, ArrayList<String> abilities, int power,
			int toughness, String image, Boolean flash){
		this.name = name;
		if(abilities == null){
			throw new IllegalArgumentException("Error creating card " + name + ": null is not a valid ability list");
		}
		this.abilities = abilities;
		try{
			this.setCost(manaCost);
			this.setColor(color);
			this.setType(type);
			this.addManaAbility(manaAbility);
			this.setPT(power, toughness);
			this.setImage(image);
		} catch (Exception e){
			throw new IllegalArgumentException("Error creating card " + name + ": " + e.getMessage());
		}
		this.tapped = false;
		this.flash = flash;
	}

	//!# add more constructors

	/**
	 * Adds ability to card
	 * TODO: come up with a good way to represent abilities (Mckee)
	 * @param string ability to add following TODO: format
	 */
	public void addAbility(String string) {
		this.abilities.add(string);

	}

	/**
	 *
	 * @return a string of all abilities that card has
	 */
	public String[] getAbilities() {
		return (this.abilities.toArray(new String[0]));
	}

	/**
	 * @return color of card represented by W?U?B?R?G?
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Gets the cost of the card expressed as oracle text
	 * without curly braces.
	 * Conforms to regex [0-9]*W*U*B*R*G*
	 */
	public String getCost(){
		return this.manaCost;
	}

	/**
	 * @return path of image to card
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 *
	 * @return the name of the card
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @return power of card
	 */
	public int getPower() {
		return this.power;
	}

	/**
	 * @return toughness of card
	 */
	public int getToughness() {
		return this.toughness;

	}

	/**
	 * Type and subtype of card
	 * <Type>- <subtype>*
	 * types are seperated by spaces
	 * (example Storm Crow -> "Creature- bird")
	 * @return types of card
	 */
	public String getType() {
		return this.type;

	}

	/**
	 * Sets the color of the card to given card
	 * @param string color of card represented by W?U?B?R?G?
	 */
	public void setColor(String string) {
		String regex = "W?U?B?R?G?";
		if(!string.matches(regex)) {
			throw new PatternSyntaxException("Card " + this.name + ": " + string + " is not a valid color", regex, -1);
		}
		this.color = string;

	}

	/**
	 * Sets the cost of the card expressed as oracle text
	 * without curly braces.
	 * Conforms to regex [0-9]*[WUBRG]*
	 *
	 * Ex: (Storm Crow -> '1U', Emrakul -> '15')
	 * @param s mana cost of card
	 */
	public void setCost(String s){
		String regex = "[0-9]*W*U*B*R*G*";
		if(!s.matches(regex)) {
			throw new PatternSyntaxException("Card " + this.name + ": " + s + " is not a valid mana cost", regex, -1);
		}
		this.manaCost = s;
	}

	/**
	 * sets image path for displaying the card
	 * @param string filepath to image
	 */
	public void setImage(String string) {
		File test = new File(string);
		if(!test.exists()) {
			throw new IllegalArgumentException("Card " + this.name + ": " + string + " is not a valid file name");
		}
		this.image = string;

	}

	/**
	 * sets the power of card (if creature)
	 * @param i power of creature
	 */
	public void setPower(int i) {//!# CHECK IF IS CREATURE
		this.power = i;

	}

	/**
	 * Sets both the power and toughness of card
	 * @param p power of card
	 * @param t toughness of card
	 */
	public void setPT(int p, int t){//!# CHECK IF IS CREATURE
		this.toughness = t;
		this.power = p;
	}

	/**
	 * sets toughness of card
	 * @param i toughness
	 */
	public void setToughness(int i) {//!# CHECK IF IS CREATURE
		this.toughness = i;

	}

	/**
	 * Sets the type of given card defined as
	 * <Type>- <subtype>*
	 * (example Storm Crow -> "Creature- bird")
	 * @param s type of card
	 */
	public void setType(String s) {
		if(s == null){
			throw new IllegalArgumentException("Card " + this.name + ": null is not a valid card type");
		}
		String regex = "[A-Z][a-z]*(\\- [A-Z][a-z]*( [A-Z][a-z]*)*)?";
		if(!s.matches(regex)) {
			throw new PatternSyntaxException("Card " + this.name + ": " + s + " is not a valid card typeline", regex, -1);
		} 
		this.type = s;
	}
	
	/**
	 * Sets whether this card can be cast at instant speed independent of its card typeline
	 * @param flash whether this card can be cast at instant speed
	 */
	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Adds a mana ability to the card
	 * @param manaAbility added mana ability
	 */
	public void addManaAbility(String manaAbility) {
		this.manaAbility = manaAbility;
	}

	/**
	 * Gets the mana ability of the card
	 * @return mana ability
	 */
	public String getManaAbility() {
		return this.manaAbility;
	}

	/**
	 * Determines if a card is tapped
	 * @return true if tapped, false if not
	 */
	public Boolean getTapped() {
		return this.tapped;
	}
	
	/**
	 * Determines whether this card can be cast at instant speed
	 * @return whether this card can be cast at instant speed
	 */
	public Boolean isFlash() {
		return this.flash || this.type.contains("Instant");
	}

	/**
	 * Taps a card
	 * @return true if the card was previously untapped, false otherwise
	 */
	public boolean tap() {
		if (this.tapped)
			return false;

		this.tapped = true;
		return true;
	}

	/**
	 * Untaps a card
	 */
	public void untap() {
		this.tapped = false;
	}
}
