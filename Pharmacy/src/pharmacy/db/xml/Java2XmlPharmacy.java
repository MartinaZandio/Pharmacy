package pharmacy.db.xml;

import java.beans.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.pojos.*;

public class Java2XmlPharmacy {

	
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		
		 // Pharmacy pharmacy you got from somewhere;
		
		File file = new File("./xmls/Sample-Pharmacy.xml");
		marshaller.marshal(pharmacy,file);
		marshaller.marshal(pharmacy, System.out);
		
	}
	
