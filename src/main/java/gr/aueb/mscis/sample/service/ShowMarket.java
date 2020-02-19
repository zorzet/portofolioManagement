package gr.aueb.mscis.sample.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.persistence.JPAUtil;

/**
 * CRUD operations and other functionality related to movies
 * 
 * @author bzafiris
 *
 */
public class ShowMarket {

	EntityManager em;
	
	public ShowMarket() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
/*Xtupaei to site kai gurnaei pisw times pou vazoume se ena string
 * Den gurnaei kati apo th vash*/
	
	public String getOnlineMarketImage(){
		String MarketImage;
		
		
		
		return MarketImage;
	}
	
	/*gurnaei th teleutaia timh tou xrhmatisthriou pou exei sth vash*/
	public String getOfflineMarketImage(){
		
		String MarketImage;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = null;
        query = em.createNativeQuery("select MarketsData from JpaClass MarketsData order by MarketsData.MDID desc ");
        query.executeUpdate();
        MarketImage=query.getSingleResult();
		tx.commit();
		return MarketImage;
	}
	/**/
	public String ShowMarketHistory(int days){
		String history;
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    Query query = null;
	    query = em.createNativeQuery("select from MarketsData");
	    query.executeUpdate();
	    histroy=query.setMaxResults(days);
	    List stuList=query.getResultList();
	    Iterator stuIterator=stuList.iterator();
	    while(stuIterator.hasNext()){
	    	MarketsData md=(MarketsData)stuIterator.next();
	    	
	    }	
	    tx.commit();
		return history;
	}
}
