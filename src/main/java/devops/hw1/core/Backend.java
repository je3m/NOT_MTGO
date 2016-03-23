package devops.hw1.core;


public class Backend {
	Card cards[] = new Card[3];
	int i = 0, j = 0;

	/**
	 * Adds the given card to the given zone
	 * @param z Zone to add card to
	 * @param c Card to add
	 * @param i	position of the zone to add card
	 */
	void addCard(Zone z, Card c, int i){
		this.cards[this.i] = c;
		this.i++;
	}

	/**
	 * This method will get the contents (in order) of all cards of a given zone
	 * @param zone Zone enum that you want cards from
	 * @return Array of Card's in the given zone
	 */
	Card[] getZoneContents(Zone zone){
		Card[] c = new Card[this.j+1];
		c[0] =  this.cards[this.j];
		c[this.j] = this.cards[this.j];
		this.j++;

		return c;
	}

	/**
	 * Removes the card at the given index from the zone
	 * @param index of card to remove
	 */
	void removeCard(Zone z, int i){

	}
}
