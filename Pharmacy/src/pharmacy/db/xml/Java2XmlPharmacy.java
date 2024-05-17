package pharmacy.db.xml;

import java.beans.Statement;
import java.io.*;
import java.sql.*;
import java.util.List;

import javax.persistence.*;
<<<<<<< HEAD
import javax.xml.bind.*;
=======
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.pojos.*;

public class Java2XmlPharmacy {

	
<<<<<<< HEAD
	private static Connection c; 
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private ConnectionManager conMan; 
	
	public Java2XmlPharmacy(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	
	private static void printPharmacies() {
	
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
			Medicine medicine= new Medicine(rs.getString("name"), rs.getInt("numAssigned")); 
			Stock stock= new Stock(rs.getInt("amount"));
			Pharmacy ph= new Pharmacy(id, name, location, postalCode, numberOfWorkers,???????);
		}
		
	}
	
	public static void main (String[]args ) throws Exception{
		String url = "jdbc:sqlite:pharmacy-provider.db";
		try {
			Connection c = DriverManager.getConnection(url);
			c.setAutoCommit(false);
			java.sql.Statement stmt=c.createStatement();
			stmt.executeUpdate("PRAGMA foreign_keys=ON");
		   c.commit();
			c.close();
		}catch (SQLException e) {
		}

	
=======
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		
		 // Pharmacy pharmacy you got from somewhere;
		
		File file = new File("./xmls/Sample-Pharmacy.xml");
		marshaller.marshal(pharmacy,file);
		marshaller.marshal(pharmacy, System.out);
		
	}
	
