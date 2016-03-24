package devops.hw1.core;

import java.awt.Rectangle;

public class GUICard {
	private Rectangle rec;
	private Card card;
	
	public GUICard(Rectangle rec, Card card){
		this.setRec(rec);
		this.setCard(card);
	}

	public Rectangle getRec() {
		return rec;
	}

	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
