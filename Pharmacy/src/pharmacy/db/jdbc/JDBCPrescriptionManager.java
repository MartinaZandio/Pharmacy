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
			String template = "INSERT INTO prescription (id, quantity, issueDate, dateUsed) VALUES (?, ?, ?, ?)";
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
	public ArrayList<Prescription> getPrescription(int id){  //SE USA
		ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
		try {
			String sql = "SELECT * FROM prescriptions WHERE id = ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id1 = rs.getInt("id");
				Integer medicineQuantity = rs.getInt("medicineQuantity");
				Date issueDate = rs.getDate("issueDate");
				Date useDate = rs.getDate("useDate");
				Prescription p = new Prescription(id, medicineQuantity, issueDate, useDate);
				prescriptions.add(p);
				}
			rs.close();
			search.close();
			return prescriptions;
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
		return null;
	}
}
