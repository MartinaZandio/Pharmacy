package pharmacy.ui;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.interfaces.*;
import pharmacy.db.jdbc.*;
import pharmacy.db.jpa.JPAUserManager;
import pharmacy.db.pojos.*;

public class Menu {

	private static BufferedReader r= new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private static ConnectionManager conMan;
	private static LaboratoryManager laboratoryManager;
	private static MedicineManager medicineManager;
	private static PatientManager patientManager;
	private static PharmacyManager pharmacyManager;
	private static PrescriptionManager prescriptionManager; 
	private static UserManager userMan;
	

	public static void main(String[] args) throws NumberFormatException, IOException{
		
		conMan = new ConnectionManager();
		patientManager = conMan.getPatientMan();
		//pharmacyManager = new JDBCPharmacyManager(conMan.getConnection());
		medicineManager = conMan.getMedicineMan();
		prescriptionManager = conMan.getPrescriptionMan();
		userMan = new JPAUserManager();
		
		System.out.println("Choose your desired option");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("0. Exit");

		int choice=Integer.parseInt(r.readLine());
		switch(choice) {
		case 1: {
			menuLogin();
			break;
		}
		case 2: {
			menuRegister();
			break;
		}
		case 0: {
			conMan.close();
			return;
		}
		default:
		}
		
	}
		
		private static void menuLogin() throws NumberFormatException, IOException{
			System.out.println("Username: ");
			String username=r.readLine();
			System.out.println("Password: ");
			String password=r.readLine();
			User u= userMan.login(username, password);
		}
		
		private static void menuRegister() throws NumberFormatException, IOException{
			System.out.println("Choose a username: ");
			String username=r.readLine();
			System.out.println("Choose a password: ");
			String password = r.readLine();
			System.out.println("Choose your role (type its name): ");
			List<Role> roles = userMan.getAllRoles();
			System.out.println(roles);
			String roleName = r.readLine();
			Role r = userMan.getRole(roleName);
			User u= new User (username,password, r);
			userMan.register(u);
		}
		
		
	
		private static void pharmacyMenu() throws NumberFormatException, IOException{
			ConnectionManager conMan = new ConnectionManager();
			patientManager = new JDBCPatientManager(conMan);
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			System.out.println("Choose your desired option");
			System.out.println("1. Add a new patient");
			System.out.println("2. Search a patient by its name");
			System.out.println("3. Search a patient by its id");
			System.out.println("4. Add a new medicine");

			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			switch(choice) {
			case 1: {
				addPatient();
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				addMedicine();
			}
			case 0: {
				return;
			}
			default:
			}
			
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
			patientManager.addPatient(patient);
		}
		
		private static void addMedicine() throws NumberFormatException, IOException{
			System.out.println("Please, write the Medicine info: ");
			System.out.println("Name:");
			String name= r.readLine();
			System.out.println("NumberAssigned: ");
			Integer numAsigned= Integer.parseInt(r.readLine());
			/*System.out.println("Prescription id:");
			Integer prescriptionId= Integer.parseInt(r.readLine());
			System.out.println("Stock: ");
			ArrayList<Stock> stock;*/
			System.out.println("These are the available medicines, choose one by typing its id: ");
			listMedicines();
			Integer medId=Integer.parseInt(r.readLine());
			Medicine medicine = new Medicine(name, numAsigned);
			medicineManager.addMedicine(medicine);
		}
		
		private static void listMedicines() throws IOException{
			System.out.println("Medicine name (press enter to search all): ");
			String name = r.readLine();
			System.out.println("These are the available medicines, choose one by typing their id:");
			List<Medicine> medicines = medicineManager.searchMedicineByName(name);
			System.out.println(medicines);
			
			
		}
		
	
	
	
}
	

