package pharmacy.db.pojos;

import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.io.Serializable;


@XmlAccessorType(XmlAccessType.FIELD)

public class Medicine implements Serializable {

	private static final long serialVersionUID = -965036009312790930L;
	
	@XmlElement
	private String name;
	@XmlTransient
	private int numAsigned;
	@XmlTransient
	private Prescription prescription;
	@XmlTransient
	private Laboratory laboratory;
	@XmlElement
	private List<Stock> stock;
	@XmlTransient
	private List<Prescription> prescriptions;
	private int quantity;


	public Medicine() {
		super();
		this.stock = new ArrayList<Stock>();
		this.prescriptions = new ArrayList<Prescription>();
		this.quantity = quantity;
	}
	
	public Medicine(String name, int numAsigned) {
		this.name=name;
		this.numAsigned= numAsigned;
		this.stock = new ArrayList<Stock>();
		this.prescriptions = new ArrayList<Prescription>();
		this.quantity = quantity;
	}
	
	public Medicine(String name, int numAsigned, Prescription prescription, Laboratory laboratory,
			List<Stock> stock) {
		super();
		this.name = name;
		this.numAsigned = numAsigned;
		this.prescription = prescription;
		this.laboratory = laboratory;
		this.stock = new ArrayList<Stock>();
		this.prescriptions = new ArrayList<Prescription>();
		this.quantity = quantity;
		
	}


	public Medicine(String name, int numAsigned, int quantity) {
		super();
		this.name = name;
		this.numAsigned = numAsigned;
		this.quantity = quantity;
		this.stock = new ArrayList<Stock>();
		this.prescriptions = new ArrayList<Prescription>();
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
	

	public List<Prescription> getBorrowersPrescriptions() {
		return prescriptions;
	}

	public void setBorrowers(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}


	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(laboratory, name, numAsigned, prescription, prescriptions, stock, quantity);
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
				&& numAsigned == other.numAsigned && Objects.equals(prescription, other.prescription)
				&& Objects.equals(prescriptions, other.prescriptions) && Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "Medicine" + "\n" + 
	name + ", id: " + numAsigned + "\n"+
	"stock: " + quantity;
	}


	
}
 