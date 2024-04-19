package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = 3668403771940551440L;
	private Integer id;
	private String name;
	private String dateOfBirth;
	private enum gender{MALE, FEMALE}
	private gender sex;
	
	public Patient() {
		super();
	}

	
	public Patient(Integer id, String name, String dateOfBirth, gender sex) {
		super();
		this.id=id;
		this.name=name;
		this.dateOfBirth=dateOfBirth;
		this.sex=gender.MALE;
		
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public gender getSex() {
		return sex;
	}

	public void setSex(gender sex) {
		this.sex = sex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, id, name, sex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && sex == other.sex;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + "]";
	}
	
	
}
