package pharmacy.db.jdbc;
import java.sql.*;
import java.util.*;

import pharmacy.db.interfaces.PatientProfile;
import pharmacy.db.pojos.Patient;

public class JDBCPatientManager implements PatientProfile {
	
	private Connection c;
	
	public JDBCBookManager(Connection c) {
		this.c=c;
	}
	
	@Override
	public void addPatient(Patient p) {
		// TODO Auto-generated method stub
		try {
		String query= "INSERT INTO patient (id, name, dateOfBirth, sex)"
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement insert= c.prepareStatement(query);		
		insert.setInt(1,p.getId());
		insert.setString(2, p.getName());
		insert.setDate(3, p.getDateOfBirth());
		insert.setInt(4, p.getsex());

		insert.executeUpdate();	
		insert.close();
		}catch(SQLException sqlE) {
			System.out.println("Error in query");
			sqlE.printStackTrace();
		}
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		// TODO Auto-generated method stub
		return null;
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
				Gender s= rs.getString("Gender"); //¿Como ponemos la enumeracion aqui?
				Patient new Patient = new Patient(identity,nme,DOB,s);
			}
			return patients;
		}catch(SQLException e){
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		
		return patients;
	}

	@Override
	public void deletePatient(Patient p) {
		// TODO Auto-generated method stub

	}

}
