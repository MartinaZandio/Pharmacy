package pharmacy.db.pojos;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "roles")

public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6717476836266445889L;
	@Id
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@JoinColumn
	private List<User> users;
	
	public Role() {
		super();
		this.users=new ArrayList<User>();
	}
	
	
	public Role(String name) {
		super();
		this.name = name;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	//Methods added
	public void addUser(User u) {
		if (!users.contains(u)) {
			users.add(u);
		}
	}
	
	public void removeUser(USer u) {
		if(users.contains(u))
			users.remove();
	}
}
