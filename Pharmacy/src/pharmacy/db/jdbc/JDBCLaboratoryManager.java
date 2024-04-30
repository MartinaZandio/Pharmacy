package pharmacy.db.jdbc;

import java.sql.Connection;

public class JDBCLaboratoryManager {
	
	private ConnectionManager conMan;
	private Connection c;

	public JDBCLaboratoryManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

}
