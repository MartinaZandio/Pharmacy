package pharmacy.db.xml;

import java.io.*;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.internal.oxm.Marshaller;

import pharmacy.db.interfaces.XmlManager;
import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.pojos.*;

public class XmlPharmacyManager implements XmlManager {
	
	private static Connection c;
	
	@Override 
	public File pharmacy2Xml(Pharmacy pharmacy) {
	
	try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Marshaller marshaller = JAXBContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File ("./xmls/External-Pharmacy"); 
		marshaller.marshal(pharmacy, file);
		return file;
		
		}catch(Exception e) {
		e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public Pharmacy xml2Pharmacy(File xml) {
		
	try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Pharmacy pharmacy = (Pharmacy)unmarshaller.unmarshal(xml);
		return pharmacy;
	
	}catch(Exception e) {
		System.out.println("Unable to load the xml file");
		e.printStackTrace();
	}
	return null;
	}

		
	@Override
	public void pharmacy2Html(Pharmacy pharmacy) {
		
		File file = pharmacy2Xml(pharmacy);
		TransformerFactory tf= TransformerFactory.newInstance();
		try {
			Transformer t= tf.newTransformer(new StreamSource(new File("./xmls/External-Pharamcy")));
			t.transform(new StreamSource(file), new StreamResult(new File("./xmls/External-Pharamcy")));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printPharmacies() throws SQLException {
		
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
