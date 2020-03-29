package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.XartofulakioService;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.resource.XartofulakioInfo;

public class XartofulakioResourceTest extends JerseyTest {

	private Initializer dataHelper;

	@Override
	protected Application configure() {
		return new ResourceConfig(XartofulakioResource.class);
	}

	public XartofulakioResourceTest() {
		super();
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		dataHelper = new Initializer();
		dataHelper.prepareData();
	}

	@Test
	public void test_findXartofulakia() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		XartofulakioService xs = new XartofulakioService(em);
		List<Xartofulakio> exp_results = new ArrayList<>();
		List<Xartofulakio> results = new ArrayList<>();
		Xartofulakio x = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).setMaxResults(1)
				.getSingleResult();
		exp_results.add(x);
		List<XartofulakioInfo> xinfo = target("/Xartofulakio/xartofulakia/" + exp_results.get(0).getDX().getDXId())
				.request().get(new GenericType<List<XartofulakioInfo>>() {
				});
		for (XartofulakioInfo xi : xinfo) {
			results.add(xi.getXartofulakio(em));
		}
		assertEquals(exp_results, results);
		
		String exp_output = xs.ShowXartofulakioPelath(x.getDX().getDXId(), x.getCus().getCustomerId());
		String output = target(
				"/Xartofulakio/xartofulakioPelath/" + x.getDX().getDXId() + "/" + x.getCus().getCustomerId()).request()
						.get(String.class);
		assertEquals(exp_output, output);
	}

	@Test
	public void test_ReturnCustomerImage() {
		EntityManager em = JPAUtil.getCurrentEntityManager();
		XartofulakioService xs = new XartofulakioService(em);
		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
		Xartofulakio x = null;
		for (Xartofulakio xl: xlist) {
			if(xl.getCus().getCustomerId()==2) {
				x = xl;
			}
		}
		String exp_results = "";
		String results = target(
				"/Xartofulakio/xartofulakioPelathImg/" + x.getDX().getDXId() + "/" + x.getCus().getCustomerId() + "/23-02-2020").request()
						.get(String.class);
		assertEquals(exp_results, results);
	}

//	@Test
//	public void test_transact() {
//		EntityManager em = JPAUtil.getCurrentEntityManager();
//		Xartofulakio x = null;
//		List<Xartofulakio> xlist = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
//		for (Xartofulakio xl : xlist) {
//			if (xl.getCus().getCustomerId() == 1)
//				x = xl;
//		}
//		Response response = target("Xartofulakio/transact/sell/AEGN/20/3.54/25-02-2020/open").request()
//				.post(Entity.entity(XartofulakioInfo.wrap(x), MediaType.APPLICATION_JSON));
//		assertEquals(Status.ACCEPTED.getStatusCode(), response.getStatus());
//
//	}
}
