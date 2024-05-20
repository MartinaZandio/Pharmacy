package pharmacy.db.interfaces;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import pharmacy.db.pojos.*;


public interface MedicineManager {
		
	public void addMedicine(Medicine medicine);
	public ArrayList<Medicine> searchMedicineByName(String name);
	public Medicine getMedicine(int id);
	ArrayList<Medicine> getMedicines(int patient_id);
		
}

