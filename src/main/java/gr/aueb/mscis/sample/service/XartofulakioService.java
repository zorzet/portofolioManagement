package gr.aueb.mscis.sample.service;

/* Περίπτωση Χρήσης 2 */

import java.util.*;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Metoxh;
import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Xartofulakio;

public class XartofulakioService {
	private static EntityManager em;

	public XartofulakioService(EntityManager em) {
		this.em = em;
	}

/////////////////////////////////////////////////////////////////////
//	  Ο ΔΧ επιλέγει πελάτη από τους διαθέσιμους πελάτες του.      //
////////////////////////////////////////////////////////////////////	

	/**
	 * Για ένα Διαχειριστή επιστρέφει μια λίστα με τα χαρτοφυλάκια που αυτός
	 * διαχειρίζεται
	 * 
	 * @param DXId
	 * @return λίστα Χαρτοφυλακίων
	 */
	@SuppressWarnings("unchecked")
	public static List<Xartofulakio> findXartofulakiaById(int DXId) {
		List<Xartofulakio> results = null;
		results = em.createQuery("select x from Xartofulakio x where x.dx.DXId like :DXId ").setParameter("DXId", DXId)
				.getResultList();

		return results;
	}

/////////////////////////////////////	
	/**
	 * Για ένα συγκεκριμένο Διαχειριστή, εκτυπώνει στην οθόνη όλα τα στοιχεία των
	 * πελατών που του ανήκουν
	 * 
	 * @param DXId
	 */
	public void ShowCustomers(int DXId) {
		String message = "";
		List<Xartofulakio> results = null;
		results = findXartofulakiaById(DXId);
		for (Xartofulakio Xartofulakio : results) {
			message = message + "CID " + Xartofulakio.getCus().getCustomerId() + " XID " + Xartofulakio.getXid()
					+ " NAME  " + Xartofulakio.getCus().getName() + " SURNAME " + Xartofulakio.getCus().getSurname()
					+ " ADT " + Xartofulakio.getCus().getADT() + " AFM " + Xartofulakio.getCus().getAFM() + " EMAIL "
					+ Xartofulakio.getCus().getEmail() + " INVESTED AMOUNT " + Xartofulakio.getCus().getInvestAmount();
		}

		System.out.println(message);
	}

///////////////////////////////////////////////////////////////////////////////////////////////	
//                  Το σύστημα εμφανίζει το επιλεγμένο χαρτοφυλάκιο του πελάτη (τρέχουσες θέσεις, beta, ιστορικό).	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Χρησιμοποείται για να υπολογίσει το beta του χαρτοφυλακιου άθροισμα(beta
	 * metoxhs* τωρινη τιμή μετοχής* τεμάχια μετοχής)/τωρινη τιμή μετοχής* τεμάχια
	 * μετοχής για τις μετοχές που έχουμε στο χαρτοφυλάκιο μας
	 * 
	 * @param xid   DX
	 * @param cusid CS
	 * @param date
	 * @return double Χρησιμοποιεί και τις δύο επόμενες συναρτήσεις
	 */
	@SuppressWarnings("unchecked")
	public static double CalculateBeta(int xid, int cusid, String date) {
		double ari8mhths = 0.0, paranomasths = 0.0;

		// PARE OLA TA XARTOFULAKIA TOU DX
		List<Xartofulakio> xlist = null;
		Set<Transaction> translist = null;
		xlist = findXartofulakiaById(xid);

		for (int i = 0; i < xlist.size(); i++) {
			if ((xlist.get(i).getCus().getCustomerId()) == cusid) {
				translist = xlist.get(i).getTransactions();
				System.out.println(translist.size());
			}
		}

		// VRES TA TRANSACTION POU EGINAN & EINAI OPEN
		for (Transaction t : translist) {
			if ((t.getState().equalsIgnoreCase("open"))) {
				ari8mhths = betaOfStock(t.getStock(), date) * priceOfStock(t.getStock(), date) * t.getUnits();
				paranomasths = priceOfStock(t.getStock(), date) * t.getUnits();
			}
		}

		if (paranomasths != 0)
			return (ari8mhths / paranomasths);
		else
			throw new java.lang.RuntimeException("Action not valid, no stocks in portofolio");
	}

