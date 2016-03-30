package devops.hw1.core;

import java.util.ArrayList;

public class Card {
	private String name;
	private String manaCost;
	private String color;
	private String type;
	private ArrayList<String> abilities = new ArrayList<String>();
	private int power, toughness;

	/**
	 * Constructs a card object with the given name
	 * @param s name of the card
	 */
	public Card(String s){
		this.name = s;
	}

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
		return (this.abilities.toArray(new String[1]));
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
	 *
	 * @return the name of the card
	 */
	public String getName(){
		return this.name;
	}

	public int getPower() {
		return this.power;
	}

	public int getToughness() {
		return this.toughness;

	}

	public String getType() {
		return this.type;

	}

	/**
	 * Sets the color of the card to given card
	 * @param string color of card represented by [WUBRG]*
	 */
	public void setColor(String string) {
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
		this.manaCost = s;
	}

	public void setPower(int i) {
		this.power = i;

	}

	public void setPT(int p, int t){
		this.toughness = t;
		this.power = p;
	}

	public void setToughness(int i) {
		this.toughness = i;

	}

	public void setType(String s) {
		this.type = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
