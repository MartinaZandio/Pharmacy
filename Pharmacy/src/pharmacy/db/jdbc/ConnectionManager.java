package pharmacy.db.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	private Connection c;

	public Connection getConnection() {
		return c;
	}
	
	public ConnectionManager() {
		this.Connect();
		this.createTables();
	}

	private void Connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:./db/library.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		}catch(ClassNotFoundException cnfE) {
			System.out.println("Databases libraries not loaded");
			cnfE.printStackTrace();
		}catch(SQLException sqlE) {
			System.out.println("Error with database");
			sqlE.printStackTrace();
		}
	}	
		
	private void close() {
		try {
			c.close();
		}catch(SQLException e) {
			System.out.println("Error closing the database");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		//If the tables are already created, skip this step
		try {
			//Create the tables			
			Statement createTables1=c.createStatement();
			String create1="CREATE TABLE patient ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL,"
					+ " dateOfBirth DATE NOT NULL,"
					+ " sex TEXT NOT NULL)"; //NO SE SI ESTÁ BIEN
			createTables1.executeUpdate(create1);
			createTables1.close();	
		}catch(SQLException sqlE) {
			if(sqlE.getMessage().contains("already exist")){
				System.out.println("No need to create the tables; already there");
			}
			else {
			System.out.println("Error in query");
			sqlE.printStackTrace();
			}
		}
	}
		
	
}
