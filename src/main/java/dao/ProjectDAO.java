package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Project;

public class ProjectDAO implements DAO<Project, Integer>{
	private static ProjectDAO daoProject;
	
	public static ProjectDAO getInstance() {
		if(daoProject==null) {
			daoProject = new ProjectDAO();
		}
		return daoProject;
	}
	
	@Override
	public Project persist(Project entity) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return entity;
	}

	@Override
	public Project update(Integer id, Project updatedProject) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		try {
			if(project!=null) {
				project.setAuthor(updatedProject.getAuthor());
				project.setCategory(updatedProject.getCategory());
				project.setName(updatedProject.getName());
				project.setTopics(updatedProject.getTopics());
			}
			else {
				em.persist(updatedProject);
			}
		}
		finally {
			em.flush();
			em.getTransaction().commit();
			em.close();
			emf.close();
		}
		return updatedProject;
	}

	@Override
	public Project findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		Project project = em.find(Project.class, id);
		em.close();
		return project;
	}

	@Override
	public List<Project> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Project>query = em.createQuery("Select p from Project p", Project.class);
		List<Project>projects = query.getResultList();
		return projects;
	}

	@Override
	public boolean delete(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		try {
			em.remove(project);
		}
		finally {
			em.getTransaction().commit();
			em.close();
			emf.close();
		}
		return project!=null;
	}
}
