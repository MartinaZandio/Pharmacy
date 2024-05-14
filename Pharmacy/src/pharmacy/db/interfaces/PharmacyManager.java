package pharmacy.db.interfaces;

import java.util.List;

import pharmacy.db.pojos.*;


public interface PharmacyManager {

	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient);
	public void markPrescriptionAsUsed(int prescription_id);
	public void orderStock(int medicine_id, int pharmacy_id, int qty);
	public List<Patient> identifyPatient(String name);
	public void assignMedicine(Medicine medicine, Prescription prescription);
	public boolean checkAuthenticity(int prescription_id);
	
}
