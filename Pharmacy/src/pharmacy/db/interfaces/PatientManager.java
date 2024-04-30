package pharmacy.db.interfaces;
import pharmacy.db.pojos.*;

import java.sql.SQLException;
import java.util.List;


public interface PatientManager {

	public void addPatient(Patient p);
	public List<Patient> searchPatientByName(String name);
	public List<Patient> searchPatientById(int id);
	public void deletePatient(Patient p) throws SQLException;
	public Patient getPatient(int id);
	public void identifyPatient(Patient p);
	
}