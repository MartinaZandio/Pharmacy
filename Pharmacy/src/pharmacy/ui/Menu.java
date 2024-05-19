package pharmacy.ui;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.interfaces.*;
import pharmacy.db.jdbc.*;
import pharmacy.db.jpa.JPAUserManager;
import pharmacy.db.pojos.*;
import pharmacy.db.xml.XmlPharmacyManager;

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

	public static void main(String[] args) throws Exception {
		
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
		System.out.println("0. Save & exit.");

		int choice=Integer.parseInt(r.readLine());
		return choice;
	}
		
		private static Patient menuLogin() throws Exception{
			System.out.println("Username: ");
			String username = r.readLine();
			System.out.println("Password: ");
			String password = r.readLine();
			User u = userMan.login(username, password);
			if (u.getRole().getName() == "Patient" ) {
				Patient p = patientManager.getPatient(username);
				patientMenu();
				return p;
			} else {
			pharmacistMenu();
			}
			return null;
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
			
		private static void patientMenu() throws Exception{
			ConnectionManager conMan = new ConnectionManager();
			patientManager = new JDBCPatientManager(conMan);
			
			Patient p = menuLogin();
			int patient_id=p.getId();
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Check medical history.");
			System.out.println("2. Check medicine.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			do {
				switch(choice) {
				case 1: {
					checkMedicalHistoryMenu(patient_id);
					break;
				}
				case 2: {
					checkMedicineMenu(patient_id);
					break;
				}
				case 0: {
					mainMenu();
					return;
				}
				default:
				}
			} while (choice != 0);
			
		}
		
		private static void checkMedicalHistoryMenu(int patient_id) throws Exception {
			
			ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(patient_id);
			System.out.println(prescriptions);
			
			patientMenu();
			
		}
		
		private static void checkMedicineMenu(int patient_id) throws Exception {
			
			ArrayList<Medicine> medicines = new ArrayList<Medicine>();
			medicines = medicineManager.getMedicines(patient_id);
			System.out.println(medicines);
			
			patientMenu();
		}
		
		private static void pharmacistMenu() throws Exception{
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Identify a patient.");
			System.out.println("2. Check stock.");
			System.out.println("3. Print Xml.");
			System.out.println("4. Change Xml to Java.");

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
				case 3:{
					XmlPharmacyManager.pharmacy2Xml();
					break;
				}
				case 4:{
					XmlPharmacyManager.xml2Pharmacy();
					break;
				}
				case 0: {
					mainMenu();
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
			patientManager.identifyPatient(patientId);
			pharmacist_patientMenu();
			return patientId;
		}
		
		private static int checkStockMenu() throws Exception {
			System.out.println("Type the medicine name: ");
			String medicineName = r.readLine();
			List<Medicine> meds = medicineManager.searchMedicineByName(medicineName);
			System.out.println(meds);
			System.out.println("Type the medicine id that you want to select: ");
			int medicineId = Integer.parseInt(r.readLine());
			Medicine m = medicineManager.getMedicine(medicineId);
			System.out.println(m);
			stockMenu();
			return medicineId;
			
		}
		
		
		private static void pharmacist_patientMenu() throws IOException {
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Mark prescription as used.");
			System.out.println("2. Check autenticity.");
			System.out.println("3. Sell medicine.");
			System.out.println("0. Go back.");

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
					identifyPatientMenu();
					return;
				}
				default:
				}
			} while (choice != 0);
		}
	
		
		private static void markPrescriptionAsUsedMenu() throws IOException{
			ConnectionManager conMan = new ConnectionManager();
			prescriptionManager = new JDBCPrescriptionManager(conMan);
			
			int patientId = identifyPatientMenu();
		
			System.out.println("Select the prescription you want to mark as used, by typing its id.");
			ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(patientId);
			System.out.println(prescriptions);
			Integer idPrescription = Integer.parseInt(r.readLine());
			
			pharmacyManager.markPrescriptionAsUsed(idPrescription);
			
			pharmacist_patientMenu();
		}
		
		private static void checkAutenticityMenu() throws IOException {
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			int patientId = identifyPatientMenu();

			System.out.println("Select the prescription you want to check by typing its id: ");
			ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(patientId);
			System.out.println(prescriptions);
			Integer idPrescription = Integer.parseInt(r.readLine());
		
		
			boolean autenticity = pharmacyManager.checkAuthenticity(idPrescription);
			System.out.println(autenticity);
			
			pharmacist_patientMenu();
		}
		
		private static void sellMedicine() throws IOException {
			int patientId = identifyPatientMenu();
			
			System.out.println("Type the pharmacy name: ");
			String name = r.readLine();
			ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
			pharmacies = pharmacyManager.getPharmacy(name);
			System.out.println(pharmacies);
			Integer idPharmacy = Integer.parseInt(r.readLine());
			
			pharmacyManager.sellMedicine(patientId, idPharmacy);
		}
		
		private static void stockMenu() throws Exception{
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
					pharmacistMenu();
					return;
				}
				default:
				}
			} while (choice != 0);
		}

		private static void orderStockMenu() throws Exception {
			int medicineId = checkStockMenu();
			
			System.out.println("Type the amount of medicine you want to order: ");
			int quantity = Integer.parseInt(r.readLine());
			
			System.out.println("Type the pharmacy name: ");
			String name = r.readLine();
			ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
			pharmacies = pharmacyManager.getPharmacy(name);
			System.out.println(pharmacies);
			Integer idPharmacy = Integer.parseInt(r.readLine());
		
			pharmacyManager.orderStock(medicineId, idPharmacy, quantity);
			
			pharmacistMenu();
		}
}