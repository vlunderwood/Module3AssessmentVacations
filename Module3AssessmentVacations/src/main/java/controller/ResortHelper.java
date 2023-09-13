
/**
 * @author valei - vlunderwood
 * CIS175 - Fall 2023
 * Sep 11, 2023
 */
package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.ListResort;


public class ResortHelper {
	//EntityManager suppling the operations to and from the database
static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Vacations");
	
	//method to enter resort
	public void insertResort(ListResort r) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		em.close();
	}
	//method to show resorts using JPQL query
	public List<ListResort>showAllResorts(){
		EntityManager em = emfactory.createEntityManager();
		List<ListResort>allResorts=em.createQuery("SELECT i FROM ListResort i").getResultList();
		return allResorts;
	}
	
	//method to delete resorts using JPQL query
	public void deleteResorts(ListResort toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListResort>typedQuery = em.createQuery("select r from ListResort r where r.resort = :selectedResort and r.city = :selectedCity and r.country = :selectedCountry and r.price = :selectedPrice", ListResort.class);
		
		typedQuery.setParameter("selectedResort", toDelete.getResort());
		typedQuery.setParameter("selectedCity", toDelete.getCity());
		typedQuery.setParameter("selectedCountry", toDelete.getCountry());
		typedQuery.setParameter("selectedPrice", toDelete.getPrice());
		
		typedQuery.setMaxResults(1);
		
		ListResort result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	//method to search for resort by ID using JPQL query
	public ListResort searchById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListResort found = em.find(ListResort.class, idToEdit);
		em.close();
		return found;
	}

	//method to update resort
	public void updateResort(ListResort toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	//method to search for a resort by its name using JPQL query
	public List<ListResort> searchByResort(String resortName) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<ListResort> typedQuery = em.createQuery("select li from ListResort li where li.resort = :selectedResort", ListResort.class);
			typedQuery.setParameter("selectedResort", resortName);
			List<ListResort> foundResorts = typedQuery.getResultList();
			em.close();
			return foundResorts;
	}

	//method to search for a resort by its city using JPQL query
	public List<ListResort> searchByCity(String cityName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListResort> typedQuery = em.createQuery("select li from ListResort li where li.city = :selectedCity", ListResort.class);
		typedQuery.setParameter("selectedCity", cityName);
		List<ListResort> foundResorts = typedQuery.getResultList();
		em.close();
		return foundResorts;
	}
	//method to search for resort by its country using JPQL query
	public List<ListResort> searchByCountry(String countryName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListResort> typedQuery = em.createQuery("select r from ListResort r where r.country = :selectedCountry", ListResort.class);
		typedQuery.setParameter("selectedCountry", countryName);
		List<ListResort> foundResorts = typedQuery.getResultList();
		em.close();
		return foundResorts;
	}
	//method to search for resort by its price using JPQL query
	public List<ListResort> searchByPrice(Double priceName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListResort> typedQuery = em.createQuery("select r from ListResort r where r.price = :selectedPrice", ListResort.class);
		typedQuery.setParameter("selectedPrice", priceName);
		List<ListResort> foundResorts = typedQuery.getResultList();
		em.close();
		return foundResorts;
	}

	//Closing the EntityManagerFactory connection 
	public void cleanUp(){
		emfactory.close();
	}
}
