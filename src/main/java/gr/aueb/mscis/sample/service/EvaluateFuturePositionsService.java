package gr.aueb.mscis.sample.service;

/* Περίπτωση Χρήσης 3 */

import java.util.List;
import javax.persistence.EntityManager;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Xartofulakio;


public class EvaluateFuturePositionsService {

	EntityManager em;
	
	public EvaluateFuturePositionsService(EntityManager em) {
		this.em = em;
	}
	
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη μετοχή τους δείκτες (ΜΚΟ15, ΜΚΟ80, yk20, xk20, beta)
/////////////////////////////////////////////////////////////////	
	
	/**
	 *Επιστρέφει όλες τις μετοχές που παρακολουθούμε στη βάση μας 
	 * @return List
	 */
	public List<Metoxh> findAllMotoxes() {
		List<Metoxh> results = null;

		results = em.createQuery("select DISTINCT(m.Name) from Metoxh m", Metoxh.class)
				.getResultList();
		
		if(results==null) {
			throw new java.lang.RuntimeException("NO STOCK FOUND");
		}
		return results;
	}
	
	/**
	 * Για μια μετοχή, επιστρέφει την εικόνα της για μια συγκεκριμένη ημερομηνία
	 * @param name
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Metoxh InformationOfStock(String name, String date) {
		String InformationOfStock=null;
		List<Metoxh> ml=null;
		ml=findAllMotoxes();
	    for (int i = 0; i < ml.size(); i++) {
	    	if(ml.get(i).getDate().equalsIgnoreCase(date)&(ml.get(i).getName().equalsIgnoreCase(name))) {
	    		return ml.get(i);
	    	}
	    }
	    
	    	throw new java.lang.RuntimeException("NO STOCK FOUND");
	}
	
	
	/**
	 * Γυρνάει την εικόνα των δεικτών για μια συγκεκριμένη μετοχή
	 * για μια συγκεκριμένη μέρα
	 * @param name
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Deiktes findDeiktesPerStock(String name, String date){
		List <Deiktes>  dlist=null;
		Deiktes d=null;
		Metoxh m=InformationOfStock(name,date);
		dlist=em.createQuery("select d from Deiktes d where d.metoxh.Name like :metoxh ")
				.setParameter("metoxh",name).getResultList();
		for(int i=0; i<dlist.size(); i++)
			{
				if(dlist.get(i).getDate().equalsIgnoreCase(m.getDate()))
						d=dlist.get(i);
			}
		if(d==null) {
			throw new java.lang.RuntimeException("NO DEIKTES RECORD FOUND");
		}
		return d;
	}
	
	public String StockImage(String name, String date) {
	String InformationOfStock; 
	Metoxh m=null;
	Deiktes d=null;
	m=InformationOfStock(name,date);
	d=findDeiktesPerStock(name,date);
	InformationOfStock=" Name "+m.getName()+" Date "+m.getDate()+" Beta "+m.getBeta()+m.getClosing()
						+ " MKO15 "+d.getMKO15()+" MKO80: "+d.getMKO80()+" XK20 "+d.getXk20()+" YK2O "+d.getYk20();
	
	return InformationOfStock;
	}
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει πρόταση με βάση τον αλγόριθμο PPO.
/////////////////////////////////////////////////////////////////	
	/**
	 * Για μια μτοχή, για κάποια ημέρα, με βάση τη τιμή της μετοχής και τους δείκτες, υπολογίζεται ο PPO
	 * και επιστρέφει μήνυμα αγοράς η πώλησης
	 * @param sname
	 * @param date
	 * @return
	 */
	public String BuyOrSellPerStock(String sname, String date) {
		String decision=null;
		Deiktes d=null;
		Metoxh m=null;
		m=InformationOfStock(sname,date);
		d=findDeiktesPerStock(sname,date);
		if(((d.getMKO15()>d.getMKO80())&(m.getClosing()>d.getYk20()))){
			decision="buy";
		}else if(m.getClosing()<d.getXk20()){
			decision="sell";
		}else {
			decision="none";
		}
		return decision;
	}	
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει για τη συγκεκριμένη μετοχή τα τεμάχια που έχει ο χρήστης,
//το ποσοστό του κεφαλαίου που καταλαμβάνουν και το beta του χαρτολακίου.
/////////////////////////////////////////////////////////////////			
	
	/**
	 * Επιστρέφει για τη συγκεκριμένη μετοχή τα τεμάχια που έχει ο χρήστης
	 * @param Stock
	 * @param XId
	 * @param CusId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int showUnitsofStocksperPortofolio(String Stock, int XId,int CusId) {
		int units=0;
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
				units=translist.get(j).getUnits();
			}
		}
		return units;
	}
	@SuppressWarnings("unchecked")
	public double showPosostoofStocksperPortofolio(String Stockn, int XId,int CusId,String date) {
		double pososto=0.0;
		//PARE OLA TA XARTOFULAKIA TOU DX
		List<Xartofulakio> xlist=null;
		List<Transaction> translist=null;
		xlist= em.createQuery("select x from Xartofulakio x where x.XId like :DXId ").setParameter("DXId", XId)
				.getResultList();
		//VRES AUTO TOU PELATH
		Xartofulakio x=null;
		for (int i = 0; i < xlist.size(); i++) {
			if((xlist.get(i).getCus().getCustomerId())==CusId) {
				pososto=xlist.get(i).getCus().getInvestAmount();
			}
		}
		pososto=(showUnitsofStocksperPortofolio(Stockn,XId,CusId)*InformationOfStock(Stockn,date).getClosing() )/pososto;
		return pososto;
	}
	public void printing(String Stockn, int XId,int CusId,String date) {
		System.out.println("UNITS OF STOCK IN PORTOFOLIO "+showUnitsofStocksperPortofolio(Stockn,XId,CusId));
		System.out.println("Percentage of Units of Stock In Portofolio "+showPosostoofStocksperPortofolio(Stockn,XId,CusId,date));
		System.out.println("BETA of Portofolio "+gr.aueb.mscis.sample.service.XartofulakioService.CalculateBeta( XId,CusId,date));
	}
}