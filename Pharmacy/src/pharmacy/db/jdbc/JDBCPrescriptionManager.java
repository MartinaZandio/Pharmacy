package pharmacy.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
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
			pstmt.setString(3, p.getIssueDate());
			pstmt.setString(4, p.getDateUsed());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Prescription getPrescription(int id) {
		try {
			String sql = "SELECT * FROM prescriptions WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Prescription p = new Prescription (rs.getInt("id"), rs.getInt("quantity"), rs.getString("issueDate"), rs.getString("dateUsed"), rs.getPatient().getId(id));
			return p;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	


	


}
