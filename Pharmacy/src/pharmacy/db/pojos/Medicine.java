package pharmacy.db.pojos;

import java.util.Objects;
import java.io.Serializable;

public class Medicine implements Serializable {

	private String name;
	private int numAsigned;
	private Laboratory laboratory;
	//list de pharmacys??
	
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
	
	@Override
	public String toString() {
		String texto="";
		texto+="Name: " + name;
		texto+="\nNumber Asigned: " + numAsigned;
		return texto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, numAsigned);
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
		return Objects.equals(name, other.name) && numAsigned == other.numAsigned;
	}
	
	
	
}
 