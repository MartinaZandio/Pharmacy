package pharmacy.ui;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pharmacy.db.interfaces.PatientProfile;
import pharmacy.db.jdbc.JDBCPatientManager;

public class Menu {

	private static BufferedReader r= new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static PatientProfile patientManager = new JDBCPatientManager();
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		System.out.println("Choose your desired option");
		System.out.println("1. Add a new patient");
		System.out.println("2. Search a patient by its name");
		System.out.println("3. Search a patient by its id");
		System.out.println("0. Exit");

		int choice=Integer.parseInt(r.readLine());
		switch(choice) {
		case 1: {
			addPatient();
		}
		case 2: {
			break;
		}
		case 3: {
			break;
		}
		case 0: {
			return;
		}
		default:
		}
		
		private static void addPatient() throws NumberFormatException, IOException{
			System.out.println("Please, write the Patient info: ");
			System.out.println("Id:");
			Integer id= Integer.parseInt(r.readLine());
			System.out.println("Name: ");
			String name= r.readLine();
			System.out.println("Date of birth (DD-MM-YYYY):");
			LocalDate localDate= LocalDate.parse(r.readLine(), formatter);
			Date dob= Date.valueOf(localDate);
			System.out.println("Sex(HOMBRE, MUJER): ");
			String sex= r.readLine();
			Patient patient = new Patient(id, name, dob, sex);
			patientManager.addPatient(null);
			break;
		}
	}
	
	
}
