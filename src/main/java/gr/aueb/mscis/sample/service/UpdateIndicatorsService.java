package gr.aueb.mscis.sample.service; 

/* Περίπτωση Χρήσης 4 */ 
/*ΕΧΩ ΠΑΡΕΙ ΤΗ ΠΑΡΑΔΟΧΗ ΟΤΙ Ο ΠΙΝΑΚΑΣ ΤΩΝ ΜΕΤΟΧΩΝ ΕΙΝΑΙ ΕΝΗΜΕΡΩΜΕΝΟΣ*/

import java.util.*;
import javax.persistence.*;

import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;

public class UpdateIndicatorsService {
	private EntityManager em;

	public UpdateIndicatorsService(EntityManager em) {
		this.em = em;
	}
	
	/*pare tis teleutaia 14 kleisimata mias metoxhs */
	@SuppressWarnings("unchecked")
	public double GetLast14ClosingsOfStock(String Name) {
		double Closings14 = 0.0;
		List<Metoxh> mtxlist = null;
	    mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name")
					.setParameter("Name", Name).setMaxResults(14).getResultList();
	    if(mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}

	    for (int i = 0; i < mtxlist.size(); i++) {
            Closings14+=mtxlist.get(i).getClosing();
        }
	    if(Closings14==0) {
	    	throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
	    }
		return (Closings14);
	}
	
	/*pare tis teleutaia 79 kleisimata mias metoxhs */
	@SuppressWarnings("unchecked")
	public double GetLast79ClosingsOfStock(String Name) {
		double Closings79=0.0;
		List<Metoxh> mtxlist=null;
	    mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name")
					.setParameter("Name", Name).setMaxResults(79).getResultList();
	    if(mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}
	
	    for (int i = 0; i < mtxlist.size(); i++) {
	        Closings79+=mtxlist.get(i).getClosing();
	    }
	    if(Closings79==0) {
	    	throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
	    }
		return Closings79;
	}
	
	/*pare tis teleutaia 19 upshla mias metoxhs */
	@SuppressWarnings("unchecked")
	public double GetLast19HighsOfStock(String Name) {
		double Highs19=0.0;
		List<Metoxh> mtxlist=null;
	    mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name")
					.setParameter("Name", Name).setMaxResults(19).getResultList();
	    if(mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}
	
	    for (int i = 0; i < mtxlist.size(); i++) {
	    	Highs19=mtxlist.get(i).getHigh()+Highs19;
	    }
	    if(Highs19==0) {
	    	throw new java.lang.RuntimeException("HIGH20 COULD NOT BE CALCULATED");
	    }
		return (Highs19);
	}	
	
	/*pare tis teleutaia 19 xamhla mias metoxhs */
	@SuppressWarnings("unchecked")
	public double GetLast19LowsOfStock(String Name) {
		double Lows19=0.0;
		List<Metoxh> mtxlist=null;
	    mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name")
					.setParameter("Name", Name).setMaxResults(19).getResultList();
	    if(mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}
	
	    for (int i = 0; i < mtxlist.size(); i++) {
	    	Lows19+=mtxlist.get(i).getLow();
	    }
	    if(Lows19==0) {
	    	throw new java.lang.RuntimeException("LOW20 COULD NOT BE CALCULATED");
	    }
		return (Lows19);
	}
	
	/*pare mia mia oles tis metoxes, to shmerino tous kleisimo, upshlo kai xamhlo kai 
	 * gia ka8e mia kane kainourgia eggrafh sto pinaka deiktes*/
	@SuppressWarnings("unchecked")
	public void UpdateIndicators(String date) {
		List<Metoxh> mtxlist=null;
		mtxlist = em.createQuery("select m from Metoxh m.date like :today")
					.setParameter("today", date).getResultList();
	    for (int i = 0; i < mtxlist.size(); i++) {
	    	UpdateDeiktesAndStock(mtxlist.get(i));
	    }
	
	}
		
	@SuppressWarnings("unused")
	public Metoxh createMetoxh(String name, String date, Double high, Double low, Double closing, Double beta, int Volume) {
		try {
			Metoxh m = new Metoxh(name, date, high, low, closing, beta, Volume);
			return m;
		} catch(Exception e) {
			throw new java.lang.RuntimeException("Error Updating Stock");
		}
	}
	

	public Deiktes createDeikth(String Name, double high, double low, double closing) {
		double ghigh = GetLast19HighsOfStock(Name);
		double glow = GetLast19LowsOfStock(Name);
		double gclosing14 = GetLast14ClosingsOfStock(Name); 
		double gclosing79 = GetLast79ClosingsOfStock(Name); 
		try {
			Deiktes d = new Deiktes(((glow+low/20)), ((ghigh+high)/20), ((gclosing14+closing)/30), ((gclosing79+closing)/80));
			return d;
		} catch (Exception e) {
			throw new java.lang.RuntimeException("Error Updating Indicators");
		}
	}
	
	/*UNDER CONSTRUCTION*/
	public void UpdateDeiktesAndStock(Metoxh m) {
		//Metoxh m = createMetoxh(stockid,name, date, high, low, closing,beta,Volume);
		Deiktes d = createDeikth(m.getName(), m.getHigh(), m.getLow(), m.getClosing());
		m.setDeiktes(d);
		d.setMetoxh(m);
		
        EntityTransaction tx = em.getTransaction();
        tx.begin();
		em.persist(m);
		em.persist(d);
		tx.commit();
	}
}
