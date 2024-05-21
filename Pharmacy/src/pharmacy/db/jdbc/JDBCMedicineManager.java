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
	public Medicine getMedicine(int id) {  //SE USA
		try {
			String sql = "SELECT * FROM medicines WHERE id = ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, id);
			ResultSet rs = search.executeQuery();
			while (rs.next()) {
				Medicine m = new Medicine (rs.getString("name"), rs.getInt("numberAssigned"), rs.getInt("quantity")); 
				return m;
			}
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	
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
	
	public List<String> getMedicines(int prescription_id) {
		List<String> medicines= new ArrayList<String>();
		try {
			String sql= "SELECT name FROM medicines INNER JOIN pres_med ON medicines.numberAssigned = pres_med.medicine_id WHERE prescription_id = ?";
			PreparedStatement search=c.prepareStatement(sql);
			search.setInt(1, prescription_id);
			ResultSet rs = search.executeQuery();
			String nombreMed = rs.getString("name");
			medicines.add(nombreMed);
			return medicines;
		}catch(SQLException e) {
			System.out.println("Error looking for a prescription");
			e.printStackTrace();
		}
		return null;
	}
	
	/* Split in 2 methods; 1. Get a list of prec for a patient id. 2. get a list of medicines for a presc id
	@Override
	public String getMedicines(int patient_id){   //SE USA
		List<Medicine> medicines = new ArrayList<Medicine>();
		try {
			String sql = "SELECT id FROM prescriptions WHERE patient_id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, patient_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer idPres = rs.getInt("id");
				String sql2 = "SELECT * FROM pres_med WHERE prescription_id LIKE ?";
				PreparedStatement search2 = c.prepareStatement(sql2);
				search2.setInt(1, idPres);
				ResultSet rs2 = search2.executeQuery();
				
				while (rs2.next()) {
					Integer med_id = rs2.getInt("medicine_id");
					
					String sql3 = "SELECT name FROM medicines WHERE numberAssigned LIKE ?";
					PreparedStatement search3 = c.prepareStatement(sql3);
					search3.setInt(1, med_id);
					ResultSet rs3 = search3.executeQuery();
					
					String nombreMed = rs3.getString("name");
					return nombreMed;
				
				}
			}
		} catch(SQLException e) {
			System.out.println("Error looking for a medicine");
			e.printStackTrace();
		} 
		return null;
	}
	*/


	

}
