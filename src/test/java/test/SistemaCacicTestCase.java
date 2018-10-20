/**
 * 
 */
package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Project;
import entities.User;
import junit.framework.TestCase;
import sistemaCacic2018.SistemaCacic;

/**
 * @author jose
 *
 */
public class SistemaCacicTestCase extends TestCase {
	public static String[]temas_generales = new String[]{"ProgrammingLanguages","Algorithms","Maths","Algebra","IA","Heuristics","JPA"};
	public static String[]temas_expertos = new String[] {"Java","MySQL","C++","Assembly","Dijkstra","DeepLearning","MachineLearning","WebDevelop","Forensic","ImageProcessing","ObjectOrientedProgramming"};

	private	EntityManagerFactory emf;
	private	EntityManager em;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@BeforeClass
	protected void before() throws Exception {
		emf = Persistence.createEntityManagerFactory("ArquiTPEspecial");
		em = emf.createEntityManager();
	}

	@Before
	/** Test Crear usuarios (10 usuarios)
	 * 	Test Crear trabajos de investigación (10 trabajos de investigación).
	 */
	protected void setUp() throws Exception{
		User walter = new User();
		User jesse = new User();
		User gus = new User();
		User mike = new User();
		User saul = new User();
		User hank = new User();
		User tuco = new User();
		User hector = new User();
		User badger = new User();
		User skinny = new User();

		Project security = new Project();//1
		Project ia = new Project();//1
		Project arduino = new Project();
		Project imageProcessing = new Project();
		Project ux = new Project();//2
		Project methCook = new Project();
		Project deepLearning = new Project();
		Project soundProcessing = new Project();
		Project dataMining = new Project();
		Project cloud = new Project();//1

		walter.switchEvaluator();
		jesse.switchEvaluator();
		jesse.switchAuthor();
		mike.switchAuthor();
		mike.switchEvaluator();
		hank.switchEvaluator();


		walter.setName("Walter White");
		walter.addKnowledge(temas_generales[0], temas_generales, temas_expertos);
		walter.addKnowledge(temas_expertos[0], temas_generales, temas_expertos);
		walter.addKnowledge(temas_generales[3], temas_generales, temas_expertos);
		walter.addKnowledge(temas_generales[2], temas_generales, temas_expertos);
		walter.setQualification();
		walter.addRevision(ia, "03/07/2016");
		walter.addRevision(ux, "04/12/2016");

		jesse.setName("Jesse pinkman");
		jesse.addKnowledge(temas_generales[2], temas_generales, temas_expertos);
		jesse.addKnowledge(temas_generales[1], temas_generales, temas_expertos);
		jesse.addKnowledge(temas_generales[5], temas_generales, temas_expertos);
		jesse.addKnowledge(temas_generales[3], temas_generales, temas_expertos);
		jesse.setQualification();
		//Se agrega por que ux es Poster tiene conocimiento de 1 de los temas
		jesse.addRevision(ux, "02/01/2019");
		//Se agrega los 2 por que posee conocimientos en todos los temas
		jesse.addRevision(security, "05/02/2020");
		//No se agrega por que no posee conocimiento en todos los temas
		jesse.addRevision(methCook, "06/01/2015");
		//No se agrega por que es el autor del mismo
		jesse.addRevision(arduino, "01/05/2019");

		gus.setName("Gustavo Fring");
		gus.addKnowledge(temas_expertos[0], temas_generales, temas_expertos);
		gus.addKnowledge(temas_expertos[4], temas_generales, temas_expertos);
		gus.setQualification();
		//No se agregan por que no es evaluador
		gus.addRevision(deepLearning, "06/01/2015");
		gus.addRevision(cloud, "01/05/2019");

		mike.setName("Mike Ermanthraut");
		//No se agregara ya que no es un conocimiento valido
		mike.addKnowledge("Dance", temas_generales, temas_expertos);
		mike.addKnowledge(temas_expertos[2], temas_generales, temas_expertos);
		mike.addKnowledge(temas_expertos[3], temas_generales, temas_expertos);
		mike.addKnowledge(temas_expertos[1], temas_generales, temas_expertos);
		mike.addKnowledge(temas_generales[1], temas_generales, temas_expertos);
		mike.setQualification();
		//Se agrega
		mike.addRevision(cloud, "02/03/2020");
		
		saul.setName("Saul Goodman");
		saul.addKnowledge(temas_expertos[2], temas_generales, temas_expertos);
		saul.addKnowledge(temas_expertos[1], temas_generales, temas_expertos);
		saul.addKnowledge(temas_generales[2], temas_generales, temas_expertos);
		saul.addKnowledge(temas_expertos[4], temas_generales, temas_expertos);
		saul.setQualification();
		saul.addRevision(soundProcessing, "04/08/2017");
		saul.addRevision(imageProcessing, "15/07/2020");
		saul.addRevision(dataMining, "08/09/2017");
		
		hank.setName("Hank Schrader");
		hank.addKnowledge(temas_generales[2], temas_generales, temas_expertos);
		hank.addKnowledge(temas_generales[1], temas_generales, temas_expertos);
		hank.addKnowledge(temas_generales[4], temas_generales, temas_expertos);
		
	}

	@Test
	protected void testCreateTenUsers() {

	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		SistemaCacic.deleteAllData(em);
	}

	@AfterClass
	protected void after() throws Exception{
		emf.close();
		em.close();
	}
}
