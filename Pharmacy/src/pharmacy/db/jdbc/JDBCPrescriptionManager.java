package pharmacy.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pharmacy.db.interfaces.PrescriptionManager;
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

	


}
