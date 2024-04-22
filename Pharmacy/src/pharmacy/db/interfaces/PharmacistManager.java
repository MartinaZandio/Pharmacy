package pharmacy.db.interfaces;

import java.util.List;

import pharmacy.db.pojos.Medicine;
import pharmacy.db.pojos.Patient;
import pharmacy.db.pojos.Prescription;

public interface PharmacistManager {

	public void addPatient(Patient p);
	public List<Patient> searchPatientByName(String name);
	public List<Patient> searchPatientById(int id);
	public void deletePatient(int patientId);
	
	
	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient);
	public void markPrescriptionAsUsed(Prescription prescription);
	public void checkAuthenticity(Prescription prescription);
	public void checkStock(Medicine medicine);
	public void orderStock(Medicine medicine);
	public void identifyPatient(Patient patient);
	
	
	
}
