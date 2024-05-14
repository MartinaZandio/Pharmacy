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

		System.out.println("Welcome!");
		System.out.println("Select an option by typing a number: ");
		System.out.println("1. Login.");
		System.out.println("2. Sing up.");
		System.out.println("0. Save & exit.");

		int choice=Integer.parseInt(r.readLine());
		switch(choice) {
		case 1: {
			menuLogin();
			break;
		}
		case 2: {
			menuSingUp();
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
			String username = r.readLine();
			System.out.println("Password: ");
			String password = r.readLine();
			User u= userMan.login(username, password);
//			if (u.getRole().getName().equals()) {
				
			} //else {
				//pharmacistMenu();
			//}
		//}
			
		private static void menuSingUp() throws IOException {
			System.out.println("Choose a username: ");
			String username = r.readLine();
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
			
		private static void patientMenu() throws NumberFormatException, IOException{
			ConnectionManager conMan = new ConnectionManager();
			patientManager = new JDBCPatientManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Check medical history.");
			System.out.println("2. Check medicine.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
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
			switch(choice) {
			case 1: {
				identifyPatient();
				break;
			}
			case 2: {
				checkStock();
				break;
			}
			case 0: {
				return;
			}
			default:
			}
			
		}
		
		private static int identifyPatient() throws IOException {
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
		
		private static void checkStock() throws IOException {
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
		
		private static void pharmacist_patientMenu() {
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Mark prescription as used.");
			System.out.println("2. Check autenticity.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			switch(choice) {
			case 1: {
				markPrescriptionAsUsed();
				break;
			}
			case 2: {
				checkAutenticity();
				break;
			}
			case 0: {
				return;
			}
			default:
			}
		}
		
		private static void markPrescriptionAsUsed() throws IOException{
			ConnectionManager conMan = new ConnectionManager();
			prescriptionManager = new JDBCPrescriptionManager(conMan);
		
			System.out.println("Select the prescription you want to mark as used, by typing its id.");
			ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
			prescriptions = prescriptionManager.getPrescription(identifyPatient());
			Integer idPrescription = Integer.parseInt(r.readLine());
		}
		
		private static void stockMenu() throws NumberFormatException, IOException{
			ConnectionManager conMan = new ConnectionManager();
			pharmacyManager = new JDBCPharmacyManager(conMan);
			
			System.out.println("Select an option by typing a number: ");
			System.out.println("1. Order new stock.");
			System.out.println("0. Exit");

			int choice=Integer.parseInt(r.readLine());
			switch(choice) {
			case 1: {
				orderStock();
				break;
			}
			case 0: {
				return;
			}
			default:
			}
		}

		private static void orderStock() throws NumberFormatException, IOException {
			System.out.println("Type the amount of medicine you want to order: ");
			int quantity = Integer.parseInt(r.readLine());
		}
			
		
		
		
		/*private static void addPatient() throws NumberFormatException, IOException{
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
			ArrayList<Stock> stock;
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
		
		private static void listPatients() throws IOException{
			System.out.println("Patients name (press enter to search all): ");
			String name = r.readLine();
			System.out.println("These are the available patients, choose one by typing their id:");
			List<Patient> patients = patientManager.searchPatientByName(name);
			System.out.println(patients);
		}
		
		private static Medicine chooseMedicine() throws IOException{
			System.out.println("Choose a medicine by typing its ID: " );
			listMedicines();
			int dep_id = Integer.parseInt(r.readLine());
			Medicine medicine = new Medicine(dep_id);
			return medicine;
		}
		
		private static Patient choosePatient() throws IOException{
			System.out.println("Choose a patient to assigng the medicine: ");
			listPatients();
			String patient = r.readLine();
			return patient;
		}*/
}