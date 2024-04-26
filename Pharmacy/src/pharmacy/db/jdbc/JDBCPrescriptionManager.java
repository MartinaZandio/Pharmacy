package pharmacy.db.jdbc;

import java.sql.Connection;

import pharmacy.db.interfaces.PrescriptionManager;

public class JDBCPrescriptionManager extends PrescriptionManager {

	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPrescriptionManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
}
