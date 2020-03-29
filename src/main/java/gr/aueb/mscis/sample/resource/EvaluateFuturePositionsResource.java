package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;

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
	@Path("infoStock/{name: [A-Za-z]*}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public MetoxhInfo findInfoOfStock(@PathParam("name") String name, @PathParam("date") String date) {
		EntityManager em = getEntityManager();

		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		Metoxh metoxh = e.findInformationOfStock(name, date);

		MetoxhInfo mi;
		mi = MetoxhInfo.wrap(metoxh);
		return mi;
	}

///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη μετοχή τους δείκτες (ΜΚΟ15, ΜΚΟ80, yk20, xk20, beta)
//////////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("stockImage/{stockname: [A-Za-z]*}/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	public String StockImage(@PathParam("stockname") String stockname, @PathParam("date") String date) {
		EntityManager em = getEntityManager();
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		try {
			return e.StockImage(stockname, date);
		} catch (Exception msg) {
			return "NO STOCK FOUND";
		}
	}

///////////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει όλες τις μετοχές που παρακολουθούμε στη βάση μας 
//////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("getAllStocks")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MetoxhInfo> findAllMetoxes() {
		List<MetoxhInfo> ml = new ArrayList<MetoxhInfo>();
		try {
			List<Metoxh> list = null;

			EntityManager em = getEntityManager();
			EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
			list = e.findAllMetoxes();
			for (Metoxh m : list) {
				ml.add(MetoxhInfo.wrap(m));
			}
			return ml;
		} catch (Exception e) {
			return ml;
		}
	}

/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει πρόταση με βάση τον αλγόριθμο PPO για μια συγκεκριμενη μετοχή.
/////////////////////////////////////////////////////////////////	
	@GET
	@Path("ppo/{pponame: [A-Za-z]*}/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	public String BuyOrSellPerStock(@PathParam("pponame") String pponame, @PathParam("date") String date) {
		EntityManager em = getEntityManager();
		EvaluateFuturePositionsService eval = new EvaluateFuturePositionsService(em);

		try {
			return eval.BuyOrSellPerStock(pponame, date);
		} catch (Exception e) {
			return null;
		}
	}

///////////////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη συγκεκριμένη μετοχή τα τεμάχια που έχει ο χρήστης
///////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("stockUnits/{Stock}/{DXId}/{CusId}")
	@Produces(MediaType.TEXT_PLAIN)
	public int showUnitsofStocksperPortofolio(@PathParam("Stock") String Stock, @PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId) {
		EntityManager em = getEntityManager();
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		return e.showUnitsofStocksperPortofolio(Stock, DXId, CusId);
	}

///////////////////////////////////////////////////////////////////////////////
///το ποσοστό του κεφαλαίου που καταλαμβάνουν και το beta του χαρτολακίου
///////////////////////////////////////////////////////////////////////////////	
	@GET
	@Path("percStocks/{Stock}/{DXId}/{CusId}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Double showPosostoofStocksperPortofolio(@PathParam("Stock") String Stock, @PathParam("DXId") int DXId,
			@PathParam("CusId") int CusId, @PathParam("date") String date) {
		EntityManager em = getEntityManager();
		EvaluateFuturePositionsService e = new EvaluateFuturePositionsService(em);
		return e.showPosostoofStocksperPortofolio(Stock, DXId, CusId, date);
	}
}