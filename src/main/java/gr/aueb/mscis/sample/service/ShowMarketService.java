package gr.aueb.mscis.sample.service;

import javax.persistence.*;
import java.util.*;
import gr.aueb.mscis.sample.model.MarketsData;

/** Περίπτωση Χρήσης 5 **/

public class ShowMarketService {

	EntityManager em;
	
	public ShowMarketService(EntityManager em) {
		this.em = em;
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
        Query query = em.createQuery("select m from MarketsData m order by m.date desc", MarketsData.class).setMaxResults(1);
        MarketImage=(String) query.getSingleResult().toString();
        tx.commit();
        return MarketImage;
	}
	
	public List<MarketsData> ShowMarketHistory(int days){
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    List<MarketsData> results = null;
	    results = em.createQuery("select m from MarketsData m order by m.date desc", MarketsData.class).setMaxResults(days).getResultList();
	    if(results.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}
	    tx.commit();
	    
		return results;
	}
	
	public void showResults(List<MarketsData> data) {
		for (int j = 0; j < data.size(); j++) {
			System.out.print("STOCK MARKETS IMAGE AT "+data.get(j).getDate()+" OPENING "+data.get(j).getOpening()+" CLOSING "+data.get(j).getClosing()
					+" HIGH "+data.get(j).getMax()+" LOW "+data.get(j).getMin()+"'\n'");
		}
	}
}
