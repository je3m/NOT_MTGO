package devops.hw1.core;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class MTGDuelDecks {
	
	
	public static void main(String[] args){
		JFrame MTGJFrame = setUpJFrame();
		Zone.GRAVEYARD.addCard(new Card("Grave"), 0);
		Zone.LIBRARY.addCard(new Card("Libr"), 0);
		Zone.GRAVEYARD1.addCard(new Card("Grave"), 0);
		Zone.LIBRARY1.addCard(new Card("Libr"), 0);
		Zone.HAND.addCard(new Card("Hand1"), 0);
		Zone.HAND.addCard(new Card("Hand2"), 0);
		Zone.HAND.addCard(new Card("Hand3"), 0);
		Zone.HAND.addCard(new Card("Hand4"), 0);
		Zone.HAND.addCard(new Card("Hand5"), 0);
		Zone.HAND.addCard(new Card("Hand6"), 0);
		Zone.HAND.addCard(new Card("Hand7"), 0);
		Zone.HAND.addCard(new Card("Hand8"), 0);
		Zone.HAND.addCard(new Card("Hand9"), 0);
		Zone.HAND1.addCard(new Card("Hand1"), 0);
		Zone.HAND1.addCard(new Card("Hand2"), 0);
		Zone.HAND1.addCard(new Card("Hand3"), 0);
		Zone.HAND1.addCard(new Card("Hand4"), 0);
		Zone.HAND1.addCard(new Card("Hand5"), 0);
		Zone.HAND1.addCard(new Card("Hand6"), 0);
		Zone.HAND1.addCard(new Card("Hand7"), 0);
		Zone.BATTLE_FIELD.addCard(new Card("BF1"), 0);
		Zone.BATTLE_FIELD1.addCard(new Card("BF2"), 0);
	}
	
	/**
	 * Sets up the JFrame to include all the default values for the game
	 * @return initialized game JFrame
	 */
	private static JFrame setUpJFrame(){
		JFrame Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Duel Decks");
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		MTGComponent MTGComp = new MTGComponent(Frame.getContentPane().getWidth(), Frame.getContentPane().getHeight());
		Frame.add(MTGComp);
		Frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	MTGComp.setWindowX(Frame.getContentPane().getWidth());
		    	MTGComp.setWindowY(Frame.getContentPane().getHeight());
		    }
		});
		return Frame;
	}
}
