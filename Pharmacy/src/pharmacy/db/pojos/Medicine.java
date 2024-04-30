package pharmacy.db.pojos;

import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;

public class Medicine implements Serializable {

	private String name;
	private int numAsigned;
	private Prescription prescription;
	private Laboratory laboratory;
	

	public Medicine() {
		super();
	}
	
	public Medicine(String name, int numAsigned){
	
		this.name= name;
		this.numAsigned=numAsigned;
		
	}
	
	//Getters y Setters
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getNumAsigned(){
		return numAsigned;
	}
	
	public void setNumAsigned(int numAsigned) {
		this.numAsigned=numAsigned;
	}
	
	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(laboratory, name, numAsigned, prescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		return Objects.equals(laboratory, other.laboratory) && Objects.equals(name, other.name)
				&& numAsigned == other.numAsigned && Objects.equals(prescription, other.prescription);
	}

	@Override
	public String toString() {
		return "Medicine [name=" + name + ", numAsigned=" + numAsigned + ", prescription=" + prescription
				+ ", laboratory=" + laboratory + "]";
	}

	
	
	
}
 