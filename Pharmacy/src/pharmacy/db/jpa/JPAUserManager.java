package pharmacy.db.jpa;

import java.util.*;

import javax.persistence.*;

import pharmacy.db.interfaces.UserManager;
import pharmacy.db.pojos.Role;
import pharmacy.db.pojos.User;

public class JPAUserManager implements UserManager {

	private EntityManager em;

	public JPAUserManager() {
		em=Persistence.createEntityManagerFactory("pharmacy-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		//Create default roles
		//if they don't exist already
		try {
			this.getRole("pharmacy");
		} catch(NoResultException e) {
			this.createRole(new Role ("pharmacy"));
			this.createRole(new Role ("patient"));
		}
		//if(this.getRole("pharmacy") == null) {
		//this.createRole(new Role ("pharmacy"));
		//this.createRole(new Role ("patient"));
		//}
	}
	
	@Override
	public void register(User u) {
		em.getTransaction().begin();  //before every change
		em.persist(u);
		em.getTransaction().commit();  //after every change

	}

	@Override
	public void createRole(Role r) {
		em.getTransaction().begin();  //before every change
		em.persist(r);
		em.getTransaction().commit();  //after every change

	}

	@Override
	public Role getRole(String name) {
		Query q= em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r= (Role) q.getSingleResult();
		return r;
	}
	
	public List<Role> getAllRoles(){
		Query q= em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public void assignRole(User u, Role r) {
		em.getTransaction().begin();
		u.setRole(r);
		r.addUser(u);
		em.getTransaction().commit();
	}

	@Override
	public User login(String userName, String password) {
		User u;
		Query q= em.createNativeQuery("SELECT * FROM users WHERE userName = ? AND password = ?", User.class);
		q.setParameter(1,userName);
		q.setParameter(2, password);
		try{
			u = (User) q.getSingleResult(); //Cast because you get an object
		}catch (NoResultException e) {
			return null;
		}
		return u;

	}

}