package back_end;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class DeckFactory {
	private static DeckFactory fac;
	private String connectionString =
			"jdbc:sqlserver://titan.csse.rose-hulman.edu;"
					+ "database=!MTGO;"
					+ "user=malinocr;"
					+ "password=#blue1baby1;"
					+ "encrypt=true;"
					+ "trustServerCertificate=true;"
					+ "hostNameInCertificate=*.database.windows.net;"
					+ "loginTimeout=30;";

	// Declare the JDBC objects.
	private Connection connection = null;

	public DeckFactory() throws SQLException{
		this.connection = DriverManager.getConnection(this.connectionString);
		DatabaseMetaData meta = this.connection.getMetaData();
		Statement st = this.connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

	}

	public static DeckFactory getInstance(){
		if(fac == null){
			try {
				fac = new DeckFactory();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return fac;
	}

	/**
	 * gets a list of cards without abilities applied to them
	 * @param cards hashmap of cards to generate
	 * @return array of cards wihout abilities
	 * @throws SQLException
	 */
	private Card[] getCards(HashMap<String, Integer> cards) throws SQLException{
		String query = "SELECT * FROM getCardInfo(?)";


		PreparedStatement prepquery = this.connection.prepareStatement(query);
		Card[] ret = new Card[cards.size()];

		Iterator itr = cards.entrySet().iterator();

		int i = 0;
		while(itr.hasNext()){
			Entry entry = (Entry) itr.next();

			for(int j = 0; j < (int) entry.getValue(); j++){
				prepquery.setString(1, (String) entry.getKey());
				ResultSet rs = prepquery.executeQuery();
				rs.next();
				String name = rs.getString(1);
				String manaCost = rs.getString(2);
				String color = rs.getString(3);
				String type = rs.getString(4);
				int power = rs.getInt(5);
				int toughness = rs.getInt(6);
				String image = "res/" + rs.getString(7) + ".png";


				Card c = new Card(name, manaCost, color, type, null, new ArrayList<String>(), power, toughness, image, false);
				ret[i] = c;

			}

			i++;
		}
		return ret;
	}


	public void addAbilities(Card[] cards) throws SQLException{
		String query = "SELECT * FROM getCardAbilities(?)";
		PreparedStatement prepquery = this.connection.prepareStatement(query);

		for (int i = 0; i < cards.length; i++){
			prepquery.setString(1, cards[i].getName());
			ResultSet rs = prepquery.executeQuery();

			while(rs.next()){
				cards[i].addAbility(rs.getString(1));
			}
		}
	}

	public Card[] generateDeck(HashMap<String, Integer> cards) throws SQLException{
		Card[] card = this.getCards(cards);

		this.addAbilities(card);

		return card;

	}
}
