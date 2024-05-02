package pharmacy.db.jdbc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.pojos.*;
import pharmacy.db.pojos.Patient.gender;


public class JDBCPharmacyManager implements PharmacyManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPharmacyManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

	@Override
	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void markPrescriptionAsUsed(Prescription prescription) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose a prescription by typing its ID: ");
			// Show all id's method??
			int dep_id = Integer.parseInt(reader.readLine());
			System.out.println("Choose by typing one option: USED / NOT USED");
			String usage = reader.readLine();
			String sql = "UPDATE prescriptions WHERE id LIKE ? SET used=?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, dep_id);
			p.setString(2, usage);
			p.executeUpdate();
			System.out.println("Update finished.");
			c.close();
		} catch (Exception e) {
			System.out.println("Error modifying prescription.");
		}
	}
	

	@Override
	public void checkAuthenticity(Prescription prescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkStock(String name) {
		

	}

	@Override
	public void orderStock(Medicine medicine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void identifyPatient(Patient patient) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type the patients name: ");
			String name = reader.readLine();
			String sql = "SELECT * FROM patients WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, name);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name1 = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				gender sex = rs.getSex("sex");
				Patient p = new Patient(id, name, dateOfBirth, sex);
				System.out.println(p);
			}
			rs.close();
			search.close();
			System.out.println("Search finished.");
			c.close();
			System.out.println("Database connection closed.");
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
		}
	
	
	@Override
	public void assignMedicine(Medicine medicine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkStock(Medicine medicine) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type the medicine's id: ");
			// Show medicines id 
			int medicine_id = Integer.parseInt(reader.readLine());
			String sql = "SELECT name, id, stock FROM medicine WHERE id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, medicine_id);
			search.close();
			System.out.println("Search finished.");
			c.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
			}
		}
	}
