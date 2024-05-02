package pharmacy.db.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.pojos.*;
import pharmacy.db.pojos.Patient.gender;
import sample.db.pojos.Department;


public class JDBCPharmacyManager implements PharmacyManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPharmacyManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

	@Override
	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void markPrescriptionAsUsed(Prescription prescription) {
		// TODO Auto-generated method stub
		try {
			// Change a department's location: beginning
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose a prescription by typing its ID: ");
			// Show all id's method??
			int presc_id = Integer.parseInt(reader.readLine());
			
			// useDate = CurrentDate
			LocalDate useDate; 
		
			
		
			System.out.println("Update finished.");

		} catch (Exception e) {
			System.out.println("Error modifying prescription.");
		}
	}

	@Override
	public void checkAuthenticity(Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkStock(String name) {
		

	}

	@Override
	public void orderStock(Medicine medicine) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
	
=======

		
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	}

	@Override
	public void identifyPatient(Patient patient) {
		// TODO Auto-generated method stub
		try {
			String template = "SELECT * FROM patients WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(template);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				gender sex = rs.getSex("sex");
				}
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
		}

	@Override
	public void assignMedicine(Medicine medicine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkStock(Medicine medicine) {
		// TODO Auto-generated method stub
		
	}


}
