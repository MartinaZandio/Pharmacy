package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.*;

public class Stock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5309087422405888889L;
	private Pharmacy pharmacy;
	private Medicine medicine;
	private List<Stock> stock;
	private int amount;
	
	public Stock() {
		super();
		this.stock = new ArrayList<Stock>();
	}
	public Stock(Pharmacy pharmacy, Medicine medicine, List<Stock> stock, int amount) {
		super();
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.stock = new ArrayList<Stock>();
		this.amount= amount;
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
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, medicine, pharmacy, stock);
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
		return amount == other.amount && Objects.equals(medicine, other.medicine)
				&& Objects.equals(pharmacy, other.pharmacy) && Objects.equals(stock, other.stock);
	}
	@Override
	public String toString() {
		return "Stock [pharmacy=" + pharmacy + ", medicine=" + medicine + ", stock=" + stock + ", Amount=" + amount
				+ "]";
	}
	
	
	


}
