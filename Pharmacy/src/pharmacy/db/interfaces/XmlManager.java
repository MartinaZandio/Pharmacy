package pharmacy.db.interfaces;

import java.io.File;
import java.sql.SQLException;

import pharmacy.db.pojos.Pharmacy;

public interface XmlManager {
	
	public Pharmacy xml2Pharmacy(File xml);
	public void pharmacy2Html(Pharmacy pharmacy) throws Exception;
	public void printPharmacies() throws SQLException;
}