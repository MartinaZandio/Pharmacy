package pharmacy.db.jdbc;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import pharmacy.db.interfaces.PatientManager;
import pharmacy.db.pojos.Medicine;
import pharmacy.db.pojos.Patient;

public class JDBCPatientManager implements PatientManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	
	@Override
	public void addPatient(Patient p) {
		// TODO Auto-generated method stub
		try {
		String template= "INSERT INTO patient (id, name, dateOfBirth, sex)"
				+ "VALUES (?,?,?,?);";
		PreparedStatement insert= c.prepareStatement(template);		
		insert.setInt(1,p.getId());
		insert.setString(2, p.getName());
		insert.setDate(3, p.getDateOfBirth());
		insert.setInt(4, p.getSex());
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
				Patient.gender s= Patient.gender.valueOf(rs.getString("Gender")); //¿Como ponemos la enumeracion aqui?
				Patient Patient = new Patient(identity,nme,DOB,s);
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

	@Override
	public void takeMedicine(int patientId, int medicineId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Medicine> getTakenMedicines(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
