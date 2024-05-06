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
			String template= "INSERT INTO medicine (id, name, prescription_id, laboratory_id)"
					+ "VALUES (?,?,?,?);";
			PreparedStatement insert= c.prepareStatement(template);		
			insert.setInt(1,medicine.getNumAsigned());
			insert.setString(2, medicine.getName());
			insert.setInt(3, medicine.getPrescription().getId());
			insert.setInt(4, medicine.getLaboratory().getId());
			insert.executeUpdate();	
			insert.close();
			}catch(SQLException sqlE) {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}

	}
	
	
	public ArrayList<Medicine> searchMedicineByName(String name){
		ArrayList<Medicine> medicines = new ArrayList<Medicine>();
		try {
			String sql = "SELECT * FROM medicines WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" +name+ "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer numAsigned = rs.getInt("numAsigned");
				String medicineName = rs.getString("name");
		//		Array stock = rs.getArray("stock");
//				Prescription prescription = conMan.getPrescriptionMan().getPrescription().getId;
//				Laboratory laboratory = conMan.getLaboratoryMan().getLaboratory().getId;
				
			}
		}catch(SQLException e) {
			System.out.println("Error looking for a medicine");
			e.printStackTrace();
		}
		return medicines;
	}

	
	@Override
	public Medicine getMedicine(int id) {
		try {
			String sql = "SELECT * FROM medicines WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Medicine m = new Medicine (rs.getString("name"), rs.getInt("numAsigned")); //rs.getPrescription()); //laboratory stock prescriptions);
			return m;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	
	}
	
	@Override
	public void assignMedicine() {
		// TODO Auto-generated method stub
		
	}

}
