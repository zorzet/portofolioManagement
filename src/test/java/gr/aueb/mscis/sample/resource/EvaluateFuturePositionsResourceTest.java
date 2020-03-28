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
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;
import gr.aueb.mscis.sample.service.ShowMarketService;
import gr.aueb.mscis.sample.resource.MetoxhInfo;
import gr.aueb.mscis.sample.model.Metoxh;

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
}
