package gr.aueb.mscis.sample.service;
 
import java.util.List;
import javax.persistence.EntityManager;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.service.XartofulakioService;
import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.persistence.JPAUtil;


/* Περίπτωση Χρήσης 3 */
public class EvaluateFuturePositionsService {

EntityManager em;
	
	public EvaluateFuturePositionsService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	
	/*Vres oles tis metoxes*/
	public List<Metoxh> findAllMotoxes() {
		List<Metoxh> results = null;

		results = em.createQuery("select from Metoxh m", Metoxh.class)
				.getResultList();
		
		if(results==null) {
			throw new java.lang.RuntimeException("NO STOCK FOUND");
		}
		return results;
	}
	/*Emfanise oles tis metoxes*/
	public void showStocks() {
		List <Metoxh> list=findAllMotoxes();
        for(Metoxh metoxh : list) {
            System.out.println(metoxh.getName()+" "+metoxh.getClosing()+" "+metoxh.getBeta()+" "+metoxh.getDate()+
            		           " "+metoxh.getHigh()+" "+metoxh.getLow());
        }
	}
	/*vres mia metoxh */
	public Metoxh findMetoxh(int StockId) {
		Metoxh results=null;
		results =(Metoxh) em.createQuery("select m from Metoxh m where m.Stockid : stockid")
				.setParameter("sotckid", StockId).getSingleResult();		
		if(results==null) {
			throw new java.lang.RuntimeException("NO STOCK FOUND");
		}
		return results;
	}
	
	
	/*Gia mia metoxh emfanise olous tous deiktes*/
	public Deiktes findDeiktesPerStock(int StockId){
		Deiktes d=null;
		d=(Deiktes)em.createQuery("select d from Deiktes d where d.metoxh.stockId like :metoxh ")
				.setParameter("metoxh",StockId).getSingleResult();
		if(d==null) {
			throw new java.lang.RuntimeException("NO DEIKTES RECORD FOUND");
		}
		return d;
	}
	
	/*Gia mia metoxh kane protash PPO me vash tous deiktes*/
	public String BuyOrSellPerStock(int StockId) {
		String decision=null;
		Deiktes d=null;
		Metoxh m=null;
		m=findMetoxh(StockId);
		d=findDeiktesPerStock(StockId);
		if(((d.getMKO15()>d.getΜΚΟ80())&(m.getClosing()>d.getYk20()))){
			decision="buy";
		}else if(m.getClosing()<d.getXk20()){
			decision="sell";
		}else {
			decision="none";
		}
		return decision;
	}	
		
	/*Emfanise Plhorofories Metoxhs sto Xarofulakio- an uparxei transaction open*/
	@SuppressWarnings("unchecked")
	public void showUnitsofStocksperPortofolio(String Stock, int XId,int CusId) {
		//PARE OLA TA XARTOFULAKIA TOU DX
		List<Xartofulakio> xlist=null;
		List<Transaction> translist=null;
		xlist= em.createQuery("select x from Xartofulakio x where x.XId like :DXId ").setParameter("DXId", XId)
				.getResultList();
		//VRES AUTO TOU PELATH
		Xartofulakio x=null;
		for (int i = 0; i < xlist.size(); i++) {
			if((xlist.get(i).getCus().getCustomerId())==CusId) {
				translist=em.createQuery("select t from Transaction t where t.Xartofulakio.XId like :Id ")
						.setParameter("Id", xlist.get(i).getXid()).getResultList();
			}
		}
		//VRES TA TRANSACTION POU EGINAN GIA AUTH TH METOXH & EINAI OPEN
		for (int j = 0; j < translist.size(); j++) {
			if((translist.get(j).getState().equalsIgnoreCase("Open")&(translist.get(j).getStock().equalsIgnoreCase(Stock)))){
				System.out.println("Stock in Portofolio "+translist.get(j).getStock()+" Units "+translist.get(j).getUnits()
						+"Bought at Price "+translist.get(j).getPrice()+" at date "+translist.get(j).getDate());
			}
		}
	}

}