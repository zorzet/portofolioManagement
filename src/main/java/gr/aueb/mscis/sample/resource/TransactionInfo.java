package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Transaction;

@XmlRootElement
public class TransactionInfo {
	private int TransId;
	private String CmdType;
	private String Stock;
	private int Units;
	private double price;
	private String date;
	private String state;
	
	
	public TransactionInfo() {
		
	}
	
	public TransactionInfo(int TransId,String cmdType, String stock, int units, double price, String date, String state) {	
		this(cmdType, stock, units, price, date, state);
		this.TransId = TransId;
	}
	
	public TransactionInfo(String stock, int units, double price, String date, String state) {
		super();	
		
		this.Stock = stock;
		this.Units = units;
		this.price = price;
		this.date = date;
		this.state= state;
	}
	
	public TransactionInfo(Transaction t) {
		super();
		this.CmdType = t.getCmdType();
		this.Stock = t.getStock();
		this.date = t.getDate();
		this.Units = t.getUnits();
		this.price = t.getPrice();
		this.state = t.getState();
		
	}
	
	
	
	public Transaction getTransaction(EntityManager em) {
		
		Transaction t;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.TransId != 0) {
			
			t = em.find(Transaction.class, this.TransId);
		}
		else {
			
			t = new Transaction();
		}
		
		tx.commit();
		t.setStock(this.Stock);
		t.setDate(this.date);
		t.setUnits(this.Units);
		t.setPrice(this.price);
		t.setState(this.state);
	
		
		return t;
	}
	
	public static TransactionInfo wrap(Transaction t) {
		return new TransactionInfo(t);
	}

	public int getTransId() {
		return TransId;
	}

	public void setTransId(int transId) {
		TransId = transId;
	}

	public String getCmdType() {
		return CmdType;
	}

	public void setCmdType(String cmdType) {
		CmdType = cmdType;
	}

	public String getStock() {
		return Stock;
	}

	public void setStock(String stock) {
		Stock = stock;
	}

	public int getUnits() {
		return Units;
	}

	public void setUnits(int units) {
		Units = units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
