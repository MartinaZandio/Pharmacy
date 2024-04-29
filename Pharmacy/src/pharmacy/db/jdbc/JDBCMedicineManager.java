package pharmacy.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pharmacy.db.interfaces.MedicineManager;
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
		// TODO Auto-generated method stub
		try {
			String template= "INSERT INTO medicine (id, name, prescription_id, laboratory_id)"
					+ "VALUES (?,?,?,?);";
			PreparedStatement insert= c.prepareStatement(template);		
			insert.setInt(1,medicine.getNumAsigned());
			insert.setString(2, medicine.getName());
			insert.setDate(3, medicine.getPrescription().getId());
			insert.setString(4, medicine.getLaboratory().getId());
			insert.executeUpdate();	
			insert.close();
			}catch(SQLException sqlE) {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}

	}

	@Override
	public void searchMedicine() {
		
	}

	@Override
	public Medicine getMedicine(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
