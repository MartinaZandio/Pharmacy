package pharmacy.db.jdbc;

import java.io.*;
import java.sql.*;
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
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose a medicine by typing its ID: " );
			// Show medicines's ID method 
			int dep_id = Integer.parseInt(reader.readLine());
			System.out.println("Choose a patient to assigng the medicine: ");
			// Show patients method
			String patient1 = reader.readLine();
			String sql = "UPDATE prescriptions WHERE ";
		} catch (Exception e) {
			System.out.println("Error modifying prescription.");
		}

	}

	@Override
	public boolean checkAuthenticity() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type the prescription id: ");
			// Show prescription 
			int prescription_id = Integer.parseInt(reader.readLine());
			String sql = "SELECT useDate FROM prescriptions WHERE id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, prescription_id);
			ResultSet rs = search.executeQuery();
			rs.next();
			Date useDate = rs.getDate("useDate");
			if (useDate != null) {
				return true;
			} else return false;
		} catch (SQLException | NumberFormatException | IOException e) {
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
	public void orderStock(Medicine medicine) {
		
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
			} catch (SQLException sqlE) {
			System.out.println("Error");
			sqlE.printStackTrace();
			}
		}

	}
