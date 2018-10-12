package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.User;

/**
 * 
 * @author <a href="https://github.com/JoseMiguez98/">Jose Miguez</a>
 *
 */
public class UserDAO implements DAO<User, Integer> {
	public static UserDAO daoUser;

	public static UserDAO getInstance() {
		if(daoUser==null) {
			daoUser = new UserDAO();
		}
		return daoUser;
	}

	@Override
	public User persist(User entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return entity;
	}

	/** Update an existent user or create if not exists
	 * 
	 * 	@return Updated/Created User
	 */
	@Override
	public User update(Integer id, User updatedUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		try {
			if(user!=null) {
				user.setName(updatedUser.getName());
				user.setKnowledge(updatedUser.getKnowledge());
				user.setQualification();
			}
			else {
				em.persist(updatedUser);
			}
		}
		finally {
			em.flush();
			em.getTransaction().commit();
			em.close();
			emf.close();
		}
		return updatedUser;
	}

	@Override
	public User findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, id);
		em.close();
		return user;
	}

	@Override
	public List<User> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		TypedQuery<User>query = em.createQuery("Select u from User u", User.class);
		List<User>users = query.getResultList();
		return users;
	}

	@Override
	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		try {
			em.remove(user);
		}
		finally {
			em.getTransaction().commit();
			em.close();
			emf.close();
		}
		return user!=null;
	}
}
