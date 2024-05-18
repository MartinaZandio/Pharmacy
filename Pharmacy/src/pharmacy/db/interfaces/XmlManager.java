package pharmacy.db.interfaces;

import java.io.File;

import pharmacy.db.pojos.Pharmacy;

public interface XmlManager {

	File pharmacy2Xml(Pharmacy pharmacy);
	Pharmacy xml2Pharmacy(File xml);
	void pharmacy2Html(Pharmacy pharmacy);

}
