package pharmacy.db.jdbc;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import pharmacy.db.interfaces.*;
import pharmacy.db.pojos.*;


public class JDBCMedicineManager implements MedicineManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCMedicineManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	
	@Override
	public void addMedicine(Medicine medicine) {
		try {
			String template= "INSERT INTO medicines (numberAssigned, name, laboratory_id)"
					+ "VALUES (?,?,?);";
			PreparedStatement insert= c.prepareStatement(template);		
			insert.setInt(1,medicine.getNumAsigned());
			insert.setString(2, medicine.getName());
			insert.setInt(3, medicine.getLaboratory().getId());
			insert.executeUpdate();	
			insert.close();
			}catch(SQLException sqlE) {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}

	}
	
	@Override
	public List<Medicine> searchMedicineByName(String name){   //SE USA
		List<Medicine> medicines = new ArrayList<Medicine>();

		try {
			String sql = "SELECT * FROM medicines WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" +name+ "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer numAsigned = rs.getInt("numberAssigned");
				String medicineName = rs.getString("name");
		
			Medicine medicine = new Medicine (medicineName, numAsigned);
			medicines.add(medicine);
			}
		}catch(SQLException e) {
			System.out.println("Error looking for a medicine");
			e.printStackTrace();
		}
		return medicines;
	}

	@Override
	public int getMedicine(int med_id, int pharmacy_id) {  //SE USA
		try {
			String sql = "SELECT * FROM stock WHERE medicine_id = ? AND pharmacy_id = ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, med_id);
			search.setInt(2, pharmacy_id);
			ResultSet rs = search.executeQuery();
			while (rs.next()) {
				Integer pharmacy_id2 = rs.getInt("pharmacy_id");
				Integer medicine_id = rs.getInt("medicine_id");
				Integer amount = rs.getInt("amount");
				return amount;
			}
			rs.close();
			search.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return (Integer) null;
	}
		
	@Override 
	public List<Prescription> getPrescription(int patient_id){
		List<Prescription> prescriptions= new ArrayList<Prescription>();
		try {
			String sql= "SELECT * FROM prescriptions WHERE patient_id LIKE ?";
			PreparedStatement search= c.prepareStatement(sql);
			search.setInt(1,patient_id);
			ResultSet rs= search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Integer quantity = rs.getInt("quantity");
				Date issueDate = rs.getDate("issueDate");
				Date dateUsed = rs.getDate("dateUsed");
				Integer patientId= rs.getInt("patient_id");

				Prescription prescription = new Prescription(id, quantity, issueDate, dateUsed, patientId);
				prescriptions.add(prescription);
				return prescriptions;
			}
		}catch(SQLException e) {
			System.out.println("Error looking for a prescription");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getMedicines(int prescription_id) {
		try {
			String sql= "SELECT name FROM medicines INNER JOIN pres_med ON medicines.numberAssigned = pres_med.medicine_id WHERE prescription_id = ?";
			PreparedStatement search =c.prepareStatement(sql);
			search.setInt(1, prescription_id);
			ResultSet rs = search.executeQuery();
			String nombreMed = rs.getString("name");
	
			return nombreMed;
		}catch(SQLException e) {
			System.out.println("Error looking for a prescription");
			e.printStackTrace();
		}
		return null;
	}

}
