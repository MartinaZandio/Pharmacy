package pharmacy.db.pojos;

import java.util.Objects;
import java.io.Serializable;

public class Laboratory implements Serializable {
	private Integer id;
	private String name; 
	private Integer postalCode; 
	private String location;

	public Laboratory() {
			super();
	 }

	public Laboratory(Integer id, String name, String location, Integer postalCode) {
		
		this.id = id;
		this.name = name;
		this.location = location;
		this.postalCode = postalCode;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public int hashCode() {
		return Objects.hash(id, location, name, postalCode);
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
		return Objects.equals(id, other.id) && Objects.equals(location, other.location)
				&& Objects.equals(name, other.name) && Objects.equals(postalCode, other.postalCode);
	}

	@Override
	public String toString() {
		return "Laboratory [id=" + id + ", name=" + name + ", postalCode=" + postalCode + ", location=" + location
				+ "]";
	}


	
	
}
