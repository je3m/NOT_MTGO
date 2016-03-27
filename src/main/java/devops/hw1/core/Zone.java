package devops.hw1.core;

import java.util.ArrayList;

public enum Zone {
	BATTLE_FIELD,
	GRAVEYARD,
	LIBRARY,
	HAND,
	EXILE,
	BATTLE_FIELD1,
	GRAVEYARD1,
	LIBRARY1,
	HAND1,
	EXILE1
	;

	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * Adds a card to the zone at the given index
	 * @param c card to add
	 * @param i index to place card
	 */
	public void addCard(Card c, int i){//!#should throw illegalargexcep for i val outside range of indices
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
	 * @return an array of cards that are within the zone
	 */
	public Card[] getCards(){

		return this.cards.toArray(new Card[0]);
	}

	/**
	 * Gets the index of the first occurrence of the given card
	 * @param name name of card
	 * @return index of the card in the zone
	 */
	public int getIndex(String name) {
		int i = 0;
		for (Card c : this.cards){
			if(name.equals(c.getName()))
				return i;
			i++;
		}
		return -1;
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
	public void remove(int i)  throws IndexOutOfBoundsException {
		if(i>= this.getSize()) {
			throw new IndexOutOfBoundsException("No object exists in the " + this + " zone at index " + i);
		}
		this.cards.remove(i);

	}
}
