package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.service.UpdateIndicatorsService;
import gr.aueb.mscis.sample.service.XartofulakioService;

import java.util.List;  
import javax.ws.rs.core.Response.Status;

@Path("UpdateIndicators")
public class UpdateIndicatorsResource {

	@Context
	UriInfo uriInfo;
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}

/////////////////////////////////////////////////////////////////////
//Το σύστημα αποθηκεύει τα ημερήσια στοιχεία για την κίνηση των μετοχών (υψηλό, χαμηλό, κλείσιμο, άνοιγμα, τζίρος).
//και ενημερώνει τους δείκτες	
/////////////////////////////////////////////////////////////////	
    @POST
    @Path("/transact/{name: [A-Za-z]*}/{high}/{low}/{closing}/{beta}/{Volume}/{date}")
	@Consumes({"text/plain"})
	public Response UpdateDeiktesAndStock(@PathParam("name") String name,@PathParam("date") String date,@PathParam("high") Double high
			,@PathParam("low")  Double low,@PathParam("closing") Double closing,@PathParam("beta") Double beta,@PathParam("Volume") int Volume) {
    	EntityManager em = getEntityManager();
		UpdateIndicatorsService indicators = new UpdateIndicatorsService(em);
		int error=indicators.UpdateDeiktesAndStock(name,date,high,low,closing,beta,Volume); 
		if(error==0) {
			return Response.status(Status.ACCEPTED).build();
		}else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
   
}
