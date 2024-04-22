package pharmacy.db.interfaces;

import java.util.List;

import pharmacy.db.pojos.Patient;

public interface PharmacistManager {

	public void addPatient(Patient p);
	public List<Patient> searchPatientByName(String name);
	public List<Patient> searchPatientById(int id);
	public void deletePatient(int patientId);
	
	
}
