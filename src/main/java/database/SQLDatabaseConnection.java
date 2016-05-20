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
				Messages.getString("SQLDatabaseConnection.0") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.1") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.2") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.3") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.4") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.5") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.6") //$NON-NLS-1$
						+ Messages.getString("SQLDatabaseConnection.7"); //$NON-NLS-1$

		// Declare the JDBC objects.
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(connectionString);
			DatabaseMetaData meta = connection.getMetaData();
			Statement st = connection.createStatement();
			String query = Messages.getString("SQLDatabaseConnection.8"); //$NON-NLS-1$
			if(query.toLowerCase().contains(Messages.getString("SQLDatabaseConnection.9")) || query.toLowerCase().contains(Messages.getString("SQLDatabaseConnection.10"))){ //$NON-NLS-1$ //$NON-NLS-2$
				query = Messages.getString("SQLDatabaseConnection.11"); //$NON-NLS-1$
			}
			ResultSet rs = st.executeQuery(query);
			for(int i = 1; i < (rs.getMetaData().getColumnCount() + 1); i++) {
				System.out.print(rs.getMetaData().getColumnName(i) + Messages.getString("SQLDatabaseConnection.12")); //$NON-NLS-1$
				if(i != rs.getMetaData().getColumnCount()){
					System.out.print(Messages.getString("SQLDatabaseConnection.13")); //$NON-NLS-1$
				}
			}
			System.out.println();
			while (rs.next()) {
				for(int i = 1; i < (rs.getMetaData().getColumnCount() + 1); i++) {
					System.out.print(rs.getObject(i).toString() + Messages.getString("SQLDatabaseConnection.14")); //$NON-NLS-1$
					if(i != rs.getMetaData().getColumnCount()){
						System.out.print(Messages.getString("SQLDatabaseConnection.15")); //$NON-NLS-1$
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