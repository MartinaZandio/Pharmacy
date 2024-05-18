package pharmacy.db.jdbc;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
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
	public Pharmacy getPharmacy(int id) {
		
		try {
			String template= "SELECT * FROM pharmacies WHERE numAssigned = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(template);
			rs.next();
			Pharmacy p = new Pharmacy (rs.getInt("id"), rs.getString("name"),rs.getString("location"), rs.getInt("postalCode"), rs.getInt("numberOfWorkers")); 
			return p;
			}catch (SQLException e) {
				System.out.println("Error in the database");
				e.printStackTrace();
			}
			return null;
	}
	
	@Override 
	public void markPrescriptionAsUsed (int prescription_id) {
		Date localDate = null;
		try{
			String sql = "UPDATE prescription SET useDate=localDate WHERE precription_id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, localDate);
			prep.setInt(2, prescription_id);
			prep.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error modifying prescription.");
			}
		}

	@Override
	public boolean checkAuthenticity(int prescription_id) {
		try {
			String sql = "SELECT useDate FROM prescriptions WHERE id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, prescription_id);
			ResultSet rs = search.executeQuery();
			rs.next();
			Date useDate = rs.getDate("useDate");
			if (useDate != null) {
				return true;
			} else return false;
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
			}
		return false;
	}	
		
	public void reduceStock(int medicine_id, int pharmacy_id, int qty) {	
		try{
			String sql = "UPDATE stock SET amount=amount-? WHERE medicine_id=? AND pharmacy_id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, qty);
			prep.setInt(2, medicine_id);
			prep.setInt(3, pharmacy_id);
			prep.executeUpdate();
			System.out.println("Update finished.");
		}catch(Exception e){
			System.out.println("Error reducing the stock");
		}
	}

	@Override
	public void orderStock(int medicine_id, int pharmacy_id, int qty) {
		try{
			String sql = "UPDATE stock SET amount=amount+? WHERE medicine_id=? AND pharmacy_id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, qty);
			prep.setInt(2, medicine_id);
			prep.setInt(3, pharmacy_id);
			prep.executeUpdate();
			System.out.println("Update finished.");
		}catch(Exception e){
			System.out.println("Error ordering the stock");
		}
	}

	@Override
	public List<Patient> identifyPatient(String name) {
		ArrayList<Patient> patients = new ArrayList<Patient>();
	try {
			String template = "SELECT * FROM patients WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(template);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name1 = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String sex = rs.getString("gender");
				String userName = rs.getString("userName");
				Patient p = new Patient(id, name1, dateOfBirth, sex, userName);
				patients.add(p);
				}
			rs.close();
			search.close();
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
	
			return patients;
		}

	@Override
	public void assignMedicine(Medicine medicine, Prescription prescription) {
		try { 
			String sql;
			sql = "INSERT INTO prescriptionMedicine (medicine_numAsigned, prescription_id)"
					+ "VALUES (?,?);";
			
			PreparedStatement insert= c.prepareStatement(sql);
			insert.setInt(1, medicine.getNumAsigned());
			insert.setInt(2, prescription.getId());
			
			insert.executeUpdate();
			insert.close();
			} catch (SQLException sqlE) {
			System.out.println("Error");
			sqlE.printStackTrace();
			}
		}

	

	
	
}
