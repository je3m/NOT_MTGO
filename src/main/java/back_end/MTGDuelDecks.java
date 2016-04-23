package back_end;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
		Zone.GRAVEYARD.addCard(new Card("Grave"), 0);
		Zone.LIBRARY.addCard(new Card("Libr"), 0);

		Card hand1 = new Card("Hand1");

		hand1.addAbility("Play this card");
		hand1.addAbility("Tap: Add G to your mana pool");

		hand1.addManaAbility("T:G");
		hand1.setImage(FOREST_PATH);
		Zone.HAND.addCard(hand1,0);

		Card hand2 = new Card("Hand2");
		hand2.addAbility("Play this card");
		hand2.addAbility("Tap: Add G to your mana pool");

		hand2.addManaAbility("T:G");
		hand2.setImage(FOREST_PATH);
		Zone.HAND.addCard(hand2, 0);

		Card hand3 = new Card("Arbor elf");
		hand3.setColor("G");
		hand3.addAbility("Play this card");
		hand3.addAbility("Tap: Add G to your mana pool");

		hand3.addManaAbility("T:G");
		hand3.setImage(LLANOWAR_ELVES_PATH);
		hand3.setType("Creature- Elf Druid");
		hand3.setCost("G");
		hand3.setPT(1, 1);


		Zone.HAND.addCard(hand3, 0);
		Zone.HAND.addCard(new Card("Hand4"), 0);
		Zone.HAND.addCard(new Card("Hand5"), 0);
		Zone.HAND.addCard(new Card("Hand6"), 0);
		Zone.HAND.addCard(new Card("Hand7"), 0);
		Zone.HAND.addCard(new Card("Hand8"), 0);
		Zone.HAND.addCard(new Card("Hand9"), 0);




		Card BF1 = new Card("BF1");
		BF1.setImage(FOREST_PATH);
		BF1.addAbility("Play this Card");
		BF1.addAbility("Tap: Add G to your mana pool");
		BF1.addManaAbility("T:G");
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
		hand1.addAbility("Tap: Add R to your mana pool");
		hand1.setColor("");
		hand1.setType("Land");
		hand1.setFlash(false);
		hand1.addManaAbility("T:R");
		hand1.setImage(MOUNTAIN_PATH);

		Card hand2 = new Card("tarfire");
		hand2.addAbility("play this card");
		hand2.setCost("R");
		hand2.setColor("R");
		hand2.setFlash(true);
		hand2.setImage(TARFIRE_PATH);
		hand2.setType("Instant- Goblin");

		Zone.HAND1.addCard(hand1, 0);
		Zone.HAND1.addCard(hand2, 0);
		Zone.HAND1.addCard(new Card("Hand3"), 0);
		Zone.HAND1.addCard(new Card("Hand4"), 0);
		Zone.HAND1.addCard(new Card("Hand5"), 0);
		Zone.HAND1.addCard(new Card("Hand6"), 0);
		Zone.HAND1.addCard(new Card("Hand7"), 0);

		Card BF2 = new Card("BF2");
		BF2.setImage(MOUNTAIN_PATH);
		BF2.addAbility("Play this card");
		BF2.addAbility("Tap: Add R to your mana pool");
		BF2.addManaAbility("T:R");
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
