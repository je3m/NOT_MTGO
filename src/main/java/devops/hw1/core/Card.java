package devops.hw1.core;

public class Card {
	private String name;
	private String manaCost;
	private String color;

	/**
	 * Constructs a card object with the given name
	 * @param s name of the card
	 */
	public Card(String s){
		this.name = s;
	}

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

	@Override
	public String toString() {
		return this.name;
	}
}
