package pharmacy.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Table(name= "patients")
public class Patient implements Serializable{
	
	private static final long serialVersionUID1 = 3668403771940551440L;

	
	@GeneratedValue(generator="patients")
	//...
	@Id
	private Integer id;
	private String name;
	private Date dateOfBirth;
<<<<<<< HEAD

	public enum gender{MALE, FEMALE}

	public enum gender{MALE, FEMALE};

	gender valueOf(String string) {
		// TODO Auto-generated method stub
		return null;
	}
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	private gender sex;
=======
	private String sex;
>>>>>>> branch 'master' of https://github.com/MartinaZandio/Pharmacy
	
	public Patient() {
		super();
	}

	
	public Patient(Integer id, String name, Date dateOfBirth, String sex) {
		super();
		this.id=id;
		this.name=name;
		this.dateOfBirth=dateOfBirth;
		this.sex= sex;
		
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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
		return "Patient [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", Gender=" + sex + "]";
	}
	
	
}
