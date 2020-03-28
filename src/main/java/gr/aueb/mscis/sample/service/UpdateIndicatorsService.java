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

/////////////////////////////////////////////////////////////////////
//Το σύστημα αποθηκεύει τα ημερήσια στοιχεία για την κίνηση των μετοχών (υψηλό, χαμηλό, κλείσιμο, άνοιγμα, τζίρος).
/////////////////////////////////////////////////////////////////	

	/**
	 * Δημιουργει instance μετοχής. Χρησιμοποιείται απο την UpdateDeiktesAndStock
	 * 
	 * @param name
	 * @param date
	 * @param high
	 * @param low
	 * @param closing
	 * @param beta
	 * @param Volume
	 * @return Metoxh
	 */
	@SuppressWarnings("unused")
	public Metoxh createMetoxh(String name, String date, Double high, Double low, Double closing, Double beta,
			int Volume) {
		Metoxh m = new Metoxh(name, date, high, low, closing, beta, Volume);
		return m;
	}

	/**
	 * Δημιουργει instance δείκτη. Χρησιμοποιείται απο την UpdateDeiktesAndStock
	 * 
	 * @param date
	 * @param Name
	 * @param high
	 * @param low
	 * @param closing
	 * @return Deiktes
	 */
	public Deiktes createDeikth(String date, String Name, double high, double low, double closing) {
		double ghigh = GetLast19HighsOfStock(Name);
		double glow = GetLast19LowsOfStock(Name);
		double gclosing14 = GetLast14ClosingsOfStock(Name);
		double gclosing79 = GetLast79ClosingsOfStock(Name);
		Deiktes d = new Deiktes(date, ((gclosing14 + closing) / 30), ((gclosing79 + closing) / 80),
				((ghigh + high) / 20), ((glow + low) / 20));
		return d;
	}

	/**
	 * Enhmrwnei sth vash tous metoxes kai tous deiktes
	 * 
	 * @param name
	 * @param date
	 * @param high
	 * @param low
	 * @param closing
	 * @param beta
	 * @param Volume
	 * @param date
	 * @return
	 */
	public int UpdateDeiktesAndStock(String name, String date, Double high, Double low, Double closing, Double beta,
			int Volume) {
		Metoxh m = createMetoxh(name, date, high, low, closing, beta, Volume);
		Deiktes d = createDeikth(date, m.getName(), m.getHigh(), m.getLow(), m.getClosing());
		d.setMetoxh(m);
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(d);
			tx.commit();
			return 0;
		} catch (Exception e) {
			return -1;
		}
		// return d;
	}

/////////////////////////////////////////////////////////////////////
//Το σύστημα υπολογίζει τον κινητό μέσο όρο 15 ημερών (ΜΚΟ15) για όλες τις μετοχές ξεχωριστά και τον αποθηκεύει.
////////////////////////////////////////////////////////////////////	
	/**
	 * Παίνρει τα τελευταία 14 κλεισίματα της μετοχής, με σκοπό να πάρει και το
	 * σημερινό και να υποκλογίσει τον ΜΚΟ15. Χρησιμοποιείται απο την Deiktes
	 * createDeikth
	 * 
	 * @param Name
	 * @return ΜΚΟ14
	 */
	@SuppressWarnings("unchecked")
	public double GetLast14ClosingsOfStock(String Name) {
		double Closings14 = 0.0;
		List<Metoxh> mtxlist = null;
		mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name").setParameter("Name", Name)
				.setMaxResults(14).getResultList();
		if (mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}

		for (int i = 0; i < mtxlist.size(); i++) {
			Closings14 += mtxlist.get(i).getClosing();
		}
		if (Closings14 == 0) {
			throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
		}
		return (Closings14);
	}

/////////////////////////////////////////////////////////////////////
//Το σύστημα υπολογίζει τον κινητό μέσο όρο 80 ημερών (ΜΚΟ80) για όλες τις μετοχές ξεχωριστά και τον αποθηκεύει.
////////////////////////////////////////////////////////////////////	
	/**
	 * Παίνρει τα τελευταία 79 κλεισίματα της μετοχής, με σκοπό να πάρει και το
	 * σημερινό και να υποκλογίσει τον ΜΚΟ80. Χρησιμοποιείται απο την Deiktes
	 * createDeikth
	 * 
	 * @param Name
	 * @return ΜΚΟ79
	 */
	@SuppressWarnings("unchecked")
	public double GetLast79ClosingsOfStock(String Name) {
		double Closings79 = 0.0;
		List<Metoxh> mtxlist = null;
		mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name").setParameter("Name", Name)
				.setMaxResults(79).getResultList();
		if (mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}

		for (int i = 0; i < mtxlist.size(); i++) {
			Closings79 += mtxlist.get(i).getClosing();
		}
		if (Closings79 == 0) {
			throw new java.lang.RuntimeException("MKO30 COULD NOT BE CALCULATED");
		}
		return Closings79;
	}
/////////////////////////////////////////////////////////////////////
//Το σύστημα υπολογίζει το υψηλό 20 ημερών (yk20) και το χαμηλό 20 ημερών (xk20) για όλες τις μετοχές ξεχωριστά και τα αποθηκεύει.
////////////////////////////////////////////////////////////////////	

	/**
	 * Παίνρει τα τελευταία 19 Υψηλά της μετοχής, με σκοπό να πάρει και το σημερινό
	 * και να υποκλογίσει τον ΥΚ20. Χρησιμοποιείται απο την Deiktes createDeikth
	 * 
	 * @param Name
	 * @return ΥΚ19
	 */
	@SuppressWarnings("unchecked")
	public double GetLast19HighsOfStock(String Name) {
		double Highs19 = 0.0;
		List<Metoxh> mtxlist = null;
		mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name").setParameter("Name", Name)
				.setMaxResults(19).getResultList();
		if (mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}

		for (int i = 0; i < mtxlist.size(); i++) {
			Highs19 = mtxlist.get(i).getHigh() + Highs19;
		}
		if (Highs19 == 0) {
			throw new java.lang.RuntimeException("HIGH20 COULD NOT BE CALCULATED");
		}
		return (Highs19);
	}

	/**
	 * 
	 * Παίνρει τα τελευταία 19 Χαμηλά της μετοχής, με σκοπό να πάρει και το σημερινό
	 * και να υποκλογίσει τον ΧΚ20. Χρησιμοποιείται απο την Deiktes createDeikth
	 * 
	 * @param Name
	 * @return ΧΚ19
	 */
	@SuppressWarnings("unchecked")
	public double GetLast19LowsOfStock(String Name) {
		double Lows19 = 0.0;
		List<Metoxh> mtxlist = null;
		mtxlist = em.createQuery("select m from Metoxh m where m.Name like :Name").setParameter("Name", Name)
				.setMaxResults(19).getResultList();
		if (mtxlist.isEmpty()) {
			throw new java.lang.RuntimeException("NO RECORDS FOUND");
		}

		for (int i = 0; i < mtxlist.size(); i++) {
			Lows19 += mtxlist.get(i).getLow();
		}
		if (Lows19 == 0) {
			throw new java.lang.RuntimeException("LOW20 COULD NOT BE CALCULATED");
		}
		return (Lows19);
	}

}
