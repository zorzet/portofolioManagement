package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;

import org.junit.*;
import static org.junit.Assert.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import java.text.*;
import java.util.Date;

import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.model.Metoxh;
import resource.EvaluateFuturePositionsResource;
import resource.MetoxhInfo;

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

		MetoxhInfo metoxhinfo = target("/EvaluatePositions/" + m.getName() + "/" + m.getDate()).request().get(MetoxhInfo.class);
		assertEquals(m, metoxhinfo.getMetoxh(em));
	}
}
