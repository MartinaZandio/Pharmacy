package pharmacy.db.interfaces;

import java.sql.SQLException;
import java.util.List;

import pharmacy.db.pojos.*;


public interface PatientManager {

	public void addPatient(Patient p);
	public List<Patient> searchPatientById(int id);
	public Patient identifyPatient(int patientId);
	public Patient getPatient(String username);
}