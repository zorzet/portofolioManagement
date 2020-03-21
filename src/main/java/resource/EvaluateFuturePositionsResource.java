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
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;
import java.text.SimpleDateFormat;  
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;
import java.util.Date;
import java.util.List;  

@Path("EvaluatePositions")
public class EvaluateFuturePositionsResource {
	
	@Context
	UriInfo uriInfo;
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}

	
///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για μια μετοχή, τη συγκεκριμένη μέρα, τη τιμή της
//////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Metoxh findInfoOfStock(@PathParam("name")String name) {
		EntityManager em=getEntityManager();
		Metoxh metoxh=new Metoxh();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
	    metoxh=e.findInformationOfStock(name,formatter.format(date));
		return metoxh;
	} 
///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη μετοχή τους δείκτες (ΜΚΟ15, ΜΚΟ80, yk20, xk20, beta)
//////////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String StockImage(@PathParam("name")String name) {
	String InformationOfStock=null;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date(); 
	EntityManager em=getEntityManager();
	EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
	InformationOfStock=e.StockImage(name,formatter.format(date));
	return InformationOfStock;
	}

///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει όλες τις μετοχές που παρακολουθούμε στη βάση μας 
//////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("getAllStocks")
	@Produces(MediaType.APPLICATION_JSON)
public List<Metoxh> findAllMetoxes(){
		List<Metoxh> list=null;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		list=e.findAllMetoxes();
		return list;		
}
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει πρόταση με βάση τον αλγόριθμο PPO για μια συγκεκριμενη μετοχή.
/////////////////////////////////////////////////////////////////	
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String BuyOrSellPerStock(@PathParam("name")String name) {
		String BuyOrSellPerStock=null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		BuyOrSellPerStock=e.BuyOrSellPerStock(name,formatter.format(date));
		return BuyOrSellPerStock;
	}
///////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη συγκεκριμένη μετοχή τα τεμάχια που έχει ο χρήστης
///////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("{Stock}/{DXId}/{CusId}")
	@Produces(MediaType.APPLICATION_JSON)
	public int showUnitsofStocksperPortofolio(
			@PathParam("Stock")String Stock,
			@PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId) {
		int showUnitsofStocksperPortofolio;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		showUnitsofStocksperPortofolio=e.showUnitsofStocksperPortofolio(Stock, DXId, CusId);
		return showUnitsofStocksperPortofolio;
	}
///////////////////////////////////////////////////////////////////////////////
///το ποσοστό του κεφαλαίου που καταλαμβάνουν και το beta του χαρτολακίου
///////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("{Stock}/{DXId}/{CusId}")
	@Produces(MediaType.APPLICATION_JSON)
	public double showPosostoofStocksperPortofolio(
			@PathParam("Stock")String Stock,
			@PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId) { 
		double showPosostoofStocksperPortofolio;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    showPosostoofStocksperPortofolio=e.showPosostoofStocksperPortofolio(Stock,DXId,CusId,formatter.format(date));
	    return showPosostoofStocksperPortofolio;
	}
}