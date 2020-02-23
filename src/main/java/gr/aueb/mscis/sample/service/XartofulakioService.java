package gr.aueb.mscis.sample.service;

/* Περίπτωση Χρήσης 2 */

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Query;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Deiktes;
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

	public void ShowCustomers(int DXId) {
		List<Xartofulakio> results = null;
		results = findXartofulakiaById(DXId);
		// peta ta se ena string
		for (Xartofulakio Xartofulakio : results) {
			System.out.println("CID " + Xartofulakio.getCus().getCustomerId() + " XID " + Xartofulakio.getXid()
					+ " NAME  " + Xartofulakio.getCus().getName() + " SURNAME " + Xartofulakio.getCus().getSurname()
					+ " ADT " + Xartofulakio.getCus().getADT() + " AFM " + Xartofulakio.getCus().getAFM() + " EMAIL "
					+ Xartofulakio.getCus().getEmail() + " INVESTED AMOUNT " + Xartofulakio.getCus().getInvestAmount());
		}
		
	}

	/* gurise ta stoixeia pou exeis gia sugkekrimeno xartofulakio */
	public String ShowXarofulakioPelath(int DXId, int Cusid) {
		String eikonaXartofulakiou = null;
		List<Xartofulakio> results=findXartofulakiaById(DXId);
		for(int i=0; i<results.size(); i++) {
			if(results.get(i).getCus().getCustomerId()==Cusid)	
				eikonaXartofulakiou = ("name " + results.get(i).getCus().getName() + " surname " + results.get(i).getCus().getSurname() + " ADT "
				+ results.get(i).getCus().getADT() + " AFM " + results.get(i).getCus().getAFM() + " Birthday " + results.get(i).getCus().getBirthDate()
				+ " email " + results.get(i).getCus().getEmail());
		}
		if(eikonaXartofulakiou.isEmpty())
			throw new java.lang.RuntimeException("Xartofulakio for this customer could not be retrieved");
		return eikonaXartofulakiou;
	}

	
	/* gia ena xartofulakio gurna ola ta transactions pou exoun pragmatopoih8ei */

	public Set<Transaction> ShowTransactionsPerPortofolio(int xid,int cusid) {
		 Set<Transaction> transet=null;
		 List<Xartofulakio> xlist=null;
		 try {
		 xlist=findXartofulakiaById(xid);
		 }
		 catch(Exception e){
			 throw new java.lang.RuntimeException("Transactions for this customer could not be retrieved");
		 }
		 for(int i=0; i<xlist.size(); i++) {
			 if(xlist.get(i).getCus().getCustomerId()==cusid) {
				 transet=xlist.get(i).getTransactions();
			 }
		 }
		 if(transet==null)
			throw new java.lang.RuntimeException("Transactions for this customer could not be retrieved");
		return transet;
	}

	/*
	 * enhmerwse ta stoixeia 8eshs enos sugkekrimenou xartofulakiou praktika kane
	 * ena neo transaction
	 */
	/**
	 * Αναζητά το χαρτοφυλάκιο  με βάση τον χαρτοφυλακίου.
	 * 
	 */
	public Boolean findXartofulakioforCustomer(int cusId) {
		EntityTransaction tx = em.getTransaction();
		Xartofulakio X=null;
		tx.begin();
		try {
		    X= em.find(Xartofulakio.class, X.getCus().getCustomerId() );
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
	
	public Transaction transact(int TransId, String cmdType, String stock, int units, double price, String date, String state,Xartofulakio x) {
		Transaction tran=new Transaction();
		try {
		//an h transaction einai pwlhsh, vres an exeis na poylhsei
			if(cmdType.equalsIgnoreCase("sell")) {
				tran=CheckForOpenTransaction(stock,x);
				//an nai poula kai update transaction. kane update balance
				if(tran!=null) {
					if (tran.getUnits()==units) {
						tran.setState("closed");
						tran.setUnits(0);
						tran.setDate(date);
						x.getCus().setInvestAmount((x.getCus().getInvestAmount()+(units*price)));
						em.merge(tran);
						em.merge(x);
					}
				}else {
				//an oxi vgale mhnuma
					throw new java.lang.RuntimeException("Action not valid. Unavailable units in stock");
				}
			}else {
		//an h transaction einai agora des an exeis xrhmata
				if(CheckForCustomersBalance(x.getCus(),price,units)){
					tran=CheckForOpenTransaction(stock,x);
			   		if(tran!=null) {
					//uparxei trans, kane update transaction kai balance
			   			tran.setUnits(tran.getUnits()+units);
			   			tran.setDate(date);
						x.getCus().setInvestAmount((x.getCus().getInvestAmount()-(units*price)));
						em.merge(tran);
						em.merge(x);
			   		}else {
			   			//an oxi ftiaxe transaction kai kane update balance
			   			tran=createTransaction(TransId, cmdType, stock, units, price, date,"open");
			   			EntityTransaction tx = em.getTransaction();
			   			tx.begin();
	/*		   			Transaction trans = em.createNativeQuery("INSERT INTO Transaction (TransId,CommandType,Stock,Units,Price,Date,State) VALUES (?,?,?,?,?,?,?)")
			   		      .setParameter(1, tran.getCmdType()))
			   		      .setParameter(2, tran.getStock()))
			   		      .setParameter(3, tran.getUnits()))
			   			  .setParameter(3, tran.getUnits())
			   		      .executeUpdate();
//			   		      x.getTransactions().add(tran);*/
			   		      em.persist(tran);
			   		      em.merge(x);
			   		      tx.commit();
			   		}
			   	}else {
					//an oxi vgale mhnuma
					throw new java.lang.RuntimeException("Action not valid, low balance");
				}	
			}
		} catch (Exception e) {
			return null;
		}
		return tran;
	}
	
	/*elegxe an uparxei open Transaction */
	public Transaction CheckForOpenTransaction(String Metoxh,Xartofulakio x){
		Transaction existing=null;
		for(Transaction trans : x.getTransactions()){
				if((trans.getStock().equalsIgnoreCase(Metoxh)) &(trans.getState().equals("open")) )
				return trans;
		}
		return existing;
	}
	
	public boolean CheckForCustomersBalance(Customer cus,double price, double units) {
		if(cus.getInvestAmount()>(units*price)) {
			return true;
		}
		return false;		
	}
	
	
	public Transaction createTransaction(int TransId, String cmdType, String stock, int units, double price, String date, String state) {
		Transaction tran=new Transaction();
		try {
			tran.setCmdType(cmdType);
			tran.setDate(date);
			tran.setPrice(price);
			tran.setState("open");
			tran.setStock(stock);
			tran.setTransId(TransId);
			tran.setUnits(units);

		} catch (Exception e) {
			return null;
		}
		return tran;
	}
	
	
}
