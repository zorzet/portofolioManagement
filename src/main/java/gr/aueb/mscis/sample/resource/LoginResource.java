package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.LoginService;

@Path("Login")
public class LoginResource {
  
	@Context
	UriInfo uriInfo;
	 
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}
	
	@GET
	@Path("{username: [A-Za-z]*}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String LoginUser(@PathParam("username") String username,@PathParam("password") String password) {
		EntityManager em = getEntityManager();
		LoginService login=new LoginService(em);
		String answer=null;
		int error=login.LoginUser(username, password);
		if(error==0) {
			answer="SUCCESS";
		}else {
			answer="FAIL";
		}
		return answer;
	}

}
