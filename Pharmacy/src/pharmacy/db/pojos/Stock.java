package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Stock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5309087422405888889L;
	private Pharmacy pharmacy;
	private Medicine medicine;
	private ArrayList<Stock> stock;
	
	public Stock() {
		super();
	}
	public Stock(Pharmacy pharmacy, Medicine medicine, ArrayList<Stock> stock) {
		super();
		this.pharmacy = pharmacy;
		this.medicine = medicine;
		this.stock = stock;
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
	@Override
	public int hashCode() {
		return Objects.hash(medicine, pharmacy, stock);
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
		return Objects.equals(medicine, other.medicine) && Objects.equals(pharmacy, other.pharmacy)
				&& Objects.equals(stock, other.stock);
	}
	@Override
	public String toString() {
		return "Stock [pharmacy=" + pharmacy + ", medicine=" + medicine + ", stock=" + stock + "]";
	}
	
	
	
	


}
