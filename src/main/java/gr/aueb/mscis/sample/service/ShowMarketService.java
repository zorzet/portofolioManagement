package gr.aueb.mscis.sample.service;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.persistence.JPAUtil;

/** Περίπτωση Χρήσης 5 **/

public class ShowMarketService {

	EntityManager em;
	
	public ShowMarketService() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	/* Xtupaei to site kai gurnaei pisw times pou vazoume se ena string
	 * Den gurnaei kati apo th vash */
	
	public String getOnlineMarketImage(){
		String MarketImage="something";
		return MarketImage;
	}
	
	/* gurnaei th teleutaia timh tou xrhmatisthriou pou exei sth vash */
	public String getOfflineMarketImage(){
		
		String MarketImage;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = null;
        query = em.createNativeQuery("select MarketsData from JpaClass MarketsData order by MarketsData.MDID desc ");
        query.executeUpdate();
        MarketImage=(String) query.getSingleResult();
		tx.commit();
		return MarketImage;
	}
	
	public List<MarketsData> ShowMarketHistory(int days){
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    List<MarketsData> results = null;
	    results = em.createQuery("select from MarketsData").getResultList();
	    tx.commit();
	    if(results==null) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}
		return results;
	}
}
