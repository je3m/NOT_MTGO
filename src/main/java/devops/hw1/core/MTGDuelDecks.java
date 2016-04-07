package devops.hw1.core;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

/**
 * Main MTG Duel Deck class that runs the rest of the game.
 *
 */
public class MTGDuelDecks {
	/**
	 * Main method that runs the game and sets up the GUI frame.
	 * @param args
	 */
	public static void main(String[] args){
		setUpJFrame();
		
		initializePlayer();
		initializePlayer1();
	}
	

	/**
	 * Initializes the game data for the first player
	 * 
	 */
	private static void initializePlayer() {
		//Test
		Zone.GRAVEYARD.addCard(new Card("Grave"), 0);
		Zone.LIBRARY.addCard(new Card("Libr"), 0);
		
		Card hand1 = new Card("Hand1");
		hand1.addAbility("Play this card");
		hand1.setImage("Forest.jpg");
		Zone.HAND.addCard(hand1,0);
		
		Card hand2 = new Card("Hand2");
		hand2.addAbility("Play this card");
		hand2.setImage("Forest.jpg");
		Zone.HAND.addCard(hand2, 0);
		
		Zone.HAND.addCard(new Card("Hand3"), 0);
		Zone.HAND.addCard(new Card("Hand4"), 0);
		Zone.HAND.addCard(new Card("Hand5"), 0);
		Zone.HAND.addCard(new Card("Hand6"), 0);
		Zone.HAND.addCard(new Card("Hand7"), 0);
		Zone.HAND.addCard(new Card("Hand8"), 0);
		Zone.HAND.addCard(new Card("Hand9"), 0);
		
		Card BF1 = new Card("BF1");
		BF1.setImage("Forest.jpg");
		Zone.BATTLE_FIELD.addCard(BF1, 0);
		
		
		
	}
	
	
	/**
	 * Initializes the game data for the second player
	 * 
	 */
	private static void initializePlayer1() {
		//Test
		Zone.GRAVEYARD1.addCard(new Card("Grave"), 0);
		Zone.LIBRARY1.addCard(new Card("Libr"), 0);
		

		Card hand1 = new Card("Hand11");
		hand1.addAbility("Play this card");
		hand1.setImage("Mountain.jpg");
		Zone.HAND1.addCard(hand1, 0);
		Zone.HAND1.addCard(new Card("Hand2"), 0);
		Zone.HAND1.addCard(new Card("Hand3"), 0);
		Zone.HAND1.addCard(new Card("Hand4"), 0);
		Zone.HAND1.addCard(new Card("Hand5"), 0);
		Zone.HAND1.addCard(new Card("Hand6"), 0);
		Zone.HAND1.addCard(new Card("Hand7"), 0);
		
		Card BF2 = new Card("BF2");
		BF2.setImage("Mountain.jpg");
		Zone.BATTLE_FIELD1.addCard(BF2, 0);
		
		
	}
	
	
	
	
	/**
	 * Sets up the JFrame to include all the default values for the game
	 * @return initialized game JFrame
	 */
	private static JFrame setUpJFrame(){
		final JFrame Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Duel Decks");
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		final MTGComponent MTGComp = new MTGComponent(Frame.getContentPane().getWidth(), Frame.getContentPane().getHeight());
		Frame.add(MTGComp);
		Frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	MTGComp.setWindowX(Frame.getContentPane().getWidth());
		    	MTGComp.setWindowY(Frame.getContentPane().getHeight());
		    }
		});
		ClickHandler ch = new ClickHandler(MTGComp);
		Frame.addMouseListener(ch);
		return Frame;
	}


	public Object[] getPhase() {
		// TODO Auto-generated method stub
		return null;
	}
}
