package devops.hw1.core;


public class Backend {

	/**
	 * TODO WRITE THIS
	 */
	static void handleCardClicked(Zone z, int currIndex, Card c) {
		z.remove(currIndex);
		switch(z) {
		case HAND:
			Zone.BATTLE_FIELD.addCard(c, 0);
			break;
		case HAND1:
			Zone.BATTLE_FIELD1.addCard(c, 0);
			break;
		case BATTLE_FIELD:
			Zone.GRAVEYARD.addCard(c, 0);
			break;
		case BATTLE_FIELD1:
			Zone.GRAVEYARD1.addCard(c, 0);
			break;
		default:
			throw new IllegalArgumentException(z + " zone is not a valid zone for card click events.");
		}
	}
	
	/**
	 * Adds the given card to the given zone
	 * @param z Zone to add card to
	 * @param c Card to add
	 * @param i	position of the zone to add card
	 */
	void addCard(Zone z, Card c, int i){
		z.addCard(c, i);
	}

	/**
	 * This method will get the contents (in order) of all cards of a given zone
	 * @param zone Zone enum that you want cards from
	 * @return Array of Card's in the given zone
	 */
	Card[] getZoneContents(Zone zone){
		return zone.getCards();
	}

	/**
	 * Removes the card at the given index from the zone
	 * @param index of card to remove
	 */
	void removeCard(Zone z, int i){
		z.remove(i);
	}
}
