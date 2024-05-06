package pharmacy.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.pojos.*;

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
		
		

	}

	@Override
	public void checkStock(String name) {
		

	}

	@Override
	public void orderStock(Medicine medicine) {

		
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
	public void assignMedicine(Medicine medicine, Prescription prescription) {
		// TODO Auto-generated method stub
		try { //open data base connection
			Statement stmt=c.createStatement();
			String sql;
			sql = "INSERT INTO prescriptionMedicine (medicine_numAsigned, prescription_id)"
					+ "VALUES (?,?);";
			
			PreparedStatement insert= c.prepareStatement(sql);
			insert.setInt(1, medicine.getNumAsigned());
			insert.setInt(2, prescription.getId());
			
			insert.executeUpdate();
			insert.close();
		
	}
		catch (SQLException sqlE) {
			System.out.println("Error");
			sqlE.printStackTrace();
			
	}



	}
	}
