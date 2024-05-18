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
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		conMan = new ConnectionManager();
		patientManager = conMan.getPatientMan();
		pharmacyManager = conMan.getPharmacyMan();
		medicineManager = conMan.getMedicineMan();
		prescriptionManager = conMan.getPrescriptionMan();
		userMan = new JPAUserManager();

		int choice = mainMenu();
		do {
			switch(choice) {
			case 1: {
				menuLogin();
				}
			break;
			case 2: {
				menuSignUp();
				break;
				}
			case 3: {
				break;
			}
			case 0: {
				conMan.close();
				return;
				}
			default:
				}
			} while (choice != 0);
	}
	
	private static int mainMenu() throws NumberFormatException, IOException {
		System.out.println("Welcome!");
		System.out.println("Select an option by typing a number: ");
		System.out.println("1. Login.");
		System.out.println("2. Sign up.");
		// System.out.println("3. Turn pharmacy into an XML file.");
		System.out.println("0. Save & exit.");

		int choice=Integer.parseInt(r.readLine());
		return choice;
	}
		
		private static void menuLogin() throws NumberFormatException, IOException{
			System.out.println("Username: ");
			String username = r.readLine();
			System.out.println("Password: ");
			String password = r.readLine();
			User u = userMan.login(username, password);
			if (u.getRole().getName() == "Patient" ) {
				Patient p = patientManager.getPatient(username);
				patientMenu(p);
			} else {
			pharmacistMenu();
			}
		}
		
		
		private static void menuSignUp() throws IOException {
			System.out.println("Choose a username: ");
			String username = r.readLine();
			System.out.println("Choose a password: ");
			String password = r.readLine();
			System.out.println("Choose your role (type its name): ");
			List<Role> roles = userMan.getAllRoles();
			System.out.println(roles);
			String roleName = r.readLine();
			Role role = userMan.getRole(roleName);
			User u = new User (username,password, role);
			userMan.register(u);
			if (u.getRole().getName() == "Patient" ) {
				System.out.println("Please type your name: ");
				String name = r.readLine();
				System.out.println("Please type your date of birth (dd-MM-yyyy): ");
				String date = r.readLine();
				Date dateOfBirth = (Date) formatter.parse(date);
				System.out.println("Please type your gender: ");
				String sex = r.readLine();
				Patient p = new Patient(name, dateOfBirth, sex, username);
				patientManager.addPatient(p);
			}
		}
			
		private static void patientMenu(Patient p) throws NumberFormatException, IOException{
			ConnectionManager conMan = new ConnectionManager();
			patientManager = new JDBCPatientManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Check medical history.");
			System.out.println("2. Check medicine.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			do {
				switch(choice) {
				case 1: {;
					break;
				}
				case 2: {
					break;
				}
				case 0: {
					return;
				}
				default:
				}
			} while (choice != 0);
			
		}
		
		private static void pharmacistMenu() throws NumberFormatException, IOException{
			ConnectionManager conMan = new ConnectionManager();
			//patientManager = new JDBCPatientManager(conMan);
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Identify a pacient.");
			System.out.println("2. Check stock.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			do {
				switch(choice) {
				case 1: {
					identifyPatientMenu();
					break;
				}
				case 2: {
					checkStockMenu();
					break;
				}
				case 0: {
					return;
				}
				default:
				}
			} while (choice != 0);
		}
		
		
		private static int identifyPatientMenu() throws IOException {
			System.out.println("Type the patient's name: ");
			String patientName = r.readLine();
			List<Patient> patients = pharmacyManager.identifyPatient(patientName);
			System.out.println(patients);
			System.out.println("Type the patient's id that you want to select: ");
			int patientId = Integer.parseInt(r.readLine());
			Patient p = patientManager.getPatient(patientId);
			System.out.println(p);
			pharmacist_patientMenu();
			return patientId;
		}
		
		private static void checkStockMenu() throws IOException {
			System.out.println("Type the medicine name: ");
			String medicineName = r.readLine();
			List<Medicine> meds = medicineManager.searchMedicineByName(medicineName);
			System.out.println(meds);
			System.out.println("Type the medicine id that you want to select: ");
			int medicineId = Integer.parseInt(r.readLine());
			Medicine m = medicineManager.getMedicine(medicineId);
			System.out.println(m);
			stockMenu();
		}
		
		private static void pharmacist_patientMenu() throws IOException {
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Mark prescription as used.");
			System.out.println("2. Check autenticity.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			do {
				switch(choice) {
				case 1: {
					markPrescriptionAsUsedMenu();
					break;
				}
				case 2: {
					checkAutenticityMenu();
					break;
				}
				case 0: {
					return;
				}
				default:
				}
			} while (choice != 0);
		}
	
		
		private static void markPrescriptionAsUsedMenu() throws IOException{
			ConnectionManager conMan = new ConnectionManager();
			prescriptionManager = new JDBCPrescriptionManager(conMan);
		
			System.out.println("Select the prescription you want to mark as used, by typing its id.");
			ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(identifyPatientMenu());
			Integer idPrescription = Integer.parseInt(r.readLine());
		}
		
		private static void checkAutenticityMenu() throws IOException {
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
		
			boolean autenticity = pharmacyManager.checkAuthenticity(identifyPatientMenu());
			System.out.println(autenticity);
		}
		
		private static void stockMenu() throws NumberFormatException, IOException{
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Order new stock.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			do {
				switch(choice) {
				case 1: {
					orderStockMenu();
					break;
				}
				case 0: {
					return;
				}
				default:
				}
			} while (choice != 0);
		}

		private static void orderStockMenu() throws NumberFormatException, IOException {
			System.out.println("Type the amount of medicine you want to order: ");
			int quantity = Integer.parseInt(r.readLine());
		}
}