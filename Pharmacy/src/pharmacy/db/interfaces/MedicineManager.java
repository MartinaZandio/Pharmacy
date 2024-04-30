package pharmacy.db.interfaces;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import pharmacy.db.pojos.*;

public interface MedicineManager {
	
	
	public void addMedicine(Medicine medicine);
	public ArrayList<Medicine> searchMedicineByName(String name);
	public Medicine getMedicine(int id);
	
	
	/*
	 * public Medicine getmedicine(int id) { try{ String sql=
	 * "SELECT * FROM patients WHERE id = " + id; Statement st= c.createStatement();
	 * ResultSet rs= st.executeQuery(sql); rs.next(); Medicine m= new Medicine();
	 * return m; } catch(SQLException e) {
	 * System.out.println("Error in the database"); e.printStackTrace(); } }
	 */
	
	
	public void assignMedicine();
		
	
	
}
