package pharmacy.db.interfaces;

import java.util.*;

import pharmacy.db.pojos.*;

public interface UserManager {

	public void register(User u);
	public void createRole(Role r);
	public Role getRole(String name);
	public List<Role> getAllRoles();
	public void assignRole(User u, Role r); 
	public User login(String userName, String password); //throw UserNotFoundException() or return NULL if there is no user;
	
}
