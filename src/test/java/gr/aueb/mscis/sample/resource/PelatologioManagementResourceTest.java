package gr.aueb.mscis.sample.resource;

import static org.junit.Assert.*;

import java.util.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.persistence.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.PelatologioManagement;

public class PelatologioManagementResourceTest extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(PelatologioManagementResource.class);
	}
	
	public PelatologioManagementResourceTest() {
		super();
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	@Test
	public void test_findCustomers() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Xartofulakio x = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult();
		Customer c = x.getCus();
		tx.commit();
		
		PelatologioManagement p = new PelatologioManagement(em);
		List<Customer> exp_output = p.findCustomerByLastName(c.getSurname());
		List<Customer> output = new ArrayList<Customer>();
		List<CustomerInfo> custinfo = target("/PelatologioManagement/findCustomersByLastName/" + c.getSurname()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByLastName/akjlsd").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		CustomerInfo custinfo1 = target("/PelatologioManagement/findCustomersById/" + c.getCustomerId()).request().get(CustomerInfo.class);
		assertEquals(c, custinfo1.getCustomer(em));
		
		try {
			custinfo1 = target("/PelatologioManagement/findCustomersById/10").request().get(CustomerInfo.class);
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByFirstName/" + c.getName()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByFirstName/akjlsd").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByAFM/" + c.getAFM()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByAFM/000000").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByADT/" + c.getADT()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByADT/000000").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByTel/" + c.getTel()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByTel/000000").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByEmail/" + c.getEmail()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByEmail/asdasd").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByInvestAmount/" + c.getInvestAmount()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByInvestAmount/0.23").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		custinfo = target("/PelatologioManagement/findCustomersByBankAccountNo/" + c.getBankAccountNo()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByBankAccountNo/asdasd").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<Customer>();
		exp_output = new ArrayList<Customer>();
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		for (Xartofulakio xl : xlist) {
			exp_output.add(xl.getCus());
		}
		custinfo = target("/PelatologioManagement/findAllCustomers").request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		Response response = target("/PelatologioManagement/UpdateCustomer/" + x.getXid()).request().post(Entity.entity(CustomerInfo.wrap(c), MediaType.APPLICATION_JSON));
		assertEquals(Status.ACCEPTED.getStatusCode(), response.getStatus());
	}

}
