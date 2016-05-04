package back_end;

import java.util.ArrayList;
/**
 * Represents the zones in MTG
 *
 */
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
	public void addCard(Card c, int i){
		if((i > this.getSize()) || (i < 0)) {
			throw new IndexOutOfBoundsException("Index " + i + " is not valid for the " + this + " zone");
		}
		this.cards.add(i, c);
	}

	/**
	 * Determines if the zone contains a card with the given name
	 * @param name name of the card to look for
	 * @return true if the card is found in the zone, false otherwise
	 */
	public Boolean contains(String name) {
		for (Card c : this.cards){
			if(c.getName().equals(name))
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

	//TODO:Test this
	/**
	 * Gets the index of a card in the zone
	 * @param c card in the zone
	 * @return index of the card
	 */
	public int getIndex(Card c){
		return cards.lastIndexOf(c);
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
	
	//TODO:Test this
	/**
	 * Removes a card from the zone
	 * @param c Card to be removed
	 */
	public void remove(Card c) {
		cards.remove(c);
	}

	/**
	 * Returns the zone corresponding to the given string
	 * @param string name of the zone
	 * @return Zone enum that matches that string
	 */
	public static Zone getZoneFromString(String string) {
		
		if(string == null) {
			throw new IllegalArgumentException("Zone name string cannot be null.");
		}
		for (Zone z: Zone.values()){
			if (string.equals(z.name())){
				return z;
			}
		}
		throw new IllegalArgumentException("invalid zone " + string);
	}

	/**
	 * Returns the player that a given zone belongs to
	 * @param zone the zone whose player is being identified
	 * @return the player that a given zone belongs to
	 */
	public static boolean getPlayerFromZone(Zone zone) {
		return zone.ordinal() < 5;
	}

	/**
	 * Returns the zone corresponding to the given string and player
	 * @param string name of the zone
	 * @param player the player to whom the zone belongs
	 * @return Zone enum that matches that string and player
	 */
	public static Zone getZoneFromString(String string, boolean player) {


		if(string == null) {
			throw new IllegalArgumentException("Zone name string cannot be null.");
		}
		
		if(!player)
			string = string.concat("1");
		for (Zone z: Zone.values()){
			if (string.equals(z.name())){
				return z;
			}
		}
		return null;
	}



}
