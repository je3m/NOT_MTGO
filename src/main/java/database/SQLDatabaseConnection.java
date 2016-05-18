package database;
// Use the JDBC driver
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLDatabaseConnection {

	public static void main(String[] args) {
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
	
	public static String[] getCardsinDeck(Connection connection, String username, String password, String deck) throws SQLException{
		String query = "select CardName from [!MTGO].dbo.getDeckContents(?,?,?)";
		PreparedStatement prepquery = connection.prepareStatement(query);
		
		prepquery.setString(1, username);
		prepquery.setString(2, password);
		prepquery.setString(3, deck);
		
		ResultSet rs = prepquery.executeQuery();
		
		ArrayList<String> ar = new ArrayList<String>();
		while(rs.next()){
			ar.add(rs.getString(1));
		}
		
		return ar.toArray(new String[0]);
	}
}