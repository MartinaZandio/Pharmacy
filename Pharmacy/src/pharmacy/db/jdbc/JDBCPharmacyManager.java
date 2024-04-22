package pharmacy.db.jdbc;

import pharmacy.db.interfaces.PharmacistManager;
import pharmacy.db.pojos.Medicine;
import pharmacy.db.pojos.Patient;
import pharmacy.db.pojos.Prescription;

public class JDBCPharmacyManager implements PharmacistManager {

	@Override
	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void markPrescriptionAsUsed(Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkAuthenticity(Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkStock(Medicine medicine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void orderStock(Medicine medicine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void identifyPatient(Patient patient) {
		// TODO Auto-generated method stub

	}

}
