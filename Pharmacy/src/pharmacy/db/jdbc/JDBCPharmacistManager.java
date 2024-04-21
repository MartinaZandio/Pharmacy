package pharmacy.db.jdbc;

import java.sql.Connection;
import java.util.List;

import pharmacy.db.interfaces.Pharmacist;
import pharmacy.db.pojos.Patient;

public class JDBCPharmacistManager implements Pharmacist {

private Connection c;
	
	public JDBCBookManager(Connection c) {
		this.c=c;
	}
	
	@Override
	public void addPatient(Patient p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchPatientById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatient(int patientId) {
		// TODO Auto-generated method stub

	}

}
