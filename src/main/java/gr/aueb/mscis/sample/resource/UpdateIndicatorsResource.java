package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.UpdateIndicatorsService;
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
    @Path("{name: [A-Za-z]*}/{high}/{low}/{closing}/{beta}/{Volume}/{date}")
	@Consumes({"text/plain"})
	public Response UpdateDeiktesAndStock(@PathParam("name") String name,@PathParam("date") String date,@PathParam("high") Double high
			,@PathParam("low")  Double low,@PathParam("closing") Double closing,@PathParam("beta") Double beta,@PathParam("Volume") int Volume) {
    	EntityManager em = getEntityManager();
		UpdateIndicatorsService indicators = new UpdateIndicatorsService(em);
		int error=indicators.UpdateDeiktesAndStock(name,date,high,low,closing,beta,Volume); 
		if(error==0) {
			return Response.accepted().build();
		}else {
			return Response.notModified().build();
		}
	}
   
}
