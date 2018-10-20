package dao;

import java.util.ArrayList;
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
	public Project persist(Project entity, EntityManager em) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public Project update(Integer id, Project updatedProject, EntityManager em) {
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
		}
		return updatedProject;
	}

	@Override
	public Project findById(Integer id, EntityManager em) {
		Project project = em.find(Project.class, id);
		return project;
	}

	@Override
	public List<Project> findAll(EntityManager em) {
		TypedQuery<Project>query = em.createQuery("Select p from Project p", Project.class);
		List<Project>projects = query.getResultList();
		return projects;
	}

	@Override
	public boolean delete(Integer id, EntityManager em) {
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		try {
			em.remove(project);
		}
		finally {
			em.getTransaction().commit();
		}
		return project!=null;
	}
	
	/**
	 * Consultar trabajos de investigación y sus propiedades.
	 * @param em
	 * @return List with all works properties
	 */
	public List<String>getAllResearchWorksInfo(EntityManager em){
		List<String>worksInfo = new ArrayList<String>();
		List<Project>projects = this.findAll(em);
		
		for(Project p : projects) {
			worksInfo.add(p.toString());
		}
		
		return worksInfo;
	}
}
