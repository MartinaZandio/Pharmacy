package pharmacy.db.jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import pharmacy.db.interfaces.*;
import pharmacy.db.pojos.*;


public class JDBCPrescriptionManager implements PrescriptionManager {

	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPrescriptionManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	
	@Override
	public Prescription createPrescription(int quantity, Date issueDate, int patient_id) {
		Prescription p = null;
		try {
			String template = "INSERT INTO prescriptions (quantity, issueDate, patient_id) VALUES (?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, quantity);
			pstmt.setDate(2, issueDate);
			pstmt.setInt(3, patient_id);
			pstmt.executeUpdate();
			pstmt.close();
			p = new Prescription (this.getLastPresId(), quantity, issueDate, null, patient_id);
			return p;
		} catch (SQLException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
		}
		return p;
	}
	
	@Override
	public int getLastPresId () {
		try {
			String query = "SELECT last_insert_rowid() AS id";
			PreparedStatement p = c.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			Integer lastId = rs.getInt("id");
			return lastId;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public void pres_medInsert(int prescription_id,int medicine_id) {
		Prescription p= new Prescription();
		try {
			String template = "INSERT INTO pres_med (prescription_id, medicine_id) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, prescription_id);
			pstmt.setInt(2, medicine_id);
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Prescription uploaded.");
		} catch (SQLException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
		}
	}
	
	@Override

	public List<Prescription> getPrescription(int patient_id){  //SE USA
		List<Prescription> prescriptions = new ArrayList<Prescription>();
		try {
			String sql = "SELECT * FROM prescriptions WHERE patient_id = ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, patient_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Integer medicineQuantity = rs.getInt("quantity");
				Date issueDate = rs.getDate("issueDate");
				Date dateUsed = rs.getDate("dateUsed");
				Prescription p = new Prescription(id, medicineQuantity, issueDate, dateUsed);
				prescriptions.add(p);
				}
			return prescriptions;
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
		return null;
		}
}
