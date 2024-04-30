package pharmacy.db.pojos;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Objects;
=======

import java.util.ArrayList;
import java.util.Objects;
import java.util.*;

import pharmacy.db.pojos.Prescription;
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
import java.io.Serializable;

public class Medicine implements Serializable {

	private String name;
	private int numAsigned;
	private Prescription prescription;
<<<<<<< HEAD
	private Laboratory laboratory;
	
=======
	private Laboratory laboratory;
	private ArrayList<Stock> stock;	
	private ArrayList<Prescription> prescriptions;
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy


	public Medicine() {
		super();
	}
	
	
	
	public Medicine(String name, int numAsigned, Prescription prescription, Laboratory laboratory,
			ArrayList<Stock> stock) {
		super();
		this.name = name;
		this.numAsigned = numAsigned;
		this.prescription = prescription;
		this.laboratory = laboratory;
		this.stock = stock;
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
<<<<<<< HEAD
=======
	

	public ArrayList<Prescription> getBorrowersPrescriptions() {
		return prescriptions;
	}

	public void setBorrowers(ArrayList<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}


	public ArrayList<Stock> getStock() {
		return stock;
	}
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy

	public void setStock(ArrayList<Stock> stock) {
		this.stock = stock;
	}



	@Override
	public int hashCode() {
<<<<<<< HEAD
		return Objects.hash(laboratory, name, numAsigned, prescription);
=======
		return Objects.hash(laboratory, name, numAsigned, prescription, prescriptions, stock);
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
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
<<<<<<< HEAD
				&& numAsigned == other.numAsigned && Objects.equals(prescription, other.prescription);
=======
				&& numAsigned == other.numAsigned && Objects.equals(prescription, other.prescription)
				&& Objects.equals(prescriptions, other.prescriptions) && Objects.equals(stock, other.stock);
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Medicine [name=" + name + ", numAsigned=" + numAsigned + ", prescription=" + prescription
				+ ", laboratory=" + laboratory + "]";
=======


	@Override
	public String toString() {
		return "Medicine [name=" + name + ", numAsigned=" + numAsigned + ", prescription=" + prescription
				+ ", laboratory=" + laboratory + ", stock=" + stock + ", prescriptions=" + prescriptions + "]";
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	}

	
	
	
}
 