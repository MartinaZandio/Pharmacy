package pharmacy.db.xml;

import java.beans.Statement;
import java.io.*;
import java.sql.Connection;
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

	
	private static Connection c; 
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private ConnectionManager conMan; 
	
	public Java2XmlPharmacy(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}
	
	private static void printPharmacies() {
	
		String sql = "SELECT * FROM pharmacies";
		PreparedStatement prep= c.prepareStatement(sql); //pharmacy.class
		ResultSet rs = prep.executeQuery();
		rs.next();
		List<Pharmacy> pharmacies = (List<Pharmacy>) prep.getResultList();
		for(Pharmacy ph: pharmacies) {
			System.out.println(ph);
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

	
		JAXBContext jaxbContext = JAXBContext.newInstance(Pharmacy.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		printPharmacies();
		System.out.print("Choose a pharmacy to turn into an XML file:");
		int id=Integer.parseInt(reader.readLine());
		PreparedStatement prep2= c.prepareStatement("SELECT * FROM reports WHERE id= ?");//, Pharmacy.class
		prep2.setInt(1, id);
		ResultSet rs = prep2.executeQuery();
		rs.next();
		Pharmacy pharmacy = (Pharmacy) prep2.getResultSet();
		
		File file = new File("./xmls/Sample-Pharmacy.xml");
		marshaller.marshal(pharmacy,file);
		marshaller.marshal(pharmacy, System.out);
		
		
	}
	
}
