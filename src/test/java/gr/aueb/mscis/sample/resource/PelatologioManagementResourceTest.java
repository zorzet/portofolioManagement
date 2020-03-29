package gr.aueb.mscis.sample.resource;

import static org.junit.Assert.*;

import java.util.*;

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
	public void test_findCustomersbyID() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		tx.commit();

		CustomerInfo custinfo = target("/PelatologioManagement/findCustomersById/" + c.getCustomerId()).request().get(CustomerInfo.class);
		assertEquals(c, custinfo.getCustomer(em));
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersById/10").request().get(CustomerInfo.class);
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
	}
	
	@Test
	public void test_findCustomersbyLastName() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
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
	}
	
	@Test
	public void test_findCustomersbyFirstName() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer c = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1).getSingleResult().getCus();
		tx.commit();
		
		PelatologioManagement p = new PelatologioManagement(em);
		List<Customer> exp_output = p.findCustomerByFirstName(c.getName());
		List<Customer> output = new ArrayList<Customer>();
		List<CustomerInfo> custinfo = target("/PelatologioManagement/findCustomersByFirstName/" + c.getName()).request().get(new GenericType<List<CustomerInfo>>() {});
		for(CustomerInfo ci : custinfo) {
			output.add(ci.getCustomer(em));
		}
		assertEquals(exp_output, output);
		
		try {
			custinfo = target("/PelatologioManagement/findCustomersByFirstName/akjlsd").request().get(new GenericType<List<CustomerInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
	}

}
