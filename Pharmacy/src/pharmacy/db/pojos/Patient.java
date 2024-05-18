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
	private String sex;
	private String userName;
	
	public Patient() {
		super();
	}

	
	public Patient(Integer id, String name, Date dateOfBirth, String sex, String userName) {
		super();
		this.id=id;
		this.name=name;
		this.dateOfBirth=dateOfBirth;
		this.sex= sex;
		this.userName = userName;
		
	}


	public Patient(String userName) {
		super();
		this.userName = userName;
	}


	public Patient(String name2, Date dateOfBirth2, String sex2, String username2) {
		super();
		this.name=name2;
		this.dateOfBirth=dateOfBirth2;
		this.sex= sex2;
		this.userName = username2;
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

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, id, name, sex, userName);
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
				&& Objects.equals(name, other.name) && Objects.equals(sex, other.sex)
				&& Objects.equals(userName, other.userName);
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", userName="
				+ userName + "]";
	}


	
	
	
}
