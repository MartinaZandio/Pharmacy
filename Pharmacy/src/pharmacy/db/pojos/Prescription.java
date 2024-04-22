	
	package pharmacy.db.pojos;
	import java.util.Objects;
	import java.io.Serializable;

	public class Prescription implements Serializable{


	private int quantity;
	private String issueDate;
	private String dateUsed;
	
	public Prescription(int quantity, String issueDate, String dateUsed){
		
		this.quantity=quantity;
		this.issueDate=issueDate;
		this.dateUsed=dateUsed;
		
	}
	
	//Getters y Setters
	
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

	@Override
	public String toString() {
		return "Prescription [quantity=" + quantity + ", issueDate=" + issueDate + ", dateUsed=" + dateUsed + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateUsed, issueDate, quantity);
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
		return Objects.equals(dateUsed, other.dateUsed) && Objects.equals(issueDate, other.issueDate)
				&& quantity == other.quantity;
	}
	
	
	
}