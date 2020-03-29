package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.service.PelatologioManagement;
import gr.aueb.mscis.sample.resource.*;

import java.util.List;

@Path("PelatologioManagement")
public class PelatologioManagementResource {

	@Context
	UriInfo uriInfo;

	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}

//public Customer findCustomersById(int id)

	@GET
	@Path("findCustomersById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerInfo findCustomersById(@PathParam("id") int id) {
		EntityManager em = getEntityManager();
		PelatologioManagement p = new PelatologioManagement(em);

		try {
			Customer customer = p.findCustomersById(id);
			CustomerInfo c;
			c = CustomerInfo.wrap(customer);
			return c;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByLastName(String lastName)

	@GET
	@Path("findCustomersByLastName/{Surname: [A-Za-z]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByLastName(@PathParam("Surname") String surname) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByLastName(surname);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByFirstName(String firstName)
	@GET
	@Path("findCustomersByFirstName/{Name: [A-Za-z]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByFirstName(@PathParam("Name") String name) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByFirstName(name);
			System.out.println(list.size());
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByAFM(String AFM)
	@GET
	@Path("findCustomersByAFM/{AFM: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByAFM(@PathParam("AFM") String afm) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByAFM(afm);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByADT(String ADT)
	@GET
	@Path("findCustomersByADT/{ADT}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByADT(@PathParam("ADT") String adt) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByADT(adt);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByTel(String Tel)
	@GET
	@Path("findCustomersByTel/{Tel: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByTel(@PathParam("Tel") String tel) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByTel(tel);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByEmail(String Email)
	@GET
	@Path("findCustomersByEmail/{Email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByEmail(@PathParam("Email") String email) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByEmail(email);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByInvestAmount(double InvestAmount)
	@GET
	@Path("findCustomersByInvestAmount/{InvestAmount}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByInvestAmount(@PathParam("InvestAmount") double InvestAmount) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByInvestAmount(InvestAmount);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findCustomerByBankAccountNo(String Bank)
	@GET
	@Path("findCustomersByBankAccountNo/{Bank}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByBankAccountNo(@PathParam("Bank") String Bank) {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findCustomerByBankAccountNo(Bank);
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}

//public List<Customer> findAllCustomer()	

	@GET
	@Path("findAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findAllCustomer() {
		List<CustomerInfo> ln = new ArrayList<CustomerInfo>();
		try {
			EntityManager em = getEntityManager();
			PelatologioManagement p = new PelatologioManagement(em);
			List<Customer> list = p.findAllCustomer();
			for (Customer c : list) {
				ln.add(CustomerInfo.wrap(c));
			}
			return ln;
		} catch (Exception e) {
			return null;
		}
	}
	
	@POST
	@Path("UpdateCustomer/{xid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("xid") int xid, CustomerInfo cust) {
		EntityManager em = getEntityManager();
		PelatologioManagement p = new PelatologioManagement(em);
		Customer c = cust.getCustomer(em);
		Xartofulakio x = em.find(Xartofulakio.class, xid);
		boolean answer = p.saveOrUpdateCustomer(x, c);
		if (answer = true) {
			return Response.accepted().build();
		} else {
			return Response.notModified().build();
		}
	}
}
