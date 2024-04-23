package pharmacy.db.interfaces;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pharmacy.db.pojos.*;

public interface MedicineManager {
	
	
	public void addMedicine(Medicine medicine);
	public void searchMedicine( );
	
	
	/*
	 * public Medicine getmedicine(int id) { try{ String sql=
	 * "SELECT * FROM patients WHERE id = " + id; Statement st= c.createStatement();
	 * ResultSet rs= st.executeQuery(sql); rs.next(); Medicine m= new Medicine();
	 * return m; } catch(SQLException e) {
	 * System.out.println("Error in the database"); e.printStackTrace(); } }
	 */
	
	
	public void assignMedicine();
		
	
	
}
