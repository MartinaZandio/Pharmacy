package pharmacy.db.interfaces;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import pharmacy.db.pojos.*;


public interface MedicineManager {
		
	public void addMedicine(Medicine medicine);
	public List<Medicine> searchMedicineByName(String name);
	public int getMedicine(int id);
	//List<Medicine> getMedicines(int patient_id);
	//public String getMedicines(int patient_id);
	List<Prescription> getPrescription(int patient_id);
	public List<String> getMedicines(int prescription_id);

		
}

