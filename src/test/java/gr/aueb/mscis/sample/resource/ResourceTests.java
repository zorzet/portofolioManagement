package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.*;
import gr.aueb.mscis.sample.resource.*;
import gr.aueb.mscis.sample.model.*;

public class ResourceTests extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(EvaluateFuturePositionsResource.class);
	}
	
	public ResourceTests() {
		super();
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}
	
	/**
	 * Tests for 
	 * EvaluatePositions
	 */	
	@Test
	public void test_findInfoOfStock() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Metoxh m = em.createQuery("select m from Metoxh m", Metoxh.class).setMaxResults(1).getSingleResult();
		tx.commit();

		MetoxhInfo metoxhinfo = target("/EvaluatePositions/infoStock/" + m.getName() + "/" + m.getDate()).request().get(MetoxhInfo.class);
		assertEquals(m, metoxhinfo.getMetoxh(em));
	}
	
	@Test
	public void test_StockImage() {
		EntityManager em = JPAUtil.getCurrentEntityManager();	
		EvaluateFuturePositionsService eval = new EvaluateFuturePositionsService(em);
		String exp_output = eval.StockImage("AEGN", "22-02-2020");
		String output = target("/EvaluatePositions/stockImage/AEGN/22-02-2020").request().get(String.class);
		assertEquals(exp_output, output);
	}
	
	@Test
	public void test_getAllStocks() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EvaluateFuturePositionsService eval = new EvaluateFuturePositionsService(em);
		List<Metoxh> exp_output = eval.findAllMetoxes();
		List<Metoxh> output = new ArrayList<Metoxh>();

		List<MetoxhInfo> outputinfo = target("/EvaluatePositions/getAllStocks").request().get(new GenericType<List<MetoxhInfo>>() {});
		for(MetoxhInfo m : outputinfo) {
			output.add(m.getMetoxh(em));
		}
		assertEquals(exp_output,output);
	}
	
	/**
	 * Tests for 
	 * PelatologioManagement
	 */	
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
		System.out.println(custinfo.size());
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
	
	/**
	 * Tests for 
	 * ShowMarket
	 */	
	@Test
	public void getOnlineMarketImageTest() {
		String exp_output = "something";
		String output = target("/ShowMarket/getOnlineMarket").request().get(String.class);
		assertEquals(exp_output, output);
	}
	
	@Test
	public void getOfflineMarketImageTest() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		ShowMarketService s = new ShowMarketService(em);
		String exp_output = s.getOfflineMarketImage();
		String output = target("/ShowMarket/getOfflineMarket").request().get(String.class);
		assertEquals(exp_output, output);
	}
	
	@Test
	public void showMarketHistoryTest() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		ShowMarketService s = new ShowMarketService(em);
		List<MarketsData> exp_output = s.ShowMarketHistory(2);
		List<MarketsData> output = new ArrayList<MarketsData>();

		List<MarketsDataInfo> outputinfo = target("/ShowMarket/ShowMarketHistory/2").request().get(new GenericType<List<MarketsDataInfo>>() {});
		for(MarketsDataInfo m : outputinfo) {
			output.add(m.getMarketsData(em));
		}
		
		assertEquals(exp_output,output);
		
		try {
			target("/ShowMarket/ShowMarketHistory/2").request().get(new GenericType<List<MarketsDataInfo>>() {});
		} catch(java.lang.RuntimeException msg) {
			assertNull(msg);
		}
	}
}
