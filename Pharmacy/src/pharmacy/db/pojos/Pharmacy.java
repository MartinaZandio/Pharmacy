package pharmacy.db.pojos;

import java.util.*;

import javax.xml.bind.annotation.*;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Pharmacy") 
@XmlType(propOrder = { "name", "location", "postalCode", "stock" })

public class Pharmacy implements Serializable{
	
	private static final long serialVersionUID = -4622538807700766019L;
	
	@XmlTransient
	private int id;
	@XmlElement
	private String name; 
	@XmlElement
	private String location; 
	@XmlElement
	private Integer postalCode; 
	@XmlAttribute
	private Integer numberOfWorkers;
	@XmlElement
	@XmlElementWrapper(name = "stocks")
	private List<Stock> stock;
	
	public Pharmacy() {
		super();
		this.stock = new ArrayList<Stock>();

	}
	
	public Pharmacy(int id, String name, String location, int postalCode, int numberOfWorkers) {
		super();
		this.id=id;
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
		this.numberOfWorkers = numberOfWorkers;
		this.stock = new ArrayList<Stock>();
	}
	

	public Pharmacy(int id, String name, String location, int postalCode) {
		super();
		this.id=id;
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
		this.stock = new ArrayList<Stock>();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(ArrayList<Stock> stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, location, name, numberOfWorkers, postalCode, stock);
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
		return id == other.id && Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(numberOfWorkers, other.numberOfWorkers)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(stock, other.stock);
	}
	
	@Override
	public String toString() {
		return "Pharmacy" + "\n" +
	"Id: " + id + "\n" +
	"Name: " + name + "\n" +
	"Location: " + location + "\n" +
	"Postal code: " + postalCode + "\n";
	}	
}