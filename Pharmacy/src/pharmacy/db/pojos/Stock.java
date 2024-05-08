package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Stock implements Serializable{
	private Pharmacy pharmacy;
	private Medicine medicine;
	private ArrayList<Stock> stock;
	private int Amount;
	
	public Stock() {
		super();
		this.stock = new ArrayList<Stock>();
	}
	public Stock(Pharmacy pharmacy, Medicine medicine, ArrayList<Stock> stock, int Amount) {
		super();
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.stock = new ArrayList<Stock>();
		this.Amount= Amount;
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
	public ArrayList<Stock> getStock() {
		return stock;
	}
	public void setStock(ArrayList<Stock> stock) {
		this.stock = stock;
	}
	
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Amount, medicine, pharmacy, stock);
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
		return Amount == other.Amount && Objects.equals(medicine, other.medicine)
				&& Objects.equals(pharmacy, other.pharmacy) && Objects.equals(stock, other.stock);
	}
	@Override
	public String toString() {
		return "Stock [pharmacy=" + pharmacy + ", medicine=" + medicine + ", stock=" + stock + ", Amount=" + Amount
				+ "]";
	}
	
	
	


}
