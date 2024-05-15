package pharmacy.db.xml;

import java.io.*;
import java.util.List;

import javax.persistence.*;

import pharmacy.db.pojos.*;

public class Java2XmlPharmacy {

	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static void printPharmacies() {
		Query q1= em.createNativeQuery("SELECT * FROM pharmacies", Pharmacy.class);
		List<Pharmacy> pharmacies = (List<Pharmacy>) q1.getResultList();
		
		for(Pharmacy ph: pharmacies) {
			System.out.println(ph);
		}
	}
	
}
