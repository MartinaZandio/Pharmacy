package pharmacy.db.interfaces;

import pharmacy.db.pojos.*;

public interface PrescriptionManager {
	
	public void createPrescription();
	public Prescription getPrescription(int id);

	public boolean medicineIsUsed(Medicine medicine);
}
