package devops.hw1.core;

import java.util.ArrayList;

public enum Zone {
	BATTLE_FIELD,
	GRAVEYARD,
	LIBRARY,
	HAND,
	EXILE;

	private ArrayList<Card> cards = new ArrayList<Card>();

	public void addCard(Card c, int i){
		this.cards.add(i, c);
	}

	public Card[] getCards(){

		return this.cards.toArray(new Card[0]);
	}

	public int getSize() {
		return this.cards.size();
	}
}
