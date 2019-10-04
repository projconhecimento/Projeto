package criptoLogin.com.login.initialization;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager
{
	private static EntityManager entityManager;

	public static void IniciateEntityFactory()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = emf.createEntityManager();
		FactoryManager fac = new FactoryManager();
		fac.setEntityManager(em);
	}

	public static EntityManager getEntityManager()
	{
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager)
	{
		FactoryManager.entityManager = entityManager;
	}
	
}
