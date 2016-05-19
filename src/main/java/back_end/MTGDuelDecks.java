package back_end;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import database.SQLDatabaseConnection;
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
	public static final String IMPERIOUS_PERFECT_PATH = "res/Imperious_Perfect.jpg";
	public static final String ELF_WARRIOR_TOKEN_PATH = "res/Elf_Warrior_Token.jpg";

	/**
	 * Main method that runs the game and sets up the GUI frame.
	 * @param args Username, Password, Deck1, Deck2
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException{
		Backend bkd = Backend.getInstance();
		String connectionString =
				"jdbc:sqlserver://titan.csse.rose-hulman.edu;"
						+ "database=!MTGO;"
						+ "user=malinocr;"
						+ "password=#blue1baby1;"
						+ "encrypt=true;"
						+ "trustServerCertificate=true;"
						+ "hostNameInCertificate=*.database.windows.net;"
						+ "loginTimeout=30;";

		// Declare the JDBC objects.
		final Connection conn = DriverManager.getConnection(connectionString);


		setUpJFrame(bkd);

		initPlayerDB(conn, true, args[0], args[1], args[2]);
		//		initPlayerDB(conn, false, args[0], args[1], args[3]);
		//		initializePlayer();
		//		initializePlayer1();
	}

	private static void initPlayerDB(Connection conn, Boolean player, String user, String pass, String deck) throws SQLException{
		String[] cardString = SQLDatabaseConnection.getCardsinDeck(conn, user, pass, deck);
		HashMap<String, Integer> cardmap = new HashMap<String, Integer>();

		for(String s: cardString){
			String[] tmp = s.split(": ");
			cardmap.put(tmp[0], Integer.parseInt(tmp[1]));
		}

		Card[] cards = DeckFactory.getInstance().generateDeck(cardmap);

		Zone lib = Zone.getZoneFromString("LIBRARY", player);
		for(int i = 0; i < cards.length; i++){
			lib.addCard(cards[i], i);
		}

		lib.shuffle();

		Backend.getInstance().draw(player, 7);

	}

	/**
	 * Initializes the game data for the first player
	 *
	 */
	private static void initializePlayer() {
		//Test
		Zone.GRAVEYARD.addCard(new Card("Forest", "", "", "Basic Land- Forest", "T:G",
				new ArrayList<String>(), 0, 0, FOREST_PATH, false), 0);
		Zone.LIBRARY.addCard(new Card("Forest", "", "", "Basic Land- Forest", "T:G",
				new ArrayList<String>(), 0, 0, FOREST_PATH, false), 0);

		Card hand1 = new Card("Forest", "", "", "Basic Land- Forest", "T:G",
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);

		hand1.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");
		hand1.addAbility("TYPE {MANA} COST {TAP} EFFECT {G} ZONE {BATTLE_FIELD} TEXT {Add G to your mana pool}");

		Zone.HAND.addCard(hand1,0);

		Card hand2 = new Card("Forest", "", "", "Basic Land- Forest", "T:G",
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);
		hand2.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");
		hand2.addAbility("TYPE {MANA} COST {TAP} EFFECT {G} ZONE {BATTLE_FIELD} TEXT {Add G to your mana pool}");

		Zone.HAND.addCard(hand2, 0);

		Card hand3 = new Card("Arbor Elf", "G", "G", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 1, 1, LLANOWAR_ELVES_PATH, false);
		hand3.addAbility("TYPE {CAST} COST {G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}");
		hand3.addAbility("TYPE {MANA} COST {TAP} EFFECT {G} ZONE {BATTLE_FIELD} TEXT {Add G to your mana pool}");



		Zone.HAND.addCard(hand3, 0);

		Card hand4 = new Card("Imperious Perfect", "2G", "G", "Creature- Elf Warrior", null,
				new ArrayList<String>(), 2, 2, IMPERIOUS_PERFECT_PATH, false);
		hand4.addAbility("TYPE {CAST} COST {2G} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Cast}");
		hand4.addAbility("TYPE {ACTIVATED} COST {G,TAP} EFFECT {ELF_TOKEN} ZONE {BATTLE_FIELD} TEXT {Put a 1/1 green Elf Warrior token into play}");


		Zone.HAND.addCard(hand4, 0);


		Card BF1 = new Card("Forest", "", "", "Creature- Elf Druid", "T:G",
				new ArrayList<String>(), 0, 0, FOREST_PATH, false);

		BF1.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");
		BF1.addAbility("TYPE {MANA} COST {TAP} EFFECT {G} ZONE {BATTLE_FIELD} TEXT {Add G to your mana pool}");
		Zone.BATTLE_FIELD.addCard(BF1, 0);



	}


	/**
	 * Initializes the game data for the second player
	 *
	 */
	private static void initializePlayer1() {
		//Test
		Zone.GRAVEYARD1.addCard(new Card("Mountain", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false), 0);
		Zone.LIBRARY1.addCard(new Card("Mountain", "", "", "Basic Land- Mountain", "",
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false), 0);


		Card hand1 = new Card("Mountain", "", "", "Basic Land- Mountain", "T:R",
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false);
		hand1.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");
		hand1.addAbility("TYPE {MANA} COST {TAP} EFFECT {R} ZONE {BATTLE_FIELD} TEXT {Add R to your mana pool}");

		Card hand2 = new Card("Tarfire", "R", "R", "Instant- Goblin", "",
				new ArrayList<String>(), 0, 0, TARFIRE_PATH, true);
		hand2.addAbility("TYPE {CAST} COST {R} TARGET {CREATURE} EFFECT {DAMAGE} ZONE {HAND} RESOLVE {GRAVEYARD} TEXT {Deal 2 damage to target creature}");

		Zone.HAND1.addCard(hand1, 0);
		Zone.HAND1.addCard(hand2, 0);

		Card BF2 = new Card("Mountain", "", "", "Basic Land- Mountain", "T:R",
				new ArrayList<String>(), 0, 0, MOUNTAIN_PATH, false);
		BF2.addAbility("TYPE {PLAY} ZONE {HAND} RESOLVE {BATTLE_FIELD} TEXT {Play}");
		BF2.addAbility("TYPE {MANA} COST {TAP} EFFECT {R} ZONE {BATTLE_FIELD} TEXT {Add R to your mana pool}");
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
