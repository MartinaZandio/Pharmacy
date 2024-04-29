package pharmacy.db.interfaces;
import pharmacy.db.pojos.*;
import java.util.List;


public interface PatientManager {

	public void takeMedicine(int patientId, int medicineId);
	public List<Medicine> getTakenMedicines(int patientId);
	void addPatient(Patient p);
	List<Patient> searchPatientByName(String name);
	List<Patient> searchPatientById(int id);
	void deletePatient(Patient p);
	void identifyPatient(Patient patient);
	
}