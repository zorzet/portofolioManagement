package gr.aueb.mscis.sample.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import gr.aueb.mscis.sample.service.XartofulakioService;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.JPAUtil;


@Path("Xartofulakio")
public class XartofulakioResource {

	@Context
	UriInfo uriInfo;
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}
	
/////////////////////////////////////////////////////////////
//// Για ένα Διαχειριστή επιστρέφει μια λίστα με τα χαρτοφυλάκια που αυτός διαχειρίζεται
//////////////////////////////////////////////////////////	
	@GET
	@Path("xartofulakia/{DXId}")
	@Produces(MediaType.TEXT_PLAIN)
	public List <XartofulakioInfo> findXartofulakiaById(@PathParam("DXId") int  DXId) {
		EntityManager em = getEntityManager();
		List<XartofulakioInfo> outputlist=new ArrayList<XartofulakioInfo>();
		List<Xartofulakio> list=null;
		try {
			XartofulakioService x = new XartofulakioService(em);
			list=x.findXartofulakiaById(DXId);
			for(Xartofulakio xartofulakio:list ) {
				outputlist.add(XartofulakioInfo.wrap(xartofulakio));
			}
			return outputlist;
		}catch(Exception e) {
			return null;
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////	
//Το σύστημα εμφανίζει το επιλεγμένο χαρτοφυλάκιο του πελάτη (τρέχουσες θέσεις, beta, ιστορικό).	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("xartofulakioPelath/{DXId}/{Cusid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ShowXartofulakioPelath(@PathParam("DXId") int DXId,@PathParam("Cusid") int Cusid) {
		String response=null;
		EntityManager em = getEntityManager();
		XartofulakioService x = new XartofulakioService(em);
		try {
			response=x.ShowXartofulakioPelath(DXId,Cusid);
		}catch (Exception e) {
			
		}
	return response;
	}

///////////////////////////////////////////////////////////////////////////////////////////////	
//Το σύστημα εμφανίζει για το επιλεγμένο Χαρτοφυλάκιο αν είναι αμυντικό, επιθετικό ή ουδέτερο       
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@GET
	@Path("xartofulakioPelath/{DXId}/{Cusid}/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ReturnCustomerImage(@PathParam("DXId") int DXId,@PathParam("Cusid") int Cusid, @PathParam("date") String date) {
		String response=null;
		EntityManager em = getEntityManager();
		XartofulakioService x = new XartofulakioService(em);
		try {
			response=x.ReturnCustomerImage(DXId, Cusid, date);
		}catch (Exception e) {

		}
	return response;	
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////
////Ενημέρωση Θέσης Πελάτη
/////////////////////////////////////////////////////////////////////////////////////////////////		
    @POST
    @Path("/transact/{cmdType: [A-Za-z]*}/{stock: [A-Za-z]*}/{units}/{price}/{date}/{state: [A-Za-z]*}/{x}")
	@Consumes({"text/plain"})
	public String transact(@PathParam("cmdType") String cmdType,@PathParam("stock") String stock,@PathParam("units") int units
			,@PathParam("price") double price,@PathParam("date") String date,@PathParam("state") String state,
			@PathParam("x") Xartofulakio x) {
    	String response;
    	int move;
    	EntityManager em = getEntityManager();
		XartofulakioService xartofulakio = new XartofulakioService(em);
    	move=xartofulakio.transact(cmdType, stock, units, price, date, state, x);
    	if(move==1) {
    		response="Transaction Completed. Position closed";
    	}else if(move==2) {
    		response="Sell! Transaction Completed";
    	}else if(move==3) {
    		response="Buy! Transaction Completed";
    	}else if(move==4) {
    		response="Out of balance.Transaction Completed";
    	}else {
    		response="Not valid Transaction";
    	}
    	return response;
    }
  }
