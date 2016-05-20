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
	public static final String MOUNTAIN_PATH = Messages.getString("MTGDuelDecks.0"); //$NON-NLS-1$
	public static final String FOREST_PATH = Messages.getString("MTGDuelDecks.1"); //$NON-NLS-1$
	public static final String LLANOWAR_ELVES_PATH = Messages.getString("MTGDuelDecks.2"); //$NON-NLS-1$
	public static final String TARFIRE_PATH = Messages.getString("MTGDuelDecks.3"); //$NON-NLS-1$
	public static final String IMPERIOUS_PERFECT_PATH = Messages.getString("MTGDuelDecks.4"); //$NON-NLS-1$
	public static final String ELF_WARRIOR_TOKEN_PATH = Messages.getString("MTGDuelDecks.5"); //$NON-NLS-1$

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
		Zone.GRAVEYARD.addCard(new Card(Messages.getString("MTGDuelDecks.6"), Messages.getString("MTGDuelDecks.7"), Messages.getString("MTGDuelDecks.8"), Messages.getString("MTGDuelDecks.9"), Messages.getString("MTGDuelDecks.10"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, FOREST_PATH, false), 0);
		Zone.LIBRARY.addCard(new Card(Messages.getString("MTGDuelDecks.11"), Messages.getString("MTGDuelDecks.12"), Messages.getString("MTGDuelDecks.13"), Messages.getString("MTGDuelDecks.14"), Messages.getString("MTGDuelDecks.15"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, FOREST_PATH, false), 0);

		Card hand1 = new Card(Messages.getString("MTGDuelDecks.16"), Messages.getString("MTGDuelDecks.17"), Messages.getString("MTGDuelDecks.18"), Messages.getString("MTGDuelDecks.19"), Messages.getString("MTGDuelDecks.20"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);

		hand1.addAbility(Messages.getString("MTGDuelDecks.21")); //$NON-NLS-1$
		hand1.addAbility(Messages.getString("MTGDuelDecks.22")); //$NON-NLS-1$

		Zone.HAND.addCard(hand1,0);

		Card hand2 = new Card(Messages.getString("MTGDuelDecks.23"), Messages.getString("MTGDuelDecks.24"), Messages.getString("MTGDuelDecks.25"), Messages.getString("MTGDuelDecks.26"), Messages.getString("MTGDuelDecks.27"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);
		hand2.addAbility(Messages.getString("MTGDuelDecks.28")); //$NON-NLS-1$
		hand2.addAbility(Messages.getString("MTGDuelDecks.29")); //$NON-NLS-1$

		Zone.HAND.addCard(hand2, 0);

		Card hand3 = new Card(Messages.getString("MTGDuelDecks.30"), Messages.getString("MTGDuelDecks.31"), Messages.getString("MTGDuelDecks.32"), Messages.getString("MTGDuelDecks.33"), Messages.getString("MTGDuelDecks.34"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 1, 1, LLANOWAR_ELVES_PATH, false);
		hand3.addAbility(Messages.getString("MTGDuelDecks.35")); //$NON-NLS-1$
		hand3.addAbility(Messages.getString("MTGDuelDecks.36")); //$NON-NLS-1$



		Zone.HAND.addCard(hand3, 0);

		Card hand4 = new Card(Messages.getString("MTGDuelDecks.37"), Messages.getString("MTGDuelDecks.38"), Messages.getString("MTGDuelDecks.39"), Messages.getString("MTGDuelDecks.40"), null,  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ArrayList<String>(), 2, 2, IMPERIOUS_PERFECT_PATH, false);
		hand4.addAbility(Messages.getString("MTGDuelDecks.41")); //$NON-NLS-1$
		hand4.addAbility(Messages.getString("MTGDuelDecks.42")); //$NON-NLS-1$
		hand4.setColor("q");

		Zone.HAND.addCard(hand4, 0);


		Card BF1 = new Card(Messages.getString("MTGDuelDecks.43"), Messages.getString("MTGDuelDecks.44"), Messages.getString("MTGDuelDecks.45"), Messages.getString("MTGDuelDecks.46"), Messages.getString("MTGDuelDecks.47"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);

		BF1.addAbility(Messages.getString("MTGDuelDecks.48")); //$NON-NLS-1$
		BF1.addAbility(Messages.getString("MTGDuelDecks.49")); //$NON-NLS-1$
		Zone.BATTLE_FIELD.addCard(BF1, 0);



	}


	/**
	 * Initializes the game data for the second player
	 *
	 */
	private static void initializePlayer1() {
		//Test
		Zone.GRAVEYARD1.addCard(new Card(Messages.getString("MTGDuelDecks.50"), Messages.getString("MTGDuelDecks.51"), Messages.getString("MTGDuelDecks.52"), Messages.getString("MTGDuelDecks.53"), Messages.getString("MTGDuelDecks.54"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false), 0);
		Zone.LIBRARY1.addCard(new Card(Messages.getString("MTGDuelDecks.55"), Messages.getString("MTGDuelDecks.56"), Messages.getString("MTGDuelDecks.57"), Messages.getString("MTGDuelDecks.58"), Messages.getString("MTGDuelDecks.59"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false), 0);


		Card hand1 = new Card(Messages.getString("MTGDuelDecks.60"), Messages.getString("MTGDuelDecks.61"), Messages.getString("MTGDuelDecks.62"), Messages.getString("MTGDuelDecks.63"), Messages.getString("MTGDuelDecks.64"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false);
		hand1.addAbility(Messages.getString("MTGDuelDecks.65")); //$NON-NLS-1$
		hand1.addAbility(Messages.getString("MTGDuelDecks.66")); //$NON-NLS-1$

		Card hand2 = new Card(Messages.getString("MTGDuelDecks.67"), Messages.getString("MTGDuelDecks.68"), Messages.getString("MTGDuelDecks.69"), Messages.getString("MTGDuelDecks.70"), Messages.getString("MTGDuelDecks.71"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, TARFIRE_PATH, true);
		hand2.addAbility(Messages.getString("MTGDuelDecks.72")); //$NON-NLS-1$

		Zone.HAND1.addCard(hand1, 0);
		Zone.HAND1.addCard(hand2, 0);

		Card BF2 = new Card(Messages.getString("MTGDuelDecks.73"), Messages.getString("MTGDuelDecks.74"), Messages.getString("MTGDuelDecks.75"), Messages.getString("MTGDuelDecks.76"), Messages.getString("MTGDuelDecks.77"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false);
		BF2.addAbility(Messages.getString("MTGDuelDecks.78")); //$NON-NLS-1$
		BF2.addAbility(Messages.getString("MTGDuelDecks.79")); //$NON-NLS-1$
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
		Frame.setTitle(Messages.getString("MTGDuelDecks.80")); //$NON-NLS-1$
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		System.out.println(Frame.getContentPane().getWidth());
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
