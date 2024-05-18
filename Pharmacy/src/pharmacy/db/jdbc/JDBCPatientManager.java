package pharmacy.db.jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.interfaces.PatientManager;
import pharmacy.db.pojos.*;


public class JDBCPatientManager implements PatientManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

	@Override
	public void addPatient(Patient p) {
		try {
		String template= "INSERT INTO patient (id, name, dateOfBirth, sex, userName)"
				+ "VALUES (?,?,?,?, ?);";
		PreparedStatement insert= c.prepareStatement(template);		
		insert.setInt(1,p.getId());
		insert.setString(2, p.getName());
		insert.setDate(3, p.getDateOfBirth());
		insert.setString(4, p.getSex());
		insert.setString (5, p.getUserName());
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
			String sql= "SELECT * FROM patient WHERE id = ?"; //this string is a template
			PreparedStatement search=c.prepareStatement(sql);
			search.setInt(1, id); //Fills the question marks
			ResultSet rs= search.executeQuery();
			while(rs.next()) {
				Integer identity= rs.getInt("id");
				String nme= rs.getString("name");
				Date DOB= rs.getDate("dateOfBirth");
				String sex= rs.getString("Sex"); 
				String userName= rs.getString("userName");
				Patient Patient = new Patient(identity,nme,DOB,sex, userName);
			}
			return patients;
		}catch(SQLException e){
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		
		return patients;
	}
	
	@Override
	public void deletePatient(Patient p) throws SQLException {
		String template = "DELETE FROM patient WHERE id = ?";
		PreparedStatement delete= c.prepareStatement(template);
		delete.setInt(1,p.getId());
		delete.executeUpdate();
	}
	
	public Patient getPatient(int id) {
		try {
			String sql = "SELECT * FROM patient WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient a = new Patient (rs.getInt("id"), rs.getString("name"), rs.getDate("date of birth"), rs.getString("Sex"), rs.getString("userName"));
			return a;
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void identifyPatient(int patientId) {
		try {
			String sql= "SELECT * FROM patient WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, p.getId());
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String sex = rs.getString("sex");
				String userName = rs.getString("userName");
				Patient patient = new Patient(id, name, dateOfBirth, sex, userName);
				System.out.println(patient);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		// TODO Auto-generated method stub
		/*List<Patient> patients= new ArrayList<Patient>();
		try {
			
			String sql= "SELECT * FROM patient WHERE id = ?"; 
			PreparedStatement search=c.prepareStatement(sql);
			search.setInt(1, id); //Fills the question marks
			ResultSet rs= search.executeQuery();
			while(rs.next()) {
				Integer identity= rs.getInt("id");
				String nme= rs.getString("name");
				Date DOB= rs.getDate("dateOfBirth");
				String sex= rs.getString("Sex"); 
				Patient Patient = new Patient(identity,nme,DOB,sex);
			}
			return patients;
		}catch(SQLException e){
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		
		return patients;*/
		return null;
	}

	@Override
	public Patient getPatient(String username) {
		try {
			String sql = "SELECT * FROM patient WHERE username = " + username;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient a = new Patient (rs.getInt("id"), rs.getString("name"), rs.getDate("date of birth"), rs.getString("Sex"), rs.getString("userName"));
			return a;
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

}

