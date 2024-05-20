package pharmacy.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.interfaces.XmlManager;
import pharmacy.db.pojos.*;

public class XmlPharmacyManager implements XmlManager {
	
	private static Connection c;
	private static BufferedReader r= new BufferedReader(new InputStreamReader(System.in));
	private static PharmacyManager pharmacyManager;

	public static void pharmacy2Xml() throws Exception {
		
		System.out.print("Choose a pharmacy to turn into an XML file:");
		int id = Integer.parseInt(r.readLine());
		Pharmacy p= pharmacyManager.getPharmacy(id);
		File xml = new File ("./xmls/External-Pharmacy"); 
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Marshaller marshaller= jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(p, xml);
		marshaller.marshal(p,System.out);
	}
	
	
	public static Pharmacy xml2Pharmacy() throws Exception{
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); 
		File xml = new File("./xmls/External-Pharmacy.xml");
		Pharmacy pharmacy = (Pharmacy)unmarshaller.unmarshal(xml);
		return pharmacy;
	}

	
	public void printPharmacies() throws SQLException {
		
		String sql = "SELECT * FROM pharmacies";
		PreparedStatement prep= c.prepareStatement(sql);
		ResultSet rs = prep.executeQuery();
		rs.next();
		while(rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String location=rs.getString("location");
			Integer postalCode = rs.getInt("postalCode");
			Integer numberOfWorkers=rs.getInt("numberOfWorkers");
			Stock stock= new Stock (rs.getInt("amount"));
			Pharmacy ph= new Pharmacy(id, name, location, postalCode, numberOfWorkers);
			System.out.println(ph);
			System.out.println(stock);
		}
		
		rs.close();
		prep.close();
		
	}
	
}
