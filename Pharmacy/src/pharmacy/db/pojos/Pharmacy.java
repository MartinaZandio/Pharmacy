package pharmacy.db.pojos;

import java.util.*;
import java.io.Serializable;


public class Pharmacy implements Serializable{
	
	String name; 
	String location; 
	Integer postalCode; 
	Integer numberOfWorkers;
	private ArrayList<Stock> stock;
	
	public Pharmacy() {
		super();
		this.stock = new ArrayList<Stock>();

	}
	
	public Pharmacy(String name, String location, int postalCode, int numberOfWorkers) {
		super();
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
		this.numberOfWorkers = numberOfWorkers;
		this.stock = new ArrayList<Stock>();

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	public Integer getNumberOfWorkers() {
		return numberOfWorkers;
	}
	public void setNumberOfWorkers(int numberOfWorkers) {
		this.numberOfWorkers = numberOfWorkers;
	}

	public ArrayList<Stock> getStock() {
		return stock;
	}

	public void setStock(ArrayList<Stock> stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, name, numberOfWorkers, postalCode, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pharmacy other = (Pharmacy) obj;
		return Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(numberOfWorkers, other.numberOfWorkers)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "Pharmacy [name=" + name + ", location=" + location + ", postalCode=" + postalCode + ", numberOfWorkers="
				+ numberOfWorkers + ", stock=" + stock + "]";
	}

	
	
}