package pharmacy.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.db.pojos.Author;
import library.db.pojos.Book;
import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.pojos.*;
import pharmacy.db.pojos.Patient.gender;

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

	}

	@Override
	public void checkAuthenticity(Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkStock(Medicine medicine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderStock(Medicine medicine) {
		// TODO Auto-generated method stub

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
				gender gender = rs.getgender("gender");
				}
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
		}

	@Override
	public void assignMedicine(Medicine medicine) {
		// TODO Auto-generated method stub
		try {
			String template = "SELECT * FROM patients WHERE name LIKE ?";
			String template2= "SELECT * FROM medicine WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(template);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			
			PreparedStatement search = c.prepareStatement(template2);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				gender gender = rs.getgender("gender");
				}
			} catch (SQLException e) {
				System.out.println("Error assigning the medicine");
				e.printStackTrace();
				}
		}

	}


}
