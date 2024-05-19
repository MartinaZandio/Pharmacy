package pharmacy.db.interfaces;

import java.io.File;
import java.sql.SQLException;

import pharmacy.db.pojos.Pharmacy;

public interface XmlManager {
	
	public Pharmacy xml2Pharmacy(File xml);
	public void printPharmacies() throws SQLException;
}