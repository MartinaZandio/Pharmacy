package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2388486227120532414L;
	@Id
	@GeneratedValue(generator="users")
	@TableGenerator (name= "users", table= "sqlite_sequence",
			pkColumnName= "name", valueColumnName= "seq", pkColumnValue= "users")
	private int id;
	@Column(nullable = false, unique =true)
	private String userName;
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	public User(String username2, String password2, String roleName) {
		super();
	}
	
	
		
	public User(String userName, String password, Role role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public User() {
		super();
	}



	public User(int id, String userName, String password, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}



	@Override
	public String toString() {
		return "User " + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
	
}
