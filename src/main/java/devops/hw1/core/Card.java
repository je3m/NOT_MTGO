package devops.hw1.core;

import java.util.ArrayList;
import java.util.regex.*;

public class Card {
	private String name;
	private String manaCost;
	private String color;
	private String type;
	private ArrayList<String> abilities = new ArrayList<String>();
	private int power, toughness;
	private String image;

	/**
	 * Constructs a card object with the given name
	 * @param s name of the card
	 */
	public Card(String s){
		this.name = s;
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
	 * @return color of card represented by [WUBRG]*
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Gets the cost of the card expressed as oracle text
	 * without curly braces.
	 * Conforms to regex [0-9]*[WUBRG]*
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
	 * @param string color of card represented by [WUBRG]*
	 */
	public void setColor(String string) {//!# INPUT VALIDATE W/ REGEX
		String regex = "[WUBRG]*";
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
	public void setCost(String s){//!# INPUT VALIDATE W/ REGEX
		
		this.manaCost = s;
	}

	/**
	 * sets image path for displaying the card
	 * @param string filepath to image
	 */
	public void setImage(String string) {//!# INPUT VALIDATE W/ REGEX
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
	public void setType(String s) {//!# INPUT VALIDATE W/ REGEX
		this.type = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
