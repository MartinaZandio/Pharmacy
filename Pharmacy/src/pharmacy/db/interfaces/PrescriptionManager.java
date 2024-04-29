package pharmacy.db.interfaces;
import pharmacy.db.pojos.*;

public interface PrescriptionManager {
	
	public void createPrescription(Patient patient);
	public void modifyPrescription(Prescription prescription);
	public void assignMedicine(Medicine medicine, Prescription prescription);

}
