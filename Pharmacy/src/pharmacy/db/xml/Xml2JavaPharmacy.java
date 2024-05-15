package pharmacy.db.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pharmacy.db.pojos.Pharmacy;
import pharmacy.db.pojos.Stock;

public class Xml2JavaPharmacy {
	public static final String PERSISTENCE_PROVIDER ="company-provider";
	private static 

	
	public static void main(String[] args) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		File file = new File ("./xmls/External-Report.xml");
		Pharmacy pharmacy= (Pharmacy) unmarshaller.unmarshal(file);
		
		System.out.println("Pharmacy:");
		System.out.println("Name:"+ Pharmacy.getName());
		System.out.println("Location:" +Pharmacy.getLocation());
		System.out.println("PostalCode:" + Pharmacy.getPostalCode());
		List<Stock> stock = Pharmacy.getStock();
		
		
	}
}
