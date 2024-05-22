package pharmacy.db.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pharmacy.db.interfaces.*;
import pharmacy.db.jpa.JPAUserManager;

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
		this.insertTables();
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
					+ " dateUsed DATE, "
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
			String create7="CREATE TABLE pres_med ( "
					+ " prescription_id REFERENCES prescriptions(id),"
					+ " medicine_id REFERENCES medicines(numberAssigned))";
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
		
	
	
	private void insertTables() {
		try {
			

			/*Statement insertTablesPatients=c.createStatement();
			String sql = "INSERT INTO patients(id, name, dateOfBirth, sex, userName) "
					+ " VALUES (1, 'Blanca', 13-05-2004, 'Female', 'blancamaldo')";
			insertTablesPatients.executeUpdate(sql);
			sql = "INSERT INTO patients(id, name, dateOfBirth, sex, userName) "
					+ " VALUES (2, 'Martina', 19-10-2004, 'Female', 'martizandio')";
			insertTablesPatients.executeUpdate(sql);
			insertTablesPatients.close();*/


			Statement insertTablesLaboratories =c.createStatement();
			String sql ="INSERT INTO laboratories(id, name, location, postalCode) "
					+ " VALUES (1, 'Cinfa', 'Sevilla' , 14024)";
			insertTablesLaboratories.executeUpdate(sql);
			
			sql ="INSERT INTO laboratories(id, name, location, postalCode) "
					+ " VALUES (2, 'Mylan', 'Mallorca' , 56337)";
			insertTablesLaboratories.executeUpdate(sql);
			insertTablesLaboratories.close();
			
			
			Statement insertTablesMedicines =c.createStatement();
			sql = "INSERT INTO medicines(numberAssigned, name, laboratory_id) "
					+ " VALUES (1, 'Ibuprofen', 2)";
			insertTablesMedicines.executeUpdate(sql);
			
			//Statement insertTables4=c.createStatement();
			sql = "INSERT INTO medicines(numberAssigned, name, laboratory_id) "
					+ " VALUES (2, 'Paracetamol', 1)";
			insertTablesMedicines.executeUpdate(sql);
			insertTablesMedicines.close();

			
			
			Statement insertTablesPharmacies =c.createStatement();
			sql ="INSERT INTO pharmacies(id, name, location, postalCode, numberOfWorkers) "
					+ " VALUES (1, 'Zandios', 'Pamplona', 35489, 4)";
			insertTablesPharmacies.executeUpdate(sql);
	
			sql = "INSERT INTO pharmacies(id, name, location, postalCode, numberOfWorkers) "
					+ " VALUES (2, 'Mendez', 'Alicante', 40001, 5)";
			insertTablesPharmacies.executeUpdate(sql);
			insertTablesPharmacies.close();
			
			
			
			Statement insertTablesPrescriptions =c.createStatement();
			sql ="INSERT INTO prescriptions(id, quantity, issueDate, dateUsed, patient_id) "
					+ " VALUES (1, 1 , 12-04-2024, NULL , NULL)";
			insertTablesPrescriptions.executeUpdate(sql);
			

			sql = "INSERT INTO prescriptions(id, quantity, issueDate, dateUsed, patient_id)"
					+  "VALUES (2, 5 , 20-05-2024, NULL , NULL)";
			insertTablesPrescriptions.executeUpdate(sql);
			insertTablesPrescriptions.close();
			
			
			Statement insertTablesStock =c.createStatement();
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (1, 1, 2)";
			insertTablesStock.executeUpdate(sql);
			
			sql="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (2, 2, 1)";
			insertTablesStock.executeUpdate(sql);
			insertTablesStock.close();
			
			
			
			Statement insertTablesPresMed =c.createStatement();
			sql ="INSERT INTO pres_med(prescription_id, medicine_id)"
					+  "VALUES (1,1)";
			insertTablesPresMed.executeUpdate(sql);
			
			sql ="INSERT INTO pres_med(prescription_id, medicine_id)"
					+  "VALUES (2,1)";
			insertTablesPresMed.executeUpdate(sql);
			insertTablesPresMed.close();
			
			
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
