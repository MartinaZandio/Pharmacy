package pharmacy.db.jdbc;

import java.sql.Connection;

import pharmacy.db.interfaces.PrescriptionManager;
import pharmacy.db.pojos.*;

public class JDBCPrescriptionManager implements PrescriptionManager {

	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPrescriptionManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	
	@Override
	public void createPrescription(Patient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyPrescription(Prescription prescription) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void assignMedicine(Medicine medicine, Prescription prescription) {
		// TODO Auto-generated method stub

	}
}
