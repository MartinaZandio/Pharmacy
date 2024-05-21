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
		ArrayList<Medicine> medicines = new ArrayList<Medicine>();
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
	
	// TODO revisar
	@Override
	public ArrayList<Medicine> getMedicines(int patient_id){   //SE USA
		ArrayList<Medicine> medicines = new ArrayList<Medicine>();
		try {
			String sql = "SELECT id FROM prescriptions WHERE patient_id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, patient_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				
				String sql2 = "SELECT * FROM pres_med WHERE id LIKE ?";
				PreparedStatement search2 = c.prepareStatement(sql2);
				search.setInt(1, id);
				ResultSet rs2 = search2.executeQuery();
				
				while (rs2.next()) {
					Integer med_id = rs2.getInt("medicine_id");
					
					String sql3 = "SELECT name FROM medicines WHERE numberAssigned LIKE ?";
					PreparedStatement search3 = c.prepareStatement(sql3);
					search.setInt(1, med_id);
					ResultSet rs3 = search3.executeQuery();

				rs.close();
				rs2.close();
				rs3.close();
				search.close();
				search2.close();
				search3.close();
				}
			}
		}catch(SQLException e) {
			System.out.println("Error looking for a medicine");
			e.printStackTrace();
		}
		return medicines;
	}


	

}
