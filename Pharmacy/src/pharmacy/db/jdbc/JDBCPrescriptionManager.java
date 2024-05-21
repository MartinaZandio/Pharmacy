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
	public void createPrescription() {
		Prescription p= new Prescription();
		try {
			String template = "INSERT INTO prescriptions (id, quantity, issueDate, dateUsed) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, p.getId());
			pstmt.setInt(2, p.getQuantity());
			pstmt.setDate(3, p.getIssueDate());
			pstmt.setDate(4, p.getDateUsed());
			pstmt.executeUpdate();
			pstmt.close();
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
