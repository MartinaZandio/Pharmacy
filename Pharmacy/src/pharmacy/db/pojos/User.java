package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2388486227120532414L;
	@Id
	@GeneratedValue(generator="users")
	@TableGenerator (name= "users", table= "sqlite_sequence,"
			pkColumn= "")
	private int id;
	private String userName;
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
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
		return Objects.hash(id, password, role, userName);
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
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

	
	
	
}
