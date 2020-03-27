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
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;
import java.util.List;  

@Path("EvaluateFuturePositions")
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
	public InformationOfStockInfo StockImage(@PathParam("stockname")String stockname,@PathParam("date")String date) {
	InformationOfStockInfo info=null;
	try {
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		info=InformationOfStockInfo.wrap(e.StockImage(stockname,date));
		return info;
	}catch (Exception e){
		return null;
	}
	}

///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει όλες τις μετοχές που παρακολουθούμε στη βάση μας 
//////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("getAllStocks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MetoxhInfo> findAllMetoxes(){
		List<MetoxhInfo> ml=null;
		try {
				List<Metoxh> list=null;
				
				EntityManager em=getEntityManager();
				EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
				list=e.findAllMetoxes();
				for (Metoxh m:list) {
					ml.add(MetoxhInfo.wrap(m));
		}
				return ml;
		}catch (Exception e) {
			return ml;
		}
  }
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει πρόταση με βάση τον αλγόριθμο PPO για μια συγκεκριμενη μετοχή.
/////////////////////////////////////////////////////////////////	
	@GET
	@Path("{pponame: [A-Za-z]*}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public BuyOrSellPerStockInfo BuyOrSellPerStock(@PathParam("pponame")String pponame,@PathParam("date")String date) {
		BuyOrSellPerStockInfo info=null;
		try {
			EntityManager em=getEntityManager();
			EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);	
			info=BuyOrSellPerStockInfo.wrap(e.BuyOrSellPerStock(pponame,date));
			return info;
		}catch(Exception e) {
			return info;
		}
	}
///////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη συγκεκριμένη μετοχή τα τεμάχια που έχει ο χρήστης
///////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("{Stock}/{DXId}/{CusId}")
	@Produces(MediaType.APPLICATION_JSON)
	public showUnitsofStocksInfo showUnitsofStocksperPortofolio(
			@PathParam("Stock")String Stock,
			@PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId) {
		showUnitsofStocksInfo info=null;
		int showUnitsofStocksperPortofolio;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
		showUnitsofStocksperPortofolio=e.showUnitsofStocksperPortofolio(Stock, DXId, CusId);
		info=showUnitsofStocksInfo.wrap(showUnitsofStocksperPortofolio);
		return info;
	}
///////////////////////////////////////////////////////////////////////////////
///το ποσοστό του κεφαλαίου που καταλαμβάνουν και το beta του χαρτολακίου
///////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("{Stock}/{DXId}/{CusId}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public PosostoofStocksInfo showPosostoofStocksperPortofolio(
			@PathParam("Stock")String Stock,
			@PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId,
			@PathParam("date")String date) { 
		double showPosostoofStocksperPortofolio;
		PosostoofStocksInfo info=null;
		EntityManager em=getEntityManager();
		EvaluateFuturePositionsService e=new EvaluateFuturePositionsService(em);
	    showPosostoofStocksperPortofolio=e.showPosostoofStocksperPortofolio(Stock,DXId,CusId,date);
	    info=PosostoofStocksInfo.wrap(showPosostoofStocksperPortofolio);
	    return info;
	}
}