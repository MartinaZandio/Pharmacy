package pharmacy.db.interfaces;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import pharmacy.db.pojos.*;


public interface MedicineManager {
		
	public void addMedicine(Medicine medicine);
	public List<Medicine> searchMedicineByName(String name);
	public int getMedicine(int med_id, int pharmacy_id);
	public List<Prescription> getPrescription(int patient_id);
	public String getMedicines(int prescription_id);
	public List<Medicine> getMedicinesPharmacy(int pharmacy_id);
	public List<Medicine> searchMedicineById(int medId);
	public List<Stock> getStockMedicine(int medicineId);

		
}

