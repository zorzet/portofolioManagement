package gr.aueb.mscis.sample.service;

import java.util.List;

import javax.persistence.*;
import org.junit.*;
import static org.junit.Assert.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.PelatologioManagement;

public class PelatologioManagementTest {
	protected EntityManager em;
	
	@Before
	public void setup(){
		Initializer dataHelper = new Initializer();
		dataHelper.prepareData();	
		em = JPAUtil.getCurrentEntityManager();
	}
	
	@After
	public void tearDown(){
		em.close();
	}
	
	@Test
	public void test_findAllCustomer() {
		List<Customer> results = null;
		PelatologioManagement p = new PelatologioManagement();
		results = p.findAllCustomer();
		assertEquals(1,results.size());
	}
	
}
