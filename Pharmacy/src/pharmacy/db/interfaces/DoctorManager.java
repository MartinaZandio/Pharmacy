package pharmacy.db.interfaces;
import pharmacy.db.pojos.*;

public interface DoctorManager {

	void createPrescription(Patient patient);

	void modifyPrescrption(Prescription prescription);

	void identifyPatient(Patient patient);

	void assignMedicine(Medicine medicine, Prescription prescription);

	void deletePrescription(Prescription prescrption);
	
}
