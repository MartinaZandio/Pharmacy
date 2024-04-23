package pharmacy.db.interfaces;

import pharmacy.db.pojos.*;

public interface DoctorManager {
	
	public void createPrescription(Patient patient);
	public void modifyPrescrption(Prescription prescription);
	public void identifyPatient(Patient patient);
	public void assignMedicine(Medicine medicine, Prescription prescription);
	public void deletePrescription(Prescription prescrption);

}
