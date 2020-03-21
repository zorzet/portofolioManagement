package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;

import org.junit.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;

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
		MetoxhInfo metoxhinfo = target("/EvaluatePositions/" + m.getName()).request().get(MetoxhInfo.class);
	}
}
