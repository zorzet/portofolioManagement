package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.DXManagementService;
import gr.aueb.mscis.sample.service.PelatologioManagement;

import java.util.*;

@Path("DXManagement")
public class DXManagementResource {

	@Context
	UriInfo uriInfo;

	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}
	
	@GET
	@Path("findDXById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DXInfo findDXById(@PathParam("id") int id) {
		EntityManager em = getEntityManager();
		DXManagementService d = new DXManagementService(em);

		try {
			DX dx = d.findDXById(id);
			DXInfo dxinfo;
			dxinfo = DXInfo.wrap(dx);
			return dxinfo;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("findDXsByLastName/{Surname: [A-Za-z]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DXInfo> findDXsByLastName(@PathParam("Surname") String surname) {
		List<DXInfo> dxlist = new ArrayList<DXInfo>();
		try {
			EntityManager em = getEntityManager();
			DXManagementService d = new DXManagementService(em);
			List<DX> list = d.findDXsByLastName(surname);
			for (DX dx : list) {
				dxlist.add(DXInfo.wrap(dx));
			}
			return dxlist;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("findDXsByFirstName/{Surname: [A-Za-z]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DXInfo> findDXsByFirstName(@PathParam("Name") String name) {
		List<DXInfo> dxlist = new ArrayList<DXInfo>();
		try {
			EntityManager em = getEntityManager();
			DXManagementService d = new DXManagementService(em);
			List<DX> list = d.findDXsByfirstname(name);
			for (DX dx : list) {
				dxlist.add(DXInfo.wrap(dx));
			}
			return dxlist;
		} catch (Exception e) {
			return null;
		}
	}
}
