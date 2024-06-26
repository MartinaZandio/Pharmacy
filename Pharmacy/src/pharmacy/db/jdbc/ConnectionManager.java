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
					+ " VALUES (1, 'Ibuprofen 200gr', 2)";
			insertTablesMedicines.executeUpdate(sql);
			
			sql = "INSERT INTO medicines(numberAssigned, name, laboratory_id) "
					+ " VALUES (2, 'Paracetamol 500mg', 1)";
			insertTablesMedicines.executeUpdate(sql);
			
			sql = "INSERT INTO medicines(numberAssigned, name, laboratory_id) "
					+ " VALUES (3, 'Ibuprofen 400gr', 1)";
			insertTablesMedicines.executeUpdate(sql);
			
			sql = "INSERT INTO medicines(numberAssigned, name, laboratory_id) "
					+ " VALUES (4, 'Paracetamol 100mg', 1)";
			insertTablesMedicines.executeUpdate(sql);
			insertTablesMedicines.close();

			
			Statement insertTablesPharmacies =c.createStatement();
			sql ="INSERT INTO pharmacies(id, name, location, postalCode, numberOfWorkers) "
					+ " VALUES (1, 'Zandios', 'Pamplona', 35489, 4)";
			insertTablesPharmacies.executeUpdate(sql);
	
			sql = "INSERT INTO pharmacies(id, name, location, postalCode, numberOfWorkers) "
					+ " VALUES (2, 'Mendez', 'Alicante', 03540, 5)";
			insertTablesPharmacies.executeUpdate(sql);
			
			sql = "INSERT INTO pharmacies(id, name, location, postalCode, numberOfWorkers) "
					+ " VALUES (3, 'Zandios&Co', 'Madrid', 28009, 3)";
			insertTablesPharmacies.executeUpdate(sql);
			insertTablesPharmacies.close();			
			
			
			Statement insertTablesStock =c.createStatement();
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (1, 1, 4)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (1, 2, 6)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (1, 3, 15)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (1, 4, 6)";
			insertTablesStock.executeUpdate(sql);
			
			sql="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (2, 1, 3)";
			insertTablesStock.executeUpdate(sql);
			
			sql="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (2, 2, 8)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (2, 3, 2)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (2, 4, 3)";
			insertTablesStock.executeUpdate(sql);
			
			sql="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (3, 1, 6)";
			insertTablesStock.executeUpdate(sql);
			
			sql="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (3, 2, 3)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (3, 3, 8)";
			insertTablesStock.executeUpdate(sql);
			
			sql ="INSERT INTO stock(pharmacy_id, medicine_id, amount) "
					+ " VALUES (3, 4, 5)";
			insertTablesStock.executeUpdate(sql);
			insertTablesStock.close();
			
		}catch(SQLException sqlE) {
			if(sqlE.getMessage().contains("UNIQUE constraint failed")){
				System.out.println("");
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
