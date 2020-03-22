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
	@Path("{name: [A-Za-z]*}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public MetoxhInfo findInfoOfStock(@PathParam("name")String name, @PathParam("date")String date) {
		EntityManager em = getEntityManager();
//	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//	    Date date = new Date(); 
		
	    EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
	    Metoxh metoxh = e.findInformationOfStock(name, date);
	    System.out.println(metoxh.getName());
	    
		MetoxhInfo mi;
		mi=MetoxhInfo.wrap(metoxh);
		return mi;
	} 
///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη μετοχή τους δείκτες (ΜΚΟ15, ΜΚΟ80, yk20, xk20, beta)
//////////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("{stockname: [A-Za-z]*}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public String StockImage(@PathParam("stockname")String stockname,@PathParam("date")String date) {
	String InformationOfStock=null;
//	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//    Date date = new Date(); 
	EntityManager em=getEntityManager();
	EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
	InformationOfStock=e.StockImage(stockname,date);
	return InformationOfStock;
	}

///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει όλες τις μετοχές που παρακολουθούμε στη βάση μας 
//////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("getAllStocks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MetoxhInfo> findAllMetoxes(){
		List<Metoxh> list=null;
		List<MetoxhInfo> ml=null;
		EntityManager em=getEntityManager();
    	EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		list=e.findAllMetoxes();
		for (Metoxh m:list) {
			ml.add(MetoxhInfo.wrap(m));
		}
		return ml;		
  }
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει πρόταση με βάση τον αλγόριθμο PPO για μια συγκεκριμενη μετοχή.
/////////////////////////////////////////////////////////////////	
	@GET
	@Path("{pponame: [A-Za-z]*}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public String BuyOrSellPerStock(@PathParam("pponame")String pponame,@PathParam("date")String date) {
		String BuyOrSellPerStock=null;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);	
		BuyOrSellPerStock=e.BuyOrSellPerStock(pponame,date);
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
	@Path("{Stock}/{DXId}/{CusId}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public double showPosostoofStocksperPortofolio(
			@PathParam("Stock")String Stock,
			@PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId,
			@PathParam("date")String date) { 
		double showPosostoofStocksperPortofolio;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
	    showPosostoofStocksperPortofolio=e.showPosostoofStocksperPortofolio(Stock,DXId,CusId,date);
	    return showPosostoofStocksperPortofolio;
	}
}