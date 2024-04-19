package pharmacy.db.jdbc;
import java.sql.*;
import java.util.List;

import pharmacy.db.interfaces.PatientProfile;
import pharmacy.db.pojos.Patient;

public class JDBCPatientManager implements PatientProfile {
	
	private Connection c;
	
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
		
	private void createtables() {
		try {
			//Create the tables
			Statement createTables1=c.createStatement();
			String create1="CREATE TABLE patient ( "
					+ " id INTEGER PRIMARY KEY,"
					+ " name TEXT NOT NULL,"
					+ " dateOfBirth DATE NOT NULL,"
					+ " sex INTEGER REFERENCES gender(id)"; //NO SE SI ESTÁ BIEN
			createTables1.executeUpdate(create1);
			createTables1.close();			
		}catch(SQLException sqlE) {
			System.out.println("Error in query");
			sqlE.printStackTrace();
		}
	}
		
	
	@Override
	public void addPatient(Patient p) {
		// TODO Auto-generated method stub
		try {
		String query= "INSERT INTO patient (id, name, dateOfBirth, sex)"
				+ "VALUES (?,?,?,?);";
		PreparedStatement insert= c.prepareStatement(query);		
		insert.setInt(1,p.getId());
		insert.setString(2, p.getName());
		insert.setDate(3, p.getDateOfBirth());
		insert.executeUpdate();	
		insert.close();
		}catch(SQLException sqlE) {
			System.out.println("Error in query");
			sqlE.printStackTrace();
		}
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchPatientById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatient(Patient p) {
		// TODO Auto-generated method stub

	}

}
