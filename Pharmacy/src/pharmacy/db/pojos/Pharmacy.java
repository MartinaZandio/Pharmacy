package pharmacy.db.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Pharmacy {
	
	String name; 
	String location; 
	Integer postalCode; 
	Integer numberOfWorkers;
	
	public Pharmacy() {
		super();
	}
	
	public Pharmacy(String name, String location, int postalCode, int numberOfWorkers) {
		super();
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
		this.numberOfWorkers = numberOfWorkers;
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

	@Override
	public int hashCode() {
		return Objects.hash(location, name, numberOfWorkers, postalCode);
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
				&& numberOfWorkers == other.numberOfWorkers && postalCode == other.postalCode;
	}

	@Override
	public String toString() {
		return "Pharmacy [name=" + name + ", location=" + location + ", postalCode=" + postalCode + ", numberOfWorkers="
				+ numberOfWorkers + "]";
	}
	
}