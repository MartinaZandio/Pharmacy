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
			String template= "SELECT * FROM pharmacies WHERE id = " + id;
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
			String sql = "UPDATE prescriptions SET dateUsed = localDate WHERE id = ?";
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
			String sql = "SELECT dateUsed FROM prescriptions WHERE id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, prescription_id);
			ResultSet rs = search.executeQuery();
			rs.next();
			Date useDate = rs.getDate("dateUsed");
			if (useDate != null) {
				return true;
			} else return false;
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
			}
		return false;
	}	
		

	public void sellMedicine(int patient_id, int pharmacy_id) {	
		try{
			String sql = "SELECT * FROM prescriptions WHERE patient_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, patient_id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer quantity = rs.getInt("quantity");
				
				String sql2 = "SELECT medicine_id FROM pres_med WHERE prescription_id LIKE ?";
				PreparedStatement prep2 = c.prepareStatement(sql2);
				prep.setInt(1, id);
				ResultSet rs2 = prep2.executeQuery();
				
				while (rs2.next()) {
					Integer medicine_id = rs.getInt("medicine_id");
					
					String sql3 = "SELECT amount FROM stock WHERE medicine_id LIKE ?";
					PreparedStatement prep3 = c.prepareStatement(sql2);
					prep.setInt(1, medicine_id);
					ResultSet rs3 = prep3.executeQuery(); 
					
					while (rs3.next()) {
						Integer amount = rs3.getInt("amount");
					
						try {
						String sql4 = "UPDATE stock SET amount=amount-? WHERE medicine_id=? AND pharmacy_id=?";
						PreparedStatement prep4 = c.prepareStatement(sql4);
						prep4.setInt(1, quantity);
						prep4.setInt(2, medicine_id);
						prep4.setInt(3, pharmacy_id);
						prep4.executeUpdate();
						prep4.close();
						System.out.println("Update finished.");
						} catch(Exception e){
							System.out.println("Error reducing the stock");
						} 
					
					rs.close();
					rs2.close();
					rs3.close();
					prep.close();
					prep2.close();
					prep3.close();
					}

				}
			}
			prep.executeUpdate();
			System.out.println("Update finished.");
		}catch(Exception e){
			System.out.println("Error reducing the stock");
		}
	}

	@Override
	public void orderStock(int medicine_id, int pharmacy_id, int qty) { 
		try{
			String sql = "UPDATE stock SET amount = amount+? WHERE medicine_id=? AND pharmacy_id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, qty);
			prep.setInt(2, medicine_id);
			prep.setInt(3, pharmacy_id);
			prep.executeUpdate();
			prep.close();
			System.out.println("Update finished.");
		}catch(Exception e){
			System.out.println("Error ordering the stock");
		}
	}

	@Override
	public List<Patient> identifyPatient(String name) { 
		List<Patient> patients = new ArrayList<Patient>();
	try {
			String template = "SELECT * FROM patients WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(template);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name1 = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String userName = rs.getString("userName");
				Patient p = new Patient(id, name1, dateOfBirth, userName);
				patients.add(p);
				return patients;
				}
			rs.close();
			search.close();
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
			return null;
		}
	
	@Override
	public List<Pharmacy> getPharmacy(String name) {  

		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		try {
			String sql = "SELECT * FROM pharmacies WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql); 
			prep.setString(1, "%"+name+"%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id1 = rs.getInt("id");
				String name1 = rs.getString("name");
				String location = rs.getString("location");
				Integer postalCode = rs.getInt("postalCode");
				Pharmacy phs = new Pharmacy(id1, name1, location, postalCode);
				pharmacies.add(phs);
				}
			rs.close();
			prep.close();
			return pharmacies;
			} catch (SQLException e) {
				System.out.println("Error creating the pharmacy");
				e.printStackTrace();
				}
		return null;
	}
	

	
	@Override
	public List<Stock> getStockPharmacy(int pharmacyId) {
		List<Stock> stocks = new ArrayList<Stock>();
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM stock WHERE pharmacy_id LIKE " + pharmacyId;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int amount = rs.getInt("amount");
				Stock s = new Stock (amount);
				stocks.add(s);
	
			}
			rs.close();
			stmt.close();
			return stocks;
		} catch (SQLException e){
			System.out.println("Error selecting the stock.");
			e.printStackTrace();
		}
		return null;
	}

}