////////////////////////////////////////////////////////	
	/**
	 * Γυρναει το beta καθε μετοχής για τη μέρα που ζητήθηκε από τον πίνακα των
	 * μετοχών
	 * 
	 * @param name
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double betaOfStock(String name, String date) {
		double betaOfStock = 0.0;
		List<Metoxh> ml = null;
		ml = em.createQuery("select m from Metoxh m where m.Name like :Name").setParameter("Name", name)
				.getResultList();
		for (int i = 0; i < ml.size(); i++) {
			if (ml.get(i).getDate().equalsIgnoreCase(date)) {
				betaOfStock = ml.get(i).getBeta();
			}
		}
		return betaOfStock;
	}

/////////////////////////////////////////////////////////	
	/**
	 * Γυρναει τη τιμή ή το κλείσιμο αντίστοιχα καθε μετοχής για τη μέρα που
	 * ζητήθηκε από τον πίνακα των μετοχών
	 * 
	 * @param name
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double priceOfStock(String name, String date) {
		double priceOfStock = 0.0;
		List<Metoxh> ml = null;
		ml = em.createQuery("select m from Metoxh m where m.Name like :Name").setParameter("Name", name)
				.getResultList();
		for (int i = 0; i < ml.size(); i++) {
			if (ml.get(i).getDate().equalsIgnoreCase(date)) {
				priceOfStock = ml.get(i).getClosing();
			}
		}
		return priceOfStock;
	}

///////////////////////////////////////////////////////	
	/**
	 * Για ένα συγκεκριμένο Διαχειριστή για έναν του Πελάτη, επιστρέφει τα στοιχεία
	 * του Πελάτη σε ένα string
	 * 
	 * @param DXId
	 * @param Cusid
	 * @return string
	 */
	public String ShowXartofulakioPelath(int DXId, int Cusid) {
		String eikonaXartofulakiou = null;
		List<Xartofulakio> results = findXartofulakiaById(DXId);
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getCus().getCustomerId() == Cusid)
				eikonaXartofulakiou = ("name " + results.get(i).getCus().getName() + " surname "
						+ results.get(i).getCus().getSurname() + " ADT " + results.get(i).getCus().getADT() + " AFM "
						+ results.get(i).getCus().getAFM() + " Birthday " + results.get(i).getCus().getBirthDate()
						+ " email " + results.get(i).getCus().getEmail());
		}
		if (eikonaXartofulakiou == null)
			throw new java.lang.RuntimeException("Xartofulakio for this customer could not be retrieved");
		return eikonaXartofulakiou;
	}
//////////////////////////////////////

	/**
	 * Για ένα συγκεκριμένο ΔΧ, για έναν του Πελάτη Επιστρέφει όλες τις συναλλαγές
	 * που έχουν πραγματοποιηθεί για τον Πελάτη
	 * 
	 * @param xid
	 * @param cusid
	 * @return
	 */
/////////////////////////////////////
	public Set<Transaction> ShowTransactionsPerPortofolio(int xid, int cusid) {
		Set<Transaction> transet = new HashSet<Transaction>();
		List<Xartofulakio> xlist = null;

		xlist = findXartofulakiaById(xid);
		if (xlist == null) {
			throw new java.lang.RuntimeException("Transactions for this customer could not be retrieved");
		}

		for (int i = 0; i < xlist.size(); i++) {
			if (xlist.get(i).getCus().getCustomerId() == cusid) {
				transet = xlist.get(i).getTransactions();
			}
		}
		if (transet == null)
			throw new java.lang.RuntimeException("Transactions for this customer could not be retrieved");
		return transet;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Χρησιμοποιεί τις δύο προηγούμενες συναρτήσεις για να επιστρέψει για εναν
	 * Πελάτη Τα στοιχεία του, τις συναλλαγές που έχει κάνει (τη θέση του) και το
	 * beta χαερτοφυλακίου του
	 * 
	 * @param xid
	 * @param cusid
	 * @return String
	 */
	public String ReturnCustomerImage(int xid, int cusid, String date) {
		String CImage = null, xarakthrismos_xartofulakiou;
		Set<Transaction> t = ShowTransactionsPerPortofolio(xid, cusid);
		if (CalculateBeta(xid, cusid, date) > 1) {
			xarakthrismos_xartofulakiou = "EPI8ETIKO XARTOFULAKIO";
		} else if (CalculateBeta(xid, cusid, date) < 1) {
			xarakthrismos_xartofulakiou = "AMUNTIKO XARTOFULAKIO";
		} else {
			xarakthrismos_xartofulakiou = "OUDETERO XARTOFULAKIO";
		}
		CImage = String.join("-", t.toString()) + " " + ShowXartofulakioPelath(xid, cusid) + " "
				+ xarakthrismos_xartofulakiou;
		return CImage;

	}

//////////////////////////////////////////////////////////////////////////////////////////////////
////                 Ενημέρωση Θέσης Πελάτη
/////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Αναλαμβάνει να κάνει το transact. Κοιτάει αν πρόκειται για αγορά Μετοχής Αν
	 * ναι, αν υπάρχουν τα χρήματα για την Αγορά Αν ναι, αν υπάρχουν ήδη τεμάχια
	 * αυτής της Μετοχής στο Χαρτοφυλάκιο για να γίνει Update κάποιο καινούγιο
	 * transaction και να ενημερωθει το balance Ή αν πρέπει να δημιουργηθεί ένα
	 * καινούργιο Transaction και να ενημερωθει το balance Ή για πώληση Μετοχής Αν
	 * υπάρχουν τεμάχια να πωληθούν Αν ναι, κανε ενα Update το υπάρχων Transaction
	 * και ενημέρωσε το balance Αν όχι βγάλε σχετικό μήνυμα
	 * 
	 * @param TransId
	 * @param cmdType
	 * @param stock
	 * @param units
	 * @param price
	 * @param date
	 * @param state
	 * @param x
	 * @return
	 */
	public Transaction transact(String cmdType, String stock, int units, double price, String date, String state,
			Xartofulakio x) {
		Transaction tran = new Transaction();
		EntityTransaction tx = em.getTransaction();
		
		// an h transaction einai pwlhsh, vres an exeis na poylhsei
		if (cmdType.equalsIgnoreCase("sell")) {
			try {
				tran = CheckForOpenTransaction(stock, x);
			} catch (java.lang.RuntimeException w) {
				throw new java.lang.RuntimeException();
			}

			// an nai poula kai update transaction. kane update balance
			if (tran.getUnits() >= units) {
				tx.begin();
				if (tran.getUnits() == units) {
					tran.setState("closed");
					tran.setUnits(0);
					tran.setDate(date);
					x.getCus().setInvestAmount((x.getCus().getInvestAmount() + (units * price)));
					em.merge(tran);
					em.merge(x);
					tx.commit();
				} else {
					tran.setUnits(tran.getUnits() - units);
					tran.setDate(date);
					x.getCus().setInvestAmount((x.getCus().getInvestAmount() + (units * price)));
					em.merge(tran);
					em.merge(x);
					tx.commit();
				}
			}
		} else {
			// an h transaction einai agora des an exeis xrhmata
			if (CheckForCustomersBalance(x.getCus(), price, units)) {
				tran = CheckForOpenTransaction(stock, x);
				tx.begin();
				if (tran != null) {
					// uparxei trans, kane update transaction kai balance
					tran.setUnits(tran.getUnits() + units);
					tran.setDate(date);
					x.getCus().setInvestAmount((x.getCus().getInvestAmount() - (units * price)));
					em.merge(tran);
					em.merge(x);
					tx.commit();
				} else {
					// an oxi ftiaxe transaction kai kane update balance
					tran = createTransaction(cmdType, stock, units, price, date);
					x.getCus().setInvestAmount((x.getCus().getInvestAmount() - (units * price)));
					em.persist(tran);
					em.merge(x);
					tx.commit();
				}
			} else {
				tx.rollback();
				throw new java.lang.RuntimeException("Action not valid, low balance");
			}
		}
		return tran;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * ΒΟΗΘΗΤΙΚΗ ΓΙΑ ΤΗΝ ΣΥΝΑΡΤΗΣΗ TRANSACT Ελέγει αν για το συνδιασμό χαρτοφυλακίου
	 * (Πελάτη) και Μετοχής, υπάρχει κάποιο Transaction Που να ειναι Open(δηλαδή αν
	 * ο πελάτης διαθέτει ήδη τεμάχια απο αυτή τη μετοχή) Αν ναι επιστρέφει ένα
	 * Instance του Transaction που είναι Open, αλλιώς Null
	 * 
	 * @param Metoxh
	 * @param x
	 * @return Transaction or Null
	 */
	public Transaction CheckForOpenTransaction(String Metoxh, Xartofulakio x) {
		Transaction existing = null;
		for (Transaction trans : x.getTransactions()) {
			if ((trans.getStock().equalsIgnoreCase(Metoxh)) & (trans.getState().equals("open")))
				return trans;
		}
		return existing;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * ΒΟΗΘΗΤΙΚΗ ΓΙΑ ΤΗΝ ΣΥΝΑΡΤΗΣΗ TRANSACT Ελέγχει αν το υπόλοιπο του Πελάτη είναι
	 * αρκετό για να πραγματοποιηθεί αυτή η αγορά. Αν ναι, επιστρέφει true, αλλιώς
	 * επιστρέφει false
	 * 
	 * @param cus
	 * @param price
	 * @param units
	 * @return boolean
	 */
	public boolean CheckForCustomersBalance(Customer cus, double price, double units) {
		if (cus.getInvestAmount() > (units * price)) {
			return true;
		}
		return false;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * ΒΟΗΘΗΤΙΚΗ ΓΙΑ ΤΗΝ ΣΥΝΑΡΤΗΣΗ TRANSACT Δημιουργεί ένα Instance Transaction και
	 * το γυρνάει στην συνάρτηση που θα το κάνει commit στον Manager
	 * 
	 * @param cmdType
	 * @param stock
	 * @param units
	 * @param price
	 * @param date
	 * @param state
	 * @return
	 */
	public Transaction createTransaction(String cmdType, String stock, int units, double price, String date) {
		Transaction tran = new Transaction(cmdType, stock, units, price, date, "open");
		return tran;
	}
}
