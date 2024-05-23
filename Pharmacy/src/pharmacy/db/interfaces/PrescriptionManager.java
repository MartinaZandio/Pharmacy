package pharmacy.db.interfaces;

import java.sql.Date;
import java.util.*;

import pharmacy.db.pojos.*;

public interface PrescriptionManager {
	
	public Prescription createPrescription(int quantity, Date issueDate, int patient_id);
	public List<Prescription> getPrescription(int id);
	public void pres_medInsert(int prescription_id,int medicine_id);
	public int getLastPresId ();
	
}
