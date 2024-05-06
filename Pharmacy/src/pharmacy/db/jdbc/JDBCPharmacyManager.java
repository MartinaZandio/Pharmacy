package pharmacy.db.jdbc;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
=======
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import library.db.pojos.Author;
import library.db.pojos.Borrower;
=======
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
import pharmacy.db.interfaces.PharmacyManager;
import pharmacy.db.pojos.*;

public class JDBCPharmacyManager implements PharmacyManager {
	
	private Connection c;
	private ConnectionManager conMan; 
	
	public JDBCPharmacyManager(ConnectionManager conMan) {
		this.conMan = conMan; 
		this.c = conMan.getConnection();
	}

	@Override
	// En las tabla prescription no hay nada que relacione una medicina especifica con un paciente tan solo la cantidad 
	public void giveMedicine(Medicine medicine, Prescription prescription, Patient patient) {
<<<<<<< HEAD
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose a medicine by typing its ID: " );
			// Show medicines's ID method 
			int dep_id = Integer.parseInt(reader.readLine());
			System.out.println("Choose a patient to assigng the medicine: ");
			// Show patients method
			String patient1 = reader.readLine();
			String sql = "UPDATE prescriptions WHERE ";
		} catch (Exception e) {
			System.out.println("Error modifying prescription.");
		}
=======
		// TODO Auto-generated method stub
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy

	}

	@Override
	public void markPrescriptionAsUsed(Prescription prescription) {
		// TODO Auto-generated method stub

	}
<<<<<<< HEAD
	
	
=======

>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	@Override
<<<<<<< HEAD
	public boolean checkAuthenticity() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type the prescription id: ");
			// Show prescription 
			int prescription_id = Integer.parseInt(reader.readLine());
			String sql = "SELECT useDate FROM prescriptions WHERE id LIKE ?";
			PreparedStatement search = c.prepareStatement(sql); 
			search.setInt(1, prescription_id);
			ResultSet rs = search.executeQuery();
			rs.next();
			Date useDate = rs.getDate("useDate");
			if (useDate != null) {
				return true;
			} else return false;
		} catch (SQLException | NumberFormatException | IOException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
			}
	}
=======
	public void checkAuthenticity(Prescription prescription) {
		// TODO Auto-generated method stub
		
			
		}
		
		
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy

	
	@Override
	public void orderStock(Medicine medicine) {

		
	}

	@Override
	public void identifyPatient(Patient patient) {
		// TODO Auto-generated method stub
		try {
			String template = "SELECT * FROM patients WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(template);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateOfBirth = rs.getDate("dateOfBirth");
<<<<<<< HEAD
				gender sex = gender.valueOf(rs.getString("sex"));
				Patient p = new Patient(id, name, dateOfBirth, sex);
				System.out.println(p);
			} // to INSERT sex.name()
			rs.close();
			search.close();
			System.out.println("Search finished.");
			c.close();
			System.out.println("Database connection closed.");
=======
				String sex = rs.getString("gender");
				}
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
			} catch (SQLException e) {
				System.out.println("Error creating the prescription");
				e.printStackTrace();
				}
		}

	@Override
<<<<<<< HEAD
	public void assignMedicine() {
=======
	public void assignMedicine(Medicine medicine, Prescription prescription) {
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
		// TODO Auto-generated method stub
<<<<<<< HEAD
		try {
			
		} catch (SQLException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
			}
=======
		try { //open data base connection
			Statement stmt=c.createStatement();
			String sql;
			sql = "INSERT INTO prescriptionMedicine (medicine_numAsigned, prescription_id)"
					+ "VALUES (?,?);";
			
			PreparedStatement insert= c.prepareStatement(sql);
			insert.setInt(1, medicine.getNumAsigned());
			insert.setInt(2, prescription.getId());
			
			insert.executeUpdate();
			insert.close();
		
	}
		catch (SQLException sqlE) {
			System.out.println("Error");
			sqlE.printStackTrace();
			
	}



>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	}
	
	
	@Override
	public void checkStock(Medicine medicine) {
<<<<<<< HEAD
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
		} catch (SQLException | NumberFormatException | IOException e) {
			System.out.println("Error creating the prescription");
			e.printStackTrace();
			}
		}
=======
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignMedicine(Medicine medicine) {
		// TODO Auto-generated method stub
		
	}
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	}
