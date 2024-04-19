package pharmacy.db.pojos;

import java.util.Objects;

public class Laboratory {
	private String name; 
	private Integer postalCode; 
	private String location;

	public Laboratory() {
			super();
	 }

	public Laboratory(String name, String location, Integer postalCode) {
		
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "Laboratory [name=" + name + ", postalCode=" + postalCode + ", location=" + location + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(location, name, postalCode);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laboratory other = (Laboratory) obj;
		return Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(postalCode, other.postalCode);
	}
	
	
}
