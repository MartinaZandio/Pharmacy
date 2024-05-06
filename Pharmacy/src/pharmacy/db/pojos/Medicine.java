package pharmacy.db.pojos;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Objects;
=======

import java.util.ArrayList;
import java.util.Objects;
import java.util.*;

import pharmacy.db.pojos.*;
import java.io.Serializable;

public class Medicine implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String name;
	private int numAsigned;
	private Prescription prescription;
	private Laboratory laboratory;
	private ArrayList<Stock> stock;	
	private ArrayList<Prescription> prescriptions;


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
	

	public ArrayList<Prescription> getBorrowersPrescriptions() {
		return prescriptions;
	}

	public void setBorrowers(ArrayList<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}


	public ArrayList<Stock> getStock() {
		return stock;
	}
	public void setStock(ArrayList<Stock> stock) {
		this.stock = stock;
	}



	@Override
	public int hashCode() {

		return Objects.hash(laboratory, name, numAsigned, prescription);
		return Objects.hash(laboratory, name, numAsigned, prescription, prescriptions, stock);
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
				&& numAsigned == other.numAsigned && Objects.equals(prescription, other.prescription)
				&& Objects.equals(prescriptions, other.prescriptions) && Objects.equals(stock, other.stock);

	}

	@Override
	public String toString() {
		return "Medicine [name=" + name + ", numAsigned=" + numAsigned + ", prescription=" + prescription
				+ ", laboratory=" + laboratory + "]";
	}



	@Override
	public String toString() {
		return "Medicine [name=" + name + ", numAsigned=" + numAsigned + ", prescription=" + prescription
				+ ", laboratory=" + laboratory + ", stock=" + stock + ", prescriptions=" + prescriptions + "]";
	}

	
	
	
}
 