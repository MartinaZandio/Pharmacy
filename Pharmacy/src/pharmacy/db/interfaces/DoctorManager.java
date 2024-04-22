package pharmacy.db.interfaces;

import pharmacy.db.pojos.Medicine;
import pharmacy.db.pojos.Patient;
import pharmacy.db.pojos.Prescription;

public interface DoctorManager {
	
	public void createPrescription(Patient patient);
	public void modifyPrescrption(Prescription prescription);
	public void identifyPatient(Patient patient);
	public void assignMedicine(Medicine medicine, Prescription prescription);
	public void deletePrescription(Prescription prescrption);

}
