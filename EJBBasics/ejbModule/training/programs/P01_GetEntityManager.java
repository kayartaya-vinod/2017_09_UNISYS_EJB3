package training.programs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P01_GetEntityManager {

	public static void main(String[] args) {
		
		EntityManagerFactory emf;
		EntityManager em;
		
		emf = Persistence.createEntityManagerFactory("TrainingPU");
		em = emf.createEntityManager();
		
		System.out.println("Got an instanceof " + em.getClass());
		
		em.close();
		emf.close();
		
	}
}
