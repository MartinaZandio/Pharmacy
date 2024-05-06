package pharmacy.db.interfaces;

import java.util.List;

import pharmacy.db.pojos.*;


public interface PharmacyManager {

	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient);
	public void markPrescriptionAsUsed(Prescription prescription);
	public boolean checkAuthenticity();
	public void checkStock(Medicine medicine);
	public void orderStock(Medicine medicine);
	public void identifyPatient(Patient patient);
	public void assignMedicine(Medicine medicine, Prescription prescription);

}
