package criptoLogin.com.login.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import criptoLogin.com.login.initialization.FactoryManager;


public class PersistenceManager
{

	private static EntityManager persistence = FactoryManager.getEntityManager();
	
	public static void Persist(Object obj)
	{
		persistence.getTransaction().begin();
		persistence.persist(obj);
//		persistence.flush();	
		persistence.getTransaction().commit();
	}
	
	public static Object getObjectbyId(Object obj, Long id)
	{
		return persistence.find(obj.getClass(), id);
	}
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjects(@SuppressWarnings("rawtypes") Class obj) 
	{
		StringBuilder hql = new StringBuilder();
		hql.append("FROM "+ obj.getSimpleName());
		Query query = persistence.createQuery(hql.toString());
		return query.getResultList();
	}
	public static EntityManager getEntityManager()
	{
		return persistence;
	}
}
