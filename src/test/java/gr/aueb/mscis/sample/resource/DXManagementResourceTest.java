package gr.aueb.mscis.sample.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.persistence.Initializer;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.DXManagementService;

public class DXManagementResourceTest  extends JerseyTest {

	private Initializer dataHelper;
	
	@Override
	protected Application configure() {
		return new ResourceConfig(DXManagementResource.class);
	}
	
	public DXManagementResourceTest() {
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
		DX dx = em.createQuery("select dx from DX dx", DX.class).setMaxResults(1).getSingleResult();
		tx.commit();
		
		DXManagementService p = new DXManagementService(em);
		List<DX> exp_output = p.findDXsByLastName(dx.getSurname());
		List<DX> output = new ArrayList<DX>();
		List<DXInfo> dxinfo = target("/DXManagement/findDXsByLastName/" + dx.getSurname()).request().get(new GenericType<List<DXInfo>>() {});
		for(DXInfo di : dxinfo) {
			output.add(di.getDX(em));
		}
		assertEquals(exp_output, output);
		
		try {
			dxinfo = target("/DXManagement/findDXsByLastName/akjlsd").request().get(new GenericType<List<DXInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		DXInfo dxinfo1 = target("/DXManagement/findDXById/" + dx.getDXId()).request().get(DXInfo.class);
		assertEquals(dx, dxinfo1.getDX(em));
		
		try {
			dxinfo1 = target("/DXManagement/findDXById/10").request().get(DXInfo.class);
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<DX>();
		dxinfo = target("/DXManagement/findDXsByFirstName/" + dx.getName()).request().get(new GenericType<List<DXInfo>>() {});
		for(DXInfo di : dxinfo) {
			output.add(di.getDX(em));
		}
		assertEquals(exp_output, output);
		
		try {
			dxinfo = target("/DXManagement/findDXsByFirstName/akjlsd").request().get(new GenericType<List<DXInfo>>() {});
		} catch (java.lang.RuntimeException msg) {
			assertNull(msg);
		}
		
		output = new ArrayList<DX>();
		exp_output = em.createQuery("select dx from DX dx", DX.class).getResultList();
		dxinfo = target("/DXManagement/findAllDXs").request().get(new GenericType<List<DXInfo>>() {});
		for(DXInfo di : dxinfo) {
			output.add(di.getDX(em));
		}
		assertEquals(exp_output, output);
		
		Response response = target("/DXManagement/UpdateDX/").request().post(Entity.entity(DXInfo.wrap(dx), MediaType.APPLICATION_JSON));
		assertEquals(Status.ACCEPTED.getStatusCode(), response.getStatus());
		response = target("/DXManagement/CreateDX/").request().post(Entity.entity(DXInfo.wrap(dx), MediaType.APPLICATION_JSON));
		assertEquals(Status.ACCEPTED.getStatusCode(), response.getStatus());
	}

}
