package pharmacy.db.jdbc;

import java.sql.Connection;
import pharmacy.db.interfaces.*;

public class JDBCLaboratoryManager implements LaboratoryManager {
	
	private ConnectionManager conMan;
	private Connection c;

	public JDBCLaboratoryManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

}
