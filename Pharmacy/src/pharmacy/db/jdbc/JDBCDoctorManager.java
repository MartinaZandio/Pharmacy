package pharmacy.db.jdbc;

import java.sql.Connection;
import pharmacy.db.interfaces.DoctorManager;
import pharmacy.db.pojos.*;

public class JDBCDoctorManager implements DoctorManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCDoctorManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

	@Override
	public void createPrescription(Patient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyPrescrption(Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void identifyPatient(Patient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignMedicine(Medicine medicine, Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePrescription(Prescription prescrption) {
		// TODO Auto-generated method stub

	}

}
