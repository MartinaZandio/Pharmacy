package pharmacy.db.jdbc;

import java.sql.Connection;
import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.pojos.*;

public class JDBCPharmacyManager implements PharmacyManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPharmacyManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

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
