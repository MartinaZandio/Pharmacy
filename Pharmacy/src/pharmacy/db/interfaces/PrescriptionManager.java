package pharmacy.db.interfaces;

import java.util.*;

import pharmacy.db.pojos.*;

public interface PrescriptionManager {
	
	public void createPrescription();
	public List<Prescription> getPrescription(int id);

	
}
