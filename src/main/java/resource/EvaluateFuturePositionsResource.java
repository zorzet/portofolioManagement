package resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.model.Metoxh;
import java.text.SimpleDateFormat;  
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;
import java.util.Date;  

@Path("EvaluatePositions")
public class EvaluateFuturePositionsResource {
	EntityManager em;
	@Context
	UriInfo uriInfo;
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}
	/*
	@GET
	@Path("{Stockn}")
	@Produces(MediaType.APPLICATION_JSON)
	public void EvaLuateFuturePositionPerStock(@PathParam("Stockn")  String Stockn, int XId,int CusId,String date, String name){
		EvaLuateFuturePositionPerStock(Stockn,XId,CusId,date,name);
	}
	*/
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Metoxh findInfoOfStock(@PathParam("name")String name) {
		Metoxh metoxh=new Metoxh();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
	    metoxh=e.findInformationOfStock(name,formatter.format(date));
		return metoxh;
	}
}
