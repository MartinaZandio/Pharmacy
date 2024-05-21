package pharmacy.db.jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.interfaces.PatientManager;
import pharmacy.db.pojos.*;
import pharmacy.ui.Menu;


public class JDBCPatientManager implements PatientManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

	@Override
	public void addPatient(Patient p) {    //SE USA 
		try {
		String template= "INSERT INTO patients (name, dateOfBirth, sex, userName)"
				+ "VALUES (?,?,?,?);";
		PreparedStatement insert = c.prepareStatement(template);		
		insert.setString(1, p.getName());
		insert.setDate(2, p.getDateOfBirth());
		insert.setString(3, p.getSex());
		insert.setString (4, p.getUserName());
		insert.executeUpdate();	
		insert.close();
		}catch(SQLException sqlE) {
			System.out.println("Error in query");
			sqlE.printStackTrace();
		}
	}

	@Override
	public List<Patient> searchPatientById(int id) {
		List<Patient> patients= new ArrayList<Patient>();
		try {
			String sql= "SELECT * FROM patients WHERE id = ?";
			PreparedStatement search=c.prepareStatement(sql);
			search.setInt(1, id); //Fills the question marks
			ResultSet rs= search.executeQuery();
			while(rs.next()) {
				Integer id2 = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String userName = rs.getString("userName");
				Patient patient = new Patient(id2, name, dateOfBirth, userName);
				patients.add(patient);
				return patients;
			}
			rs.close();
			search.close();
		}catch(SQLException e){
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override 
	public Patient identifyPatient(int patientId) {    //SE USA
    try {
			String sql = "SELECT * FROM patients WHERE id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, patientId);
			ResultSet rs = search.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String userName = rs.getString("userName");
				Patient patient = new Patient(id, name, dateOfBirth, userName);
				return patient;
			}
			rs.close();
			search.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	@Override
	public Patient getPatient(String username) {
		try {
			String sql = "SELECT * FROM patients WHERE userName = ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, username);
			ResultSet rs = search.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String userName = rs.getString("userName");
				Patient patient = new Patient(id, name, dateOfBirth, userName);
				return patient;
			}
			rs.close();
			search.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
}

