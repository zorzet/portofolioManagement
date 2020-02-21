package gr.aueb.mscis.sample.service;

/* Περίπτωση Χρήσης 6 */

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Query;

import gr.aueb.mscis.sample.model.Transaction;
import gr.aueb.mscis.sample.model.Xartofulakio;

public class XartofulakioService {
	private EntityManager em;

	public XartofulakioService(EntityManager em) {
		this.em = em;
	}

	/*
	 * vres ta xartofulakia pou diaxeirizetai o ka8e DX */

	@SuppressWarnings("unchecked")
	public List<Xartofulakio> findXartofulakiaById(int DXId) {

		List<Xartofulakio> results = null;
		results = em.createQuery("select x from Xartofulakio x where x.XId like :DXId ").setParameter("DXId", DXId)
				.getResultList();
		return results;
	}
	/*
	 * deixnei ta stoixeia twn pelatwn stous opoious anoikoun ta xartofulakia gia
	 * ka8e DX
	 */

	public String ShowCustomers(int DXId) {
		String CustomersList = null;
		List<Xartofulakio> results = null;
		results = findXartofulakiaById(DXId);
		// peta ta se ena string
		for (Xartofulakio Xartofulakio : results) {
			System.out.println("CID " + Xartofulakio.getCus().getCustomerId() + " XID " + Xartofulakio.getXid()
					+ " NAME  " + Xartofulakio.getCus().getName() + " SURNAME " + Xartofulakio.getCus().getSurname()
					+ " ADT " + Xartofulakio.getCus().getADT() + " AFM " + Xartofulakio.getCus().getAFM() + " EMAIL "
					+ Xartofulakio.getCus().getEmail() + " INVESTED AMOUNT " + Xartofulakio.getCus().getInvestAmount());
		}
		return CustomersList;
	}

	/* gurise ta stoixeia pou exeis gia sugkekrimeno xartofulakio */
	/* gia ena xartofulakio gurna ola ta transactions pou exoun pragmatopoih8ei */

	public String ShowPortofolio(int xid) {
		String eikonaXartofulakiou = null;
		Xartofulakio x = null;
		// vres to xartofulakio tou pelath
		x = (Xartofulakio) em.createQuery("select x from Xartofulakio x where x.XId like :DXId ")
				.setParameter("DXId", xid).getSingleResult();
		Set<Transaction> tr = null;
		// pare tis sunallages kai vale tis sto string1
		tr = x.getTransactions();
		StringBuilder joined = new StringBuilder(100000);
		for (Iterator<Transaction> it = tr.iterator(); it.hasNext();) {
			Transaction f = it.next();
			joined.append(" state " + f.getState() + " date " + f.getDate() + " Stock " + f.getStock() + " Date "
					+ f.getPrice() + " Units " + f.getUnits() + " TransactionId " + f.getTransId());
		}
		// pare ta stoixeia tou pelath sto string2
		eikonaXartofulakiou = ("name " + x.getCus().getName() + " surname " + x.getCus().getSurname() + " ADT "
				+ x.getCus().getADT() + " AFM " + x.getCus().getAFM() + " Birthday " + x.getCus().getBirthDate()
				+ " email " + x.getCus().getEmail());
		joined.append(eikonaXartofulakiou);
		// enwse ta string kai peta ta pisw
		return joined.toString();
	}

	/*
	 * enhmerwse ta stoixeia 8eshs enos sugkekrimenou xartofulakiou praktika kane
	 * ena neo transaction
	 */
	/**
	 * Αναζητά το χαρτοφυλάκιο  με βάση τον χαρτοφυλακίου.
	 * 
	 */
	public Boolean findXartofulakio(int XId) {
		EntityTransaction tx = em.getTransaction();
		Xartofulakio X;
		tx.begin();
		try {
		    X= em.find(Xartofulakio.class, XId );
			tx.commit();
		} catch (Exception ex) {
			X = null;
			tx.rollback();
		}

		return X != null;
	}

	/**
	 * ΚΑΝΕΙ ΤΟ TRANSACTION.
	 * ELEGXEI NA UPARCOUN GIA NA POULHSEI
	 * ENHMERWNEI TO STATE TOU TRANSACTION
	 * PROS TO PARON VAZW TH SUNALLAGH SAN PENDING
	 */
	public int transact(String cmdType, String stock, String units, double price, String date, Xartofulakio x) {
		if (x== null) {
			throw new Exception();
		}

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Transaction trans = em.createNativeQuery("INSERT INTO Transaction (TransId,CommandType,Stock,Units,Price,Date,State) VALUES (?,?,?,?,?,?,?)")
	      .setParameter(1, trans.getCmdType()))
	      .setParameter(2, trans.getStock()))
	      .setParameter(3, trans.getUnits()))
		  .setParameter(3, trans.getUnits())
	      .executeUpdate();
		em.persist(trans);
		tx.commit();
		
		return trans.getTransId();

	}
}
