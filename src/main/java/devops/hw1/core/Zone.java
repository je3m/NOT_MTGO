package devops.hw1.core;

import java.util.ArrayList;

public enum Zone {
	BATTLE_FIELD,
	GRAVEYARD,
	LIBRARY,
	HAND,
	EXILE;

	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * Adds a card to the zone at the given index
	 * @param c card to add
	 * @param i index to place card
	 */
	public void addCard(Card c, int i){
		this.cards.add(i, c);
	}

	/**
	 * Determines if the zone contains a card with the given name
	 * @param name name of the card to look for
	 * @return true if the card is found in the zone, false otherwise
	 */
	public Boolean contains(String name) {
		for (Card c : this.cards){
			if(c.name.equals(name))
				return true;
		}
		return false;
	}

	/**
	 * empties all contents from the zone
	 */
	public void empty() {
		this.cards.clear();

	}

	/**
	 *
	 * @return a list of cards that are within the zone
	 */
	public Card[] getCards(){

		return this.cards.toArray(new Card[0]);
	}

	/**
	 *
	 * @return the number of cards in the zone
	 */
	public int getSize() {
		return this.cards.size();
	}


	/**
	 * Removes the card at the given index from the zone
	 * @param i index of card to remove
	 */
	public void remove(int i) {
		this.cards.remove(i);

	}
}
