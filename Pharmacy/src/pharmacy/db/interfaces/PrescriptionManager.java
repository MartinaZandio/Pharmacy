package pharmacy.db.interfaces;

import java.util.*;

import pharmacy.db.pojos.*;

public interface PrescriptionManager {
	
	public void createPrescription();
	public ArrayList<Prescription> getPrescription(int id);

	
}
