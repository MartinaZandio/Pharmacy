package pharmacy.ui;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pharmacy.db.jdbc.ConnectionManager;
import pharmacy.db.interfaces.*;
import pharmacy.db.jpa.JPAUserManager;
import pharmacy.db.pojos.*;
import pharmacy.db.xml.XML2HtmlPharmacy;
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

		int choice;
		do {
			choice = mainMenu();
			switch(choice) {
			case 1: {
				menuLogin();
				break;
			}
			
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
		System.out.println();
		System.out.println("Welcome!");
		System.out.println("Select an option by typing a number: ");
		System.out.println("	1. Log in.");
		System.out.println("	2. Sign up.");
		System.out.println("	0. Save & exit.");

		int choice=Integer.parseInt(r.readLine());
		return choice;
	}
		
		private static void menuLogin() throws Exception{
			try {
				System.out.println("· Username: ");
				String username = r.readLine();
				System.out.println("· Password: ");
				String password = r.readLine();
				User u = userMan.login(username, password);
				if (u.getRole().getName().equals("Patient")) {
					Patient p = patientManager.getPatient(username);
					patientMenu(p);
				} else {
				pharmacistMenu();
				}
			} catch (Exception e){
				System.out.println("Error with the username / password.");
				e.printStackTrace();
				menuLogin();
			}
		}
		
		
		private static void menuSignUp() throws Exception {
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
			if (u.getRole().getName().equals("Patient")) {
				System.out.println("Please type your name and lastname: ");
				String name = r.readLine();
				System.out.println("Please type your date of birth (dd-MM-yyyy): ");
				String date = r.readLine();
				LocalDate localDate = LocalDate.parse(date, formatter);
				Date dateOfBirth = Date.valueOf(localDate);
				System.out.println("Please type your gender: ");
				String sex = r.readLine();
				Patient p = new Patient(name, dateOfBirth, sex, username);
				patientManager.addPatient(p);
			}
			else if(u.getRole().getName().equals("Pharmacist")) {
				System.out.println("Pharmacist user created");
			}
		}
			
		private static void patientMenu(Patient p) throws Exception{
			int patient_id;
			int choice;
			
			do {
			System.out.println();
			System.out.println("Select an option by typing a number: ");
			System.out.println("	1. Check medical history.");
			System.out.println("	2. Check medicine.");
			System.out.println("	3. Insert prescription to the db.");
			System.out.println("	0. Exit");
			
				patient_id = p.getId();
				choice=Integer.parseInt(r.readLine());
				switch(choice) {
				case 1: {
					checkMedicalHistoryMenu(patient_id);
					break;
				}
				case 2: {
					checkMedicineMenu(patient_id);
					break;
				}
				case 3: {
					insertPrescriptionMenu(patient_id);
					break;
				}
				case 0: {
					return;
				}
				default:
				}
				
			} while (choice != 0);
			
		}
		
		private static void insertPrescriptionMenu(int patient_id) throws Exception {
			System.out.println("Insert the quantity of medicine in the prescription: ");
			int quantity = Integer.parseInt(r.readLine());
			
			System.out.println("Please type the issue date (dd-MM-yyyy): ");
			String date = r.readLine();
			LocalDate localDate = LocalDate.parse(date, formatter);
			Date issueDate = Date.valueOf(localDate);
			
			Prescription p;
			p = prescriptionManager.createPrescription(quantity, issueDate, patient_id);
			
			System.out.println("Type the medicine name: ");
			String medicineName = r.readLine();
			List<Medicine> meds = medicineManager.searchMedicineByName(medicineName);
			System.out.println(meds);
			System.out.println("Type the medicine id that you want to select: ");
			int medicineId = Integer.parseInt(r.readLine());
			prescriptionManager.pres_medInsert(p.getId(), medicineId);
		}
		
		private static void checkMedicalHistoryMenu(int patient_id) throws Exception {
			List<Prescription> prescriptions = new ArrayList<Prescription>();
			
			prescriptions = prescriptionManager.getPrescription(patient_id);
			System.out.println(prescriptions);		
		}
		
		private static void checkMedicineMenu(int patient_id) throws Exception {
			List<Prescription> prs= new ArrayList<Prescription>();
			prs = medicineManager.getPrescription(patient_id);
			for(Prescription p: prs) {
				System.out.println(medicineManager.getMedicines(p.getId()));
			}
		}
		
		private static void pharmacistMenu() throws Exception{
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("	1. Identify a patient.");
			System.out.println("	2. Check stock.");
			System.out.println("	3. Print Xml.");
			System.out.println("	4. Change Xml to Java.");
			System.out.println("	0. Exit");			
			
			int choice;
			do {
				choice=Integer.parseInt(r.readLine());
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
					pharmacy2Xml();
					break;
				}
				case 4:{
					xml2Pharmacy();
					System.out.println("	Press 1 to get html file.");
					int c=Integer.parseInt(r.readLine());
					if (c==1) getHtmlFile();
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
		
		private static void pharmacy2Xml() throws Exception {
			System.out.print("Choose a pharmacy to turn into an XML file.");
			System.out.println("Type the pharmacy name: ");
			String name = r.readLine();
			
			List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
			pharmacies = pharmacyManager.getPharmacy(name);
			System.out.println(pharmacies);
			
			System.out.println("Type the pharmacy id: ");
			Integer idPharmacy = Integer.parseInt(r.readLine());
			Pharmacy p= pharmacyManager.getPharmacy(idPharmacy);
			p.setStock(pharmacyManager.getStockPharmacy(idPharmacy));
			
			File xml = new File ("./xmls/External-Pharmacy"); 
			XmlPharmacyManager.pharmacy2Xml(p,xml);
		}
		
		private static void xml2Pharmacy() throws Exception {
			System.out.print("Introduce an Xml to turn into a Java file:");
			String rute = r.readLine();
			File xml= new File(rute);
			XmlPharmacyManager.xml2Pharmacy(xml);
		}
		
		private static void getHtmlFile() throws Exception {
			System.out.println("Introduce the absolute path to source XML file: ");
			String source= r.readLine();
			System.out.println("Introduce the absolute path to XSLT file");
			String xsltPath= r.readLine();
			System.out.println("Introduce the directory where you want to put resulting file");
			String directory= r.readLine();
			XML2HtmlPharmacy.simpleTransfrom(source, xsltPath, directory);
		}
		
		private static int identifyPatientMenu() throws Exception {
			System.out.println("Type the patient's name: ");
			String patientName = r.readLine();
			List<Patient> patients = pharmacyManager.identifyPatient(patientName);
			System.out.println(patients);
			System.out.println("Type the patient's id that you want to select: ");
			int patientId = Integer.parseInt(r.readLine());
			
			Patient p = patientManager.identifyPatient(patientId);
			pharmacist_patientMenu(p);
			return patientId;
		}
		
		private static int checkStockMenu() throws Exception {
			System.out.println("Type the pharmacy name: ");
			String name = r.readLine();
			List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
			pharmacies = pharmacyManager.getPharmacy(name);
			System.out.println(pharmacies);
			System.out.println("Type the id of the pharmacy where you want to check stock.");
			int idPharmacy = Integer.parseInt(r.readLine());
			
			System.out.println("Type the medicine name: ");
			String medicineName = r.readLine();
			List<Medicine> meds = medicineManager.searchMedicineByName(medicineName);
			System.out.println(meds);
			System.out.println("Type the medicine id that you want to select: ");
			int medicineId = Integer.parseInt(r.readLine());
			
			int stock = medicineManager.getMedicine(medicineId, idPharmacy);
			System.out.println("The stock of the medcine selected is " +stock);
			stockMenu(medicineId);
			return medicineId;
			
		}
		
		private static void pharmacist_patientMenu(Patient p) throws Exception {
			int patient_id = p.getId();
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("	1. Mark prescription as used.");
			System.out.println("	2. Check autenticity.");
			System.out.println("	3. Sell medicine.");
			System.out.println("	0. Go back.");

			int choice;
			do {
				choice=Integer.parseInt(r.readLine());
				switch(choice) {
				case 1: {
					markPrescriptionAsUsedMenu(patient_id);
					break;
				}
				case 2: {
					checkAutenticityMenu(patient_id);
					break;
				}
				case 3: {
					sellMedicineMenu(patient_id);
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
	
		
		private static void markPrescriptionAsUsedMenu(int patient_id) throws Exception{
			
			System.out.println("Select the prescription you want to mark as used, by typing its id.");
			List<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(patient_id);
			System.out.println(prescriptions);
			Integer idPrescription = Integer.parseInt(r.readLine());
			pharmacistMenu();
		}
		
		private static void checkAutenticityMenu(int patient_id) throws Exception {
	
			System.out.println("Select the prescription you want to check by typing its id: ");
			List<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(patient_id);
			System.out.println(prescriptions);
			Integer idPrescription = Integer.parseInt(r.readLine());
		
			boolean autenticity = pharmacyManager.checkAuthenticity(idPrescription);
			if (autenticity == false) {
				System.out.println("The prescription has not been used yet. It's valid.");
			} else {
				System.out.println("The prescription has been used. It's not valid.");
			}
			
			pharmacistMenu();
		}
		
		private static void sellMedicineMenu(int patient_id) throws Exception {
			System.out.println("Type the pharmacy name: ");
			String name = r.readLine();
			List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
			pharmacies = pharmacyManager.getPharmacy(name);
			System.out.println(pharmacies);
			System.out.println("Type the pharmacy id: ");
			Integer idPharmacy = Integer.parseInt(r.readLine());
			
			pharmacyManager.sellMedicine(patient_id, idPharmacy);
			
			pharmacistMenu();
		}
		
		private static void stockMenu(int medicineId) throws Exception{
			System.out.println("Select an option by typing a number: ");
			System.out.println("	1. Order new stock.");
			System.out.println("	0. Exit");
			
			int choice;
			do {
				choice=Integer.parseInt(r.readLine());
				switch(choice) {
				case 1: {
					orderStockMenu(medicineId);
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

		private static void orderStockMenu(int medicineId) throws Exception {		
			System.out.println("Type the amount of medicine you want to order: ");
			int quantity = Integer.parseInt(r.readLine());
			
			System.out.println("Type the pharmacy name: ");
			String name = r.readLine();
			List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
			pharmacies = pharmacyManager.getPharmacy(name);
			System.out.println(pharmacies);
			System.out.println("Type the id of the pharmacy where you want to order the new stock.");
			Integer idPharmacy = Integer.parseInt(r.readLine());
		
			pharmacyManager.orderStock(medicineId, idPharmacy, quantity);
			pharmacistMenu();
		}
}