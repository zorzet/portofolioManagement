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
	
	
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει την εικόνα του FTSE εκείνη τη στιγμή 
/////////////////////////////////////////////////////////////////	
	/**
	 * Χτυπάει το site της Ναυτεμπορικής και γυρνάει real-time data σε μορφή string
	 * @return
	 */
	public String getOnlineMarketImage(){
		String MarketImage="something";
		return MarketImage;
	}
	
/////////////////////////////////////////////////////////////////////
//Εμφανίζεται κατάλληλο μήνυμα ενημέρωσης
//Εμφανίζεται η εικόνα του FTSE για όλη την προηγούμενη εργάσιμη και η τιμή κλεισίματος.
////////////////////////////////////////////////////////////////////
    /**
     * Χτυπάει το πίνακα MarketsData και γυρνάει σε ένα String την τελευταία εικόνα που έχει αποθηκευτεί από την αγορά
     * @return String
     */
	public String getOfflineMarketImage(){	
		String MarketImage;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
        Query query = em.createQuery("select m from MarketsData m order by m.date desc", MarketsData.class).setMaxResults(1);
        MarketImage=(String) query.getSingleResult().toString();
        tx.commit();
        System.out.println("Stock Market is closed");
        return MarketImage;
	}
	
/////////////////////////////////////////////////////////////////////
//Το σύστημα εμφανίζει τα κλεισίματα του FTSE για το επιλεγμένο διάστηματος.
////////////////////////////////////////////////////////////////////	
	/**
	 * Παίρνει τον αριθμό των ημερών που του έδωσε ο χρήστης (από πτροεπιλογή 1 ή 3 μήνες)
	 * Επιστρέφει σε μια λίστα της εικόνα κλεισίματος της αγοράς για κάθε μια από αυτές τις μέρες
	 * @param days
	 * @return List
	 */
	public List<MarketsData> ShowMarketHistory(int days)  throws RuntimeException{
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
	/** ΔΕ ΧΡΗΣΙΜΟΠΟΙΕΙΤΑΙ. ΤΑ ΠΑΡΑΠΑΝΩ ΔΕΔΟΜΕΝΑ ΧΡΗΣΙΜΟΠΟΙΟΥΝΤΑΙ ΚΥΡΙΩΣ ΓΙΑ ΤΗ ΣΧΕΔΙΑΣΗ ΓΡΑΦΗΜΑΤΩΝ ΣΤΟ UI
	 * Παίρνει ένα- ένα τα περιεχόμενα της προγούμενης λίστα και τα τυπώνει στην σελίδα 
	 * @param data
	 */
	public void showResults(List<MarketsData> data) {
		for (int j = 0; j < data.size(); j++) {
			System.out.print("STOCK MARKETS IMAGE AT "+data.get(j).getDate()+" OPENING "+data.get(j).getOpening()+" CLOSING "+data.get(j).getClosing()
					+" HIGH "+data.get(j).getMax()+" LOW "+data.get(j).getMin()+"'\n'");
		}
	}
}
