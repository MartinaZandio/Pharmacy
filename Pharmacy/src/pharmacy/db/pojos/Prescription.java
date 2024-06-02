	
package pharmacy.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


	public class Prescription implements Serializable{
	
	private static final long serialVersionUID = -758766051603616038L;
	private int id;
	private int qty;
	private Date issueDate;
	private Date dateUsed;
	private int patient;
	
	private List<Medicine> medicines;
	
	
	public Prescription() {
		super();
		this.medicines = new ArrayList<Medicine>();
	}
	
	public Prescription(int id, int quantity, Date issueDate, Date dateUsed) {
		this.id=id;
		this.qty=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
		this.medicines = new ArrayList<Medicine>();
	}
	
	public Prescription(int id, int quantity, Date issueDate, Date dateUsed, int patient) {
		this.id=id;
		this.qty=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
		this.patient = patient;
	}
	
	//Getters y Setters

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getQuantity() {
		return qty;
	}
	
	public void setQuantity(int quantity) {
		this.qty=quantity;
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
	
	public int getPatient() {
		return patient;
	}
	
	public void setPatient(int p) {
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
		return "\nPrescription Id: " + id + "\n" + 
	"Quantity of medicines: "+ qty + "\n" +
	"Issue date: " + issueDate + "\n"; }

	@Override
	public int hashCode() {
		return Objects.hash(dateUsed, id, issueDate, medicines, patient, qty);
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
				&& qty == other.qty;
	}

	
	
	
	
}