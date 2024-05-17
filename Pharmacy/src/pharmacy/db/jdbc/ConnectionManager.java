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
	private LaboratoryManager laboratoryMan;
	private PharmacyManager pharmacyMan;
	

	public Connection getConnection() {
		return c;
	}		
	
	public ConnectionManager() {
		this.Connect();
		this.patientMan = new JDBCPatientManager(this);
		this.medicineMan = new JDBCMedicineManager(this);
		this.prescriptionMan = new JDBCPrescriptionManager(this);
		this.laboratoryMan = new JDBCLaboratoryManager(this);
		this.pharmacyMan = new JDBCPharmacyManager(this);
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
		
	public void close() {
		try {
			c.close();
		}catch(SQLException e) {
			System.out.println("Error closing the database");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		try {
			//Create the tables			
			Statement createTables1=c.createStatement();
			String create1="CREATE TABLE patients ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " dateOfBirth DATE NOT NULL,"
					+ " sex TEXT NOT NULL,"
					+ " userName TEXT NOT NULL)";
			createTables1.executeUpdate(create1);
			createTables1.close();	
			Statement createTables2=c.createStatement();
			String create2="CREATE TABLE medicines ( "
					+ " numberAssigned INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " name TEXT NOT NULL,"
					+ " laboratory_id REFERENCES laboratories(id))";
			createTables2.executeUpdate(create2);
			createTables2.close();
			Statement createTables3=c.createStatement();
			String create3="CREATE TABLE laboratories ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL, "
					+ " location TEXT NOT NULL,"
					+ " postalCode INTEGER NOT NULL)";
			createTables3.executeUpdate(create3);
			createTables3.close();
			Statement createTables4=c.createStatement();
			String create4="CREATE TABLE pharmacies ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL, "
					+ " location TEXT NOT NULL, "
					+ " postalCode INTEGER NOT NULL, "
					+ " numberOfWorkers INTEGER NOT NULL )";
			createTables4.executeUpdate(create4);
			createTables4.close();
			Statement createTables5=c.createStatement();
			String create5="CREATE TABLE prescriptions ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " quantity INTEGER NOT NULL,"
					+ " issueDate DATE NOT NULL, "
					+ " dateUsed DATE NOT NULL, "
					+ " patient_id REFERENCES patients(id))";
			createTables5.executeUpdate(create5);
			createTables5.close();
			
			
			Statement createTables6=c.createStatement();
			String create6="CREATE TABLE stock ( "
					+ " pharmacy_id REFERENCES pharmacies(id),"
					+ " medicine_id REFERENCES medicines(numberAssigned), " 
					+ " amount INTEGER NOT NULL)";
			createTables6.executeUpdate(create6);
			createTables6.close();
			
			Statement createTables7=c.createStatement();
			String create7="CREATE TABLE medicines ( "
					+ " prescription_id REFERENCES prescriptions(id),"
					+ " medicine_id REFERENCES medicines(id))";
			createTables7.executeUpdate(create7);
			createTables7.close();			
			
			
			
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

	public LaboratoryManager getLaboratoryMan() {
		return laboratoryMan;
	}
	
	public PharmacyManager getPharmacyMan() {
		return pharmacyMan;
	}
	
}
