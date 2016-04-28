package back_end;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import front_end.ClickHandler;
import front_end.KeyBoardHandler;
import front_end.MTGComponent;

/**
 * Main MTG Duel Deck class that runs the rest of the game.
 */
public class MTGDuelDecks {
	public static final String MOUNTAIN_PATH = "res/Mountain.jpg";
	public static final String FOREST_PATH = "res/Forest.jpg";
	public static final String LLANOWAR_ELVES_PATH = "res/Llanowar_Elves.jpg";
	public static final String TARFIRE_PATH = "res/Tarfire.jpg";

	/**
	 * Main method that runs the game and sets up the GUI frame.
	 * @param args
	 */
	public static void main(String[] args){
		Backend bkd = Backend.getInstance();
		setUpJFrame(bkd);

		initializePlayer();
		initializePlayer1();
	}


	/**
	 * Initializes the game data for the first player
	 *
	 */
	private static void initializePlayer() {
		//Test
		Zone.GRAVEYARD.addCard(new Card("Grave", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.LIBRARY.addCard(new Card("Libr", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);

		Card hand1 = new Card("Hand1", "", "", "", "T:G", 
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);

		hand1.addAbility("Play this card");
		hand1.addAbility("Tap: Add G to your mana pool");

		Zone.HAND.addCard(hand1,0);

		Card hand2 = new Card("Hand2", "", "", "", "T:G", 
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);
		hand2.addAbility("Play this card");
		hand2.addAbility("Tap: Add G to your mana pool");

		Zone.HAND.addCard(hand2, 0);

		Card hand3 = new Card("Arbor elf", "G", "G", "Creature- Elf Druid", "T:G", 
				new ArrayList<String>(), 1, 1, LLANOWAR_ELVES_PATH, false);
		hand3.addAbility("Play this card");
		hand3.addAbility("Tap: Add G to your mana pool");



		Zone.HAND.addCard(hand3, 0);
		Zone.HAND.addCard(new Card("Hand4", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND.addCard(new Card("Hand5", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND.addCard(new Card("Hand6", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND.addCard(new Card("Hand7", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND.addCard(new Card("Hand8", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND.addCard(new Card("Hand9", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);




		Card BF1 = new Card("BF1", "", "", "", "T:G", 
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);

		BF1.addAbility("Play this Card");
		BF1.addAbility("Tap: Add G to your mana pool");
		Zone.BATTLE_FIELD.addCard(BF1, 0);



	}


	/**
	 * Initializes the game data for the second player
	 *
	 */
	private static void initializePlayer1() {
		//Test
		Zone.GRAVEYARD1.addCard(new Card("Grave", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.LIBRARY1.addCard(new Card("Libr", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);


		Card hand1 = new Card("Hand11", "", "", "Land", "T:R", 
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false);
		hand1.addAbility("Play this card");
		hand1.addAbility("Tap: Add R to your mana pool");

		Card hand2 = new Card("tarfire", "R", "R", "Instant- Goblin", "", 
				new ArrayList<String>(), 0, 0, TARFIRE_PATH, true);
		hand2.addAbility("play this card");

		Zone.HAND1.addCard(hand1, 0);
		Zone.HAND1.addCard(hand2, 0);
		Zone.HAND1.addCard(new Card("Hand3", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND1.addCard(new Card("Hand4", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND1.addCard(new Card("Hand5", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND1.addCard(new Card("Hand6", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);
		Zone.HAND1.addCard(new Card("Hand7", "", "", "", "", 
				new ArrayList<String>(), 0, 0, "", false), 0);

		Card BF2 = new Card("BF2", "", "", "", "T:R", 
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false);
		BF2.addAbility("Play this card");
		BF2.addAbility("Tap: Add R to your mana pool");
		Zone.BATTLE_FIELD1.addCard(BF2, 0);


	}




	/**
	 * Sets up the JFrame to include all the default values for the game
	 * @return initialized game JFrame
	 */
	private static JFrame setUpJFrame(Backend bkd){
		final JFrame Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setTitle("MTG Duel Decks");
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		final MTGComponent MTGComp = new MTGComponent(Frame.getContentPane().getWidth(), Frame.getContentPane().getHeight(), bkd);
		Frame.add(MTGComp);
		Frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				MTGComp.setWindowX(Frame.getContentPane().getWidth());
				MTGComp.setWindowY(Frame.getContentPane().getHeight());
			}
		});
		ClickHandler ch = new ClickHandler(MTGComp);
		KeyBoardHandler kb = new KeyBoardHandler(MTGComp);
		Frame.addKeyListener(kb);
		Frame.addMouseListener(ch);
		return Frame;
	}
}
