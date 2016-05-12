package database;
// Use the JDBC driver
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLDatabaseConnection {

	// Connect to your database.
	// Replace server name, username, and password with your credentials
	public static void main(String[] args) {
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
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(connectionString);
			DatabaseMetaData meta = connection.getMetaData();
			Statement st = connection.createStatement();
			String query = "select* from player";
			if(query.toLowerCase().contains("drop") || query.toLowerCase().contains("alter")){
				query = "select * from player";
			}
			ResultSet rs = st.executeQuery(query);
			for(int i = 1; i < (rs.getMetaData().getColumnCount() + 1); i++) {
				System.out.print(rs.getMetaData().getColumnName(i) + " ");
				if(i != rs.getMetaData().getColumnCount()){
					System.out.print("| ");
				}
			}
			System.out.println();
			while (rs.next()) {
				for(int i = 1; i < (rs.getMetaData().getColumnCount() + 1); i++) {
					System.out.print(rs.getObject(i).toString() + " ");
					if(i != rs.getMetaData().getColumnCount()){
						System.out.print("| ");
					}
				}
				System.out.println();
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) try { connection.close(); } catch(Exception e) {}
		}
	}
}