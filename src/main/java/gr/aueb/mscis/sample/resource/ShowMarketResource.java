	package gr.aueb.mscis.sample.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import gr.aueb.mscis.sample.service.ShowMarketService;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.service.EvaluateFuturePositionsService;

@Path("EvaluatePositions")
public class ShowMarketResource {

	@Context
	UriInfo uriInfo;
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}	
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει την εικόνα του FTSE εκείνη τη στιγμή 
/////////////////////////////////////////////////////////////////	
	@GET
	@Path("getOnlineMarket")
	@Produces(MediaType.APPLICATION_JSON)
	public MarketInfo  getOnlineMarketImage() {
		EntityManager em = getEntityManager();
		MarketInfo marketsnow=null;
		ShowMarketService s = new ShowMarketService(em);
		return MarketInfo.wrap(s.getOnlineMarketImage());
	}
/////////////////////////////////////////////////////////////////////
//Εμφανίζεται κατάλληλο μήνυμα ενημέρωσης
//Εμφανίζεται η εικόνα του FTSE για όλη την προηγούμενη εργάσιμη και η τιμή κλεισίματος.
////////////////////////////////////////////////////////////////////	
	@GET
	@Path("getOffineMarket")
	@Produces(MediaType.APPLICATION_JSON)
	public MarketInfo getOfflineMarketImage() {
		MarketInfo info;
		EntityManager em = getEntityManager();
		ShowMarketService s = new ShowMarketService(em);
		info=MarketInfo.wrap(s.getOfflineMarketImage());
		return info;
	}
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει τα κλεισίματα του FTSE για το επιλεγμένο διάστηματος.
////////////////////////////////////////////////////////////////////
	@GET
	@Path("ShowMarketHistory/{daysno}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MarketsDataInfo> ShowMarketHistory(@PathParam("daysno") int daysno){
		EntityManager em = getEntityManager();
		List<MarketsDataInfo> outputlist=new ArrayList<MarketsDataInfo>();
		List<MarketsData> list=null;
		try {
			ShowMarketService s = new ShowMarketService(em);
			list=s.ShowMarketHistory(daysno);
			for(MarketsData mdi:list ) {
				outputlist.add(MarketsDataInfo.wrap(mdi));
			}
			return outputlist;
		}catch(Exception e) {
			return null;
		}
	}
}
