package gr.aueb.mscis.sample.service; 

/* Περίπτωση Χρήσης 6 */

import javax.persistence.*;
import java.util.*;
import gr.aueb.mscis.sample.model.Customer;

public class PelatologioManagement {

	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomersById(int id) {
		List<Customer> results = null;
		results = em.createQuery("select c from Customer c where c.id like :id ").setParameter("id", id)
				.getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByLastName(String lastName) {
		List<Customer> results = null;
		results = em
				.createQuery(
						"select c from Customer c where c.customer.lastName like :lastName ")
				.setParameter("surname", lastName).getResultList();
	
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByFirstName(String firstName) {
	
		List<Customer> results = null;
		results = em
				.createQuery(
						"select f from Customer f where f.customer.firstName like :firstName ")
				.setParameter("name", firstName).getResultList();
	
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByAFM(String AFM) {
	
		List<Customer> results = null;
		results = em
				.createQuery(
						"select afm from Customer afm where afm.customer.AFM like :AFM ")
				.setParameter("AFM", AFM).getResultList();
	
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByADT(String ADT) {
	
		List<Customer> results = null;
		results = em
				.createQuery(
						"select adt from Customer adt where adt.customer.ADT like :ADT ")
				.setParameter("ADT", ADT).getResultList();
	
		return results;
	}


	//PROVOLI PELATOLWN
	public List<Customer> ShowCustomers() {
		List<Customer> result= findAllCustomer();
		
		for (Customer Customer : result) {
			System.out.println("Id " + Customer.getCustomerId() +  " FirstName  " + Customer.getName() + " LastName " + Customer.getSurname()
					+ " ADT " + Customer.getADT() + " AFM " + Customer.getAFM() + " EMAIL "
					+ Customer.getEmail() + " Telephone " + Customer.getTel() + "BirthDate"+ Customer.getBirthDate()
			        + "investAmoun" + Customer.getInvestAmount()+ "bankAccountNo" +Customer.getBankAccountNo() );
		}
		return result;
	}
		
	//KATAXORISE H ENHMERWSE	
	public boolean saveOrUpdateCustomer(Customer c) {

		if (c != null) {
			em.merge(c);
			return true;
		}
		return false;
	}

	public boolean createCustomer(Customer c) {
		if (c != null) {
			em.persist(c);
			return true;
		}
		return false;
	}
		
	public boolean deleteCustomer(Customer c) {
		if (c != null) {
			em.remove(c);
			return true;
		}
		return false;
	}

	public List<Customer> findAllCustomer() {
		List<Customer> results = null;

		results = em.createQuery("select c from Customer c", Customer.class)
				.getResultList();
		return results;
	}
	
}
