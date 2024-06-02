package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

public class Stock implements Serializable{

	private static final long serialVersionUID = 4731831667008411270L;
	@XmlTransient
	private Pharmacy pharmacy;
	@XmlElement
	private Medicine medicine;
	@XmlTransient
	private int amount;
	
	public Stock() {
		super();
	}
	
	public Stock(Pharmacy pharmacy, Medicine medicine, List<Stock> stock, int amount) {
		super();
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.amount= amount;
	}
	
	public Stock(int amount) {
		super();
		this.amount=amount;
	}
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return amount == other.amount;
	}

	@Override
	public String toString() {
		return "\nStock [pharmacy=" + pharmacy + ", medicine=" + medicine + ", amount=" + amount
				+ "]\n";
	}
	
	
	


}
