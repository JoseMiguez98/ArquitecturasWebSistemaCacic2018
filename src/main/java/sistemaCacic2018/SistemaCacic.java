package sistemaCacic2018;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.ProjectDAO;
import dao.UserDAO;
import entities.Project;
import entities.User;
/**
 * 
 * @author <a href="https://github.com/JoseMiguez98/">Jose Miguez</a>
 *
 */
public class SistemaCacic {
	
	public static String[]temas_generales = new String[]{"ProgrammingLanguages","Algorithms","Maths","Algebra","IA","Heuristics","JPA"};
	public static String[]temas_expertos = new String[] {"Java","MySQL","C++","Assembly","Dijkstra","DeepLearning","MachineLearning","WebDevelop","Forensic","ImageProcessing","ObjectOrientedProgramming"};
	
	public static void main(String[] args) throws ParseException {
		User jose = new User();
		User belu = new User();
		User enzo = new User();
//		User plaga = new User();
//		User saul = new User();
//		User batman = new User();
		Project arqui = new Project();
		Project ux = new Project();
		Project math = new Project();
		Project cacic = new Project();
//		Project security = new Project();
//		Project ia = new Project();
		Project arduino = new Project();
		//-------SwitchingAuthors-----------//
		jose.switchAuthor();
		belu.switchAuthor();
		enzo.switchAuthor();
		//-------ProjectsProperties-------//
		arqui.setCategory("Arquitecturas");
		arqui.addTopic("JPA");
		arqui.addTopic("Java");
		arqui.addTopic("Hibernate");
		arqui.addTopic("Jersey");
		arqui.setName("Arquitecturas Web");
		arqui.setAuthor(belu);
		
		//Tiene que quedar "Invalid Category"
		ux.setCategory("Poster");
		ux.addTopic("UI");
		ux.addTopic("UX");
		ux.addTopic("Heuristicas");
		ux.addTopic("Nielsen");
		ux.addTopic("Usabilidad");
		ux.addTopic(temas_expertos[2]);
		ux.setName("Interfaces de usuario");
		ux.setAuthor(jose);
		
		math.setName("Math");
		math.setCategory("Summary");
		math.addTopic("POW");
		math.addTopic("Numbers");
		math.addTopic("Sum");
		math.setAuthor(enzo);
		
		cacic.setName("CACIC2018");
		cacic.setCategory("Article");
		cacic.addTopic(temas_generales[4]);
		cacic.addTopic(temas_generales[1]);
		cacic.addTopic(temas_generales[6]);
		cacic.addTopic(temas_expertos[2]);
		cacic.setAuthor(jose);
		
		arduino.setName("Arduino");
		arduino.setCategory("Article");
		arduino.addTopic(temas_generales[6]);
		arduino.addTopic(temas_generales[1]);
		arduino.addTopic(temas_generales[4]);
		arduino.addTopic(temas_expertos[1]);
		arduino.setAuthor(belu);
		//---------------------------------//
		
		//--------UsersProperties---------//
		jose.switchEvaluator();
		jose.setName("Jose");
		jose.addKnowledge("ProgrammingLanguages",temas_generales,temas_expertos);
		jose.addKnowledge("Java",temas_generales,temas_expertos);
		jose.addKnowledge("MySQL",temas_generales,temas_expertos);
		jose.addKnowledge("JPA",temas_generales,temas_expertos);
		jose.addKnowledge(temas_expertos[6],temas_generales,temas_expertos);
		jose.addKnowledge("WebDevelopment",temas_generales,temas_expertos);
		jose.setQualification();
		jose.addRevision(arqui,"04/05/2018");
		jose.addRevision(cacic,"04/05/2018");
		
		belu.switchEvaluator();
		belu.setName("Belu");
		belu.addKnowledge("Dance",temas_generales,temas_expertos);
		belu.addKnowledge(temas_generales[5],temas_generales,temas_expertos);
		belu.addKnowledge(temas_generales[2],temas_generales,temas_expertos);
		belu.addKnowledge("Psicology",temas_generales,temas_expertos);
		belu.addKnowledge("Music",temas_generales,temas_expertos);
		belu.setQualification();
		belu.addRevision(ux,"04/05/2018");
		belu.addRevision(math,"04/05/2018");
		
		enzo.switchEvaluator();
		enzo.setName("Enzo");
		enzo.addKnowledge(temas_generales[4], temas_generales, temas_expertos);
		enzo.addKnowledge(temas_generales[1], temas_generales, temas_expertos);
		enzo.addKnowledge(temas_generales[6], temas_generales, temas_expertos);
		enzo.addKnowledge(temas_expertos[2], temas_generales, temas_expertos);
		enzo.addKnowledge(temas_expertos[6], temas_generales, temas_expertos);
		enzo.setQualification();
		//No deberia agregarse ya que no posee conocimiento en todos los temas que abarca el proyecto
		enzo.addRevision(arduino,"02/07/2020");
		//Deberia agregarse ya que ux es poster y enzo posee conocimiento solo en 1 tema del proyecto
		enzo.addRevision(ux,"03/11/2019");
		//No deberia agregarse ya que es el autor de la misma
		enzo.addRevision(math,"03/06/2020");
		//Deberia agregarse ya que posee conocimiento en todos los temas
		enzo.addRevision(cacic,"04/01/2018");
		
		UserDAO.getInstance().persist(jose);
		UserDAO.getInstance().persist(belu);
		UserDAO.getInstance().persist(enzo);
		
		ProjectDAO.getInstance().persist(ux);
		ProjectDAO.getInstance().persist(arduino);
		
//		em.persist(ux);
//		em.persist(arduino);
//		em.persist(jose);
//		em.persist(belu);
//		em.persist(enzo);
		
		//-------------------------------------//
	}
}
