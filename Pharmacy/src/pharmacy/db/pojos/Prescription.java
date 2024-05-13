	
	package pharmacy.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
	
	public class Prescription implements Serializable{

	
	private static final long serialVersionUID = -758766051603616038L;
	private int id;
	private int quantity;
	private Date issueDate;
	private Date dateUsed;
	private Patient patient;
	private List<Medicine> medicines;
	
	public Prescription(int id, int quantity, Date issueDate, Date dateUsed, Patient patient){
		this.id= id;
		this.quantity=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
		this.patient=patient;
		this.medicines = new ArrayList<Medicine>();

	}
	
	public Prescription(int id, int quantity, Date issueDate, Date dateUsed) {
		this.id=id;
		this.quantity=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
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
	
	public Date getIssueDate(){
		return issueDate;
	}
	
	public void setIssueDate(Date issueDate) {
		this.issueDate=issueDate;
	}
	
	public Date getDateUsed(){
		return dateUsed;
	}
	
	public void setDateUsed(Date dateUsed) {
		this.dateUsed=dateUsed;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient p) {
		this.patient=p;
	}
	
	public List<Medicine> getMedicines() {
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