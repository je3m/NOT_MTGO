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

	/**
	 *
	 * @return the name of the card
	 */
	public String getName(){
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
