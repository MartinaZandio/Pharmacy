package pharmacy.db.interfaces;

import java.util.ArrayList;
import java.util.List;

import pharmacy.db.pojos.*;


public interface PharmacyManager {

	public void markPrescriptionAsUsed(int prescription_id);
	public void orderStock(int medicine_id, int pharmacy_id, int qty);
	public List<Patient> identifyPatient(String name);
	public boolean checkAuthenticity(int prescription_id);
	public Pharmacy getPharmacy(int id);
	List<Pharmacy> getPharmacy(String name);
	public void sellMedicine(int patient_id, int pharmacy_id);
	public List<Stock> getStockPharmacy(int pharmacyId);
}
