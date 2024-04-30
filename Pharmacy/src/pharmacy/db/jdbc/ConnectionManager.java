package pharmacy.db.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pharmacy.db.interfaces.*;

public class ConnectionManager {

	private Connection c;
	private PatientManager patientMan;
	private MedicineManager medicineMan;
	private PrescriptionManager prescriptionMan;
	

	public Connection getConnection() {
		return c;
	}
	
	public ConnectionManager() {
		this.Connect();
		this.patientMan = new JDBCPatientManager(this);
		this.medicineMan = new JDBCMedicineManager(this);
		this.prescriptionMan = new JDBCPrescriptionManager(this);
		this.createTables();
	}

	private void Connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:./db/pharmacy.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		}catch(ClassNotFoundException cnfE) {
			System.out.println("Databases pharmacy not loaded");
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
					+ " sex TEXT NOT NULL)";
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

	public PatientManager getPatientMan() {
		return patientMan;
	}


	public MedicineManager getMedicineMan() {
		return medicineMan;
	}

	public PrescriptionManager getPrescriptionMan() {
		return prescriptionMan;
	}
	
	
		
	
}
