package gr.aueb.mscis.sample.service;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import antlr.collections.List;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.persistence.JPAUtil;

/**
Περίπτωση Χρήσης 5
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
		String MarketImage="something";
		
		
		
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
        MarketImage=(String) query.getSingleResult();
		tx.commit();
		return MarketImage;
	}
	/**/
	public String ShowMarketHistory(int days){
		String history="somthing";
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    Query query = null;
	    query = em.createNativeQuery("select from MarketsData");
	    query.executeUpdate();
	    //history=(String) query.setMaxResults(days);
	    List stuList=(List) query.getResultList();
	    Iterator stuIterator=((java.util.List) stuList).iterator();
	    while(stuIterator.hasNext()){
	    	MarketsData md=(MarketsData)stuIterator.next();
	    	
	    }	
	    tx.commit();
		return history;
	}
}
