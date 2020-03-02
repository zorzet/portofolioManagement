package gr.aueb.mscis.sample.service;

/* Περίπτωση Χρήσης 6 */

import javax.persistence.*;
import java.util.*;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;

public class PelatologioManagement {

	private EntityManager em;
	
	public PelatologioManagement(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public Customer findCustomersById(int id) {
		List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class)
				.getResultList();
		for(Xartofulakio x : xs) {
			if (x.getCus().getCustomerId() == id) return x.getCus();
		}
		throw new java.lang.RuntimeException("NO CUSTOMER FOUND");
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByLastName(String lastName) {
		List<Customer> results = new ArrayList<>();
		List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class)
				.getResultList();
		for(Xartofulakio x : xs) {
			if (x.getCus().getSurname() == lastName) results.add(x.getCus());
		}
		if(results.isEmpty()) {
			throw new java.lang.RuntimeException("NO CUSTOMER FOUND");
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByFirstName(String firstName) {
		List<Customer> results = new ArrayList<>();
		List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class)
				.getResultList();
		for(Xartofulakio x : xs) {
			if (x.getCus().getName() == firstName) results.add(x.getCus());
		}
		if(results.isEmpty()) {
			throw new java.lang.RuntimeException("NO CUSTOMER FOUND");
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByAFM(String AFM) {
		List<Customer> results = new ArrayList<>();
		List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class)
				.getResultList();
		for(Xartofulakio x : xs) {
			if (x.getCus().getAFM() == AFM) results.add(x.getCus());
		}
		if(results.isEmpty()) {
			throw new java.lang.RuntimeException("NO CUSTOMER FOUND");
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByADT(String ADT) {
		List<Customer> results = new ArrayList<>();
		List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class)
				.getResultList();
		for(Xartofulakio x : xs) {
			if (x.getCus().getADT() == ADT) results.add(x.getCus());
		}
		if(results.isEmpty()) {
			throw new java.lang.RuntimeException("NO CUSTOMER FOUND");
		}
		return results;
	}

	// PROVOLI PELATOLWN
	public void ShowCustomers() {
		List<Customer> result = findAllCustomer();

		for (Customer Customer : result) {
			System.out.println("Id " + Customer.getCustomerId() + " FirstName  " + Customer.getName() + " LastName "
					+ Customer.getSurname() + " ADT " + Customer.getADT() + " AFM " + Customer.getAFM() + " EMAIL "
					+ Customer.getEmail() + " Telephone " + Customer.getTel() + "BirthDate" + Customer.getBirthDate()
					+ "investAmoun" + Customer.getInvestAmount() + "bankAccountNo" + Customer.getBankAccountNo());
		}
	}

	// KATAXORISE H ENHMERWSE
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
		List<Customer> results = new ArrayList<>();

		List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for(Xartofulakio x : xs) results.add(x.getCus());
		return results;
	}

}
