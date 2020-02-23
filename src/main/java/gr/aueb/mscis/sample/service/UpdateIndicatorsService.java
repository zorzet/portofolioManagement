package gr.aueb.mscis.sample.service; 

/* Περίπτωση Χρήσης 4 */ 
/*ΕΧΩ ΠΑΡΕΙ ΤΗ ΠΑΡΑΔΟΧΗ ΟΤΙ Ο ΠΙΝΑΚΑΣ ΤΩΝ ΜΕΤΟΧΩΝ ΕΙΝΑΙ ΕΝΗΜΕΡΩΜΕΝΟΣ*/

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.List;

import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;

public class UpdateIndicatorsService {
	private EntityManager em;

	public UpdateIndicatorsService(EntityManager em) {
		this.em = em;
	}
	
	/*pare tis teleutaia 29 kleisimata mias metoxhs */
	@SuppressWarnings("unchecked")
	public double GetLast29ClosingsOfStock(int StockId) {
		double Closings29=0.0;
		List<Metoxh> mtxlist=null;
	    mtxlist = em
				.createQuery(
						"select from Metoxh m where m.StockId like :StockId ")
				.setParameter("StockId", StockId).
				setMaxResults(29)
				.getResultList();
	    if(mtxlist==null) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}

	    for (int i = 0; i < mtxlist.size(); i++) {
            Closings29+=mtxlist.get(i).getClosing();
        }
	    if(Closings29==0) {
	    	throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
	    }
		return (Closings29);
	}
	
	/*pare tis teleutaia 79 kleisimata mias metoxhs */
	public double GetLast79ClosingsOfStock(int StockId) {
	double Closings79=0.0;
	List<Metoxh> mtxlist=null;
    mtxlist = em
			.createQuery(
					"select from Metoxh m where m.StockId like :StockId ")
			.setParameter("StockId", StockId).
			setMaxResults(79)
			.getResultList();
    if(mtxlist==null) {
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
	public double GetLast19HighsOfStock(int StockId) {
	double Highs19=0.0;
	List<Metoxh> mtxlist=null;
    mtxlist = em
			.createQuery(
					"select m from Metoxh m where m.StockId like :StockId ")
			.setParameter("StockId", StockId).
			setMaxResults(19)
			.getResultList();
    if(mtxlist==null) {
		throw new java.lang.RuntimeException("NO RECORDS FOUND");
	}

    for (int i = 0; i < mtxlist.size(); i++) {
    	Highs19+=mtxlist.get(i).getHigh();
    }
    if(Highs19==0) {
    	throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
    }
	return (Highs19);
	}	
	
	/*pare tis teleutaia 19 xamhla mias metoxhs */
	@SuppressWarnings("unchecked")
	public double GetLast19LowsOfStock(int StockId) {
	double Lows19=0.0;
	List<Metoxh> mtxlist=null;
    mtxlist = em
			.createQuery(
					"select m from Metoxh m where m.StockId like :StockId ")
			.setParameter("StockId", StockId).
			setMaxResults(19)
			.getResultList();
    if(mtxlist==null) {
		throw new java.lang.RuntimeException("NO RECORDS FOUND");
	}

    for (int i = 0; i < mtxlist.size(); i++) {
    	Lows19+=mtxlist.get(i).getLow();
    }
    if(Lows19==0) {
    	throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
    }
	return (Lows19);
	}
	

	
	/*pare mia mia oles tis metoxes, to shmerino tous kleisimo, upshlo kai xamhlo kai 
	 * gia ka8e mia kane kainourgiaeggrafh sto pinaka deiktes*/
	@SuppressWarnings("unchecked")
	public void UpdateIndicators(String date) {
	List<Metoxh> mtxlist=null;
	mtxlist = em
			.createQuery("select m from Metoxh m.date like: today")
			.setParameter("today", date).getResultList();
    for (int i = 0; i < mtxlist.size(); i++) {
    	UpdateDeiktesAndStock(mtxlist.get(i).getStockId(),mtxlist.get(i).getDate(),mtxlist.get(i).getName(),mtxlist.get(i).getHigh(),mtxlist.get(i).getLow(),mtxlist.get(i).getClosing(),mtxlist.get(i).getBeta(),mtxlist.get(i).getVolume());
    }
	
	}
	
	
	@SuppressWarnings("unused")
	public Metoxh createMetoxh(int id, String name, String date, Double high, Double low, Double closing,Double beta,int Volume) {
		Metoxh m=new Metoxh();
		if(m==null)
			throw new java.lang.RuntimeException("Eror Updating Stock");
		try {
			m.setStockId(id);
			m.setDate(date);
			m.setLow(low);
			m.setHigh(high);
			m.setClosing(closing);
			m.setBeta(beta);
			m.setVolume(Volume);
		}catch(Exception e){
			throw new java.lang.RuntimeException("Eror Updating Stock");
		}
		return m;
	}
	

	public Deiktes createDeikth(int stockid,double high,double low,double closing) {
		Deiktes deikths = new Deiktes();
		double ghigh=GetLast19HighsOfStock(stockid);
		double glow=GetLast19LowsOfStock(stockid);
		double gclosing29=GetLast29ClosingsOfStock(stockid); 
		double gclosing79=GetLast79ClosingsOfStock(stockid); 
		try {
			deikths.setXk20((glow+low/20));
			deikths.setYk20((ghigh+high)/20);
			deikths.setMKO30((gclosing29+closing)/30);
			deikths.setΜΚΟ80((gclosing79+closing)/80); 
		} catch (Exception e) {
			return null;
		}
		return deikths;
	}
	/*UNDER CONSTRUCTION*/
	public void UpdateDeiktesAndStock(int stockid,String date,String name,double high,double low,double closing,double beta,int Volume) {
		Metoxh m=createMetoxh(stockid,name, date, high, low, closing,beta,Volume);
		Deiktes d=createDeikth(stockid,high,low,closing);
		m.setDeiktes(d);
		d.setMetoxh(m);
		em.persist(m);
	}
}
