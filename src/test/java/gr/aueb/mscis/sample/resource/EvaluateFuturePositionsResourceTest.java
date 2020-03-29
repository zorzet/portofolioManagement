package gr.aueb.mscis.sample.resource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;

public class EvaluateFuturePositionsResourceTest extends JerseyTest {
	
	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(EvaluateFuturePositionsResource.class);
	}
	
	public EvaluateFuturePositionsResourceTest() {
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
		Metoxh m = em.createQuery("select m from Metoxh m", Metoxh.class).setMaxResults(1).getSingleResult();
		MetoxhInfo metoxhinfo = target("/EvaluatePositions/infoStock/" + m.getName() + "/" + m.getDate()).request().get(MetoxhInfo.class);
		assertEquals(m, metoxhinfo.getMetoxh(em));
	}
	
	@Test
	public void test_StockImage() {
		EntityManager em = JPAUtil.getCurrentEntityManager();	
		EvaluateFuturePositionsService eval = new EvaluateFuturePositionsService(em);
		String exp_output = "NO STOCK FOUND";
		String output = target("/EvaluatePositions/stockImage/AEGN/23-02-2020").request().get(String.class);
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
	
	@Test
	public void test_BuyOrSellPerStock() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		String exp_results = "";
		String results = target("/EvaluatePositions/ppo/AEGN/22-02-2020").request().get(String.class);
		assertEquals(exp_results, results);
		
		exp_results = "NO STOCK FOUND";
		try {
			results = target("/EvaluatePositions/ppo/AEGN/28-02-2020").request().get(String.class);
		} catch (java.lang.RuntimeException msg) {
			assertEquals(exp_results, msg.getMessage());
		}
	}

}
