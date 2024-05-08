	
	package pharmacy.db.pojos;
	import java.util.*;

import pharmacy.db.pojos.*;

import java.io.Serializable;

	public class Prescription implements Serializable{

	
	private static final long serialVersionUID = -758766051603616038L;
	private int id;
	private int quantity;
	private String issueDate;
	private String dateUsed;
	private Patient patient;
	private ArrayList<Medicine> medicines;
	
	public Prescription(int id, int quantity, String issueDate, String dateUsed, Patient patient){
		this.id= id;
		this.quantity=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
		this.patient=patient;
		this.medicines = new ArrayList<Medicine>();

	}
	
	public Prescription(int id, int quantity, String issueDate, String dateUsed) {
		this.id=id;
		this.quantity=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
		this.patient=patient;
		this.medicines = new ArrayList<Medicine>();
	}
	
	public Prescription() {
		super();
		this.medicines = new ArrayList<Medicine>();
	}
	
	
	//Getters y Setters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	public String getIssueDate(){
		return issueDate;
	}
	
	public void setIssueDate(String issueDate) {
		this.issueDate=issueDate;
	}
	
	public String getDateUsed(){
		return dateUsed;
	}
	
	public void setDateUsed(String dateUsed) {
		this.dateUsed=dateUsed;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient p) {
		this.patient=p;
	}
	
	public ArrayList<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(ArrayList<Medicine> medicines) {
		this.medicines = medicines;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", quantity=" + quantity + ", issueDate=" + issueDate + ", dateUsed="
				+ dateUsed + ", patient=" + patient + ", medicines=" + medicines + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateUsed, id, issueDate, medicines, patient, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prescription other = (Prescription) obj;
		return Objects.equals(dateUsed, other.dateUsed) && id == other.id && Objects.equals(issueDate, other.issueDate)
				&& Objects.equals(medicines, other.medicines) && Objects.equals(patient, other.patient)
				&& quantity == other.quantity;
	}

	
	
	
}