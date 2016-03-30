package devops.hw1.core;

public class Card {
	String name;

	/**
	 * Constructs a card object with the given name
	 * @param s name of the card
	 */
	public Card(String s){
		this.name = s;
	}

	public String getCost(){
		return "1U";
	}

	/**
	 *
	 * @return the name of the card
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Sets the cost of the card expressed as oracle text
	 * without curly braces.
	 * Ex: (Storm Crow -> '1U', Emrakul -> '96' or '555')
	 * @param s mana cost of card
	 */
	public void setCost(String s){
		return;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
