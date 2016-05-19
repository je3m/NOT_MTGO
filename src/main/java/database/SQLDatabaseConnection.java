package database;
// Use the JDBC driver
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLDatabaseConnection {

	public static void main(String[] args) throws SQLException {
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
		final Connection connection = DriverManager.getConnection(connectionString);

		String[] strs = getCardsinDeck(connection, "jim", "swag", "elves");

		for(String s: strs)
			System.out.println(s);
	}

	public static String[] getCardList(Connection connection) throws SQLException{
		Statement st = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String query = "SELECT Name from Card Order by name ASC";

		ResultSet rs = st.executeQuery(query);

		rs.last();
		int size = rs.getRow();
		rs.beforeFirst();
		String[] ret = new String[size];

		int index = 0;
		while(rs.next()){
			ret[index] = rs.getString(1);
			index++;
		}

		return ret;
	}

	public static boolean isRegistered(Connection connection, String username, String password) throws SQLException{
		String query = "Select [!MTGO].dbo.isValidLogin (?, ?)";
		PreparedStatement prepquery = connection.prepareStatement(query);
		prepquery.setString(1, username);
		prepquery.setString(2, password);
		ResultSet rs = prepquery.executeQuery();
		rs.next();
		if(rs.getInt(1) == 0){
			return false;
		} else {
			return true;
		}
	}

	public static boolean register(Connection connection, String username, String password) throws SQLException{
		String query = "EXEC [!MTGO].dbo.registerUser @name=?, @password=?";
		PreparedStatement prepquery = connection.prepareStatement(query);

		prepquery.setString(1, username);
		prepquery.setString(2, password);

		try{
			prepquery.execute();
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public static String[] getDeckNames(Connection connection, String username, String password) throws SQLException{
		String query = "select * from [!MTGO].dbo.getPlayerDecks (?,?)";
		PreparedStatement prepquery = connection.prepareStatement(query);

		prepquery.setString(1, username);
		prepquery.setString(2, password);

		ResultSet rs = prepquery.executeQuery();

		ArrayList<String> ar = new ArrayList<String>();
		while(rs.next()){
			ar.add(rs.getString(1));
		}

		return ar.toArray(new String[0]);
	}
	
	public static String[] getCollectionNames(Connection connection, String username, String password) throws SQLException{
		String query = "select * from [!MTGO].dbo.getPlayerCollections (?,?)";
		PreparedStatement prepquery = connection.prepareStatement(query);
		
		prepquery.setString(1, username);
		prepquery.setString(2, password);
		
		ResultSet rs = prepquery.executeQuery();
		
		ArrayList<String> ar = new ArrayList<String>();
		while(rs.next()){
			ar.add(rs.getString(1));
		}
		
		return ar.toArray(new String[0]);
	}
	
	public static String[] getCardsinDeck(Connection connection, String username, String password, String deck) throws SQLException{
		String query = "select * from [!MTGO].dbo.getDeckContents(?,?,?)";
		PreparedStatement prepquery = connection.prepareStatement(query);

		prepquery.setString(1, username);
		prepquery.setString(2, password);
		prepquery.setString(3, deck);

		ResultSet rs = prepquery.executeQuery();

		ArrayList<String> ar = new ArrayList<String>();
		while(rs.next()){
			ar.add(rs.getString(1) + ": " + rs.getInt(2));
		}

		return ar.toArray(new String[0]);
	}
	
	public static String[] getCardsinCollection(Connection connection, String username, String password, String collection) throws SQLException{
		String query = "select * from [!MTGO].dbo.getCollection(?,?,?)";
		PreparedStatement prepquery = connection.prepareStatement(query);
		
		prepquery.setString(1, username);
		prepquery.setString(2, password);
		prepquery.setString(3, collection);
		
		ResultSet rs = prepquery.executeQuery();
		
		ArrayList<String> ar = new ArrayList<String>();
		while(rs.next()){
			ar.add(rs.getString(1) + ": " + rs.getInt(2));
		}
		
		return ar.toArray(new String[0]);
	}
	
	public static boolean addNewDeck(Connection connection, String username, String password, String deckName){
		try{
			String query = "EXEC [!MTGO].dbo.createDeck @player=?,@password=?,@deckName=?";
			PreparedStatement prepquery = connection.prepareStatement(query);
			
			prepquery.setString(1, username);
			prepquery.setString(2, password);
			prepquery.setString(3, deckName);
			
			prepquery.execute();
			
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public static boolean addNewCollection(Connection connection, String username, String password, String deckName){
		try{
			String query = "EXEC [!MTGO].dbo.createCollection @player=?,@password=?,@collectionName=?";
			PreparedStatement prepquery = connection.prepareStatement(query);
			
			prepquery.setString(1, username);
			prepquery.setString(2, password);
			prepquery.setString(3, deckName);
			
			prepquery.execute();
			
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	public static boolean addCardtoDeck(Connection connection, String username, String password, String deckName, String cardName, String amount){
		try{
			String query = "EXEC [!MTGO].dbo.setCardInDeck @player=?,@psswd=?,@deckName=?,@cardName=?,@quantity=?";
			PreparedStatement prepquery = connection.prepareStatement(query);
			
			prepquery.setString(1, username);
			prepquery.setString(2, password);
			prepquery.setString(3, deckName);
			prepquery.setString(4, cardName);
			prepquery.setString(5, amount);
			
			prepquery.execute();
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addCardtoCollection(Connection connection, String username, String password, String colName, String cardName, String amount){
		try{
			String query = "EXEC [!MTGO].dbo.setCardInCollection @player=?,@psswd=?,@collectionName=?,@cardName=?,@quantity=?";
			PreparedStatement prepquery = connection.prepareStatement(query);
			
			prepquery.setString(1, username);
			prepquery.setString(2, password);
			prepquery.setString(3, colName);
			prepquery.setString(4, cardName);
			prepquery.setString(5, amount);
			
			prepquery.execute();
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}