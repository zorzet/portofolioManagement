package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import gr.aueb.mscis.sample.model.*;

@XmlRootElement
public class XartofulakioInfo {
	private int XId;
	private String trexousathesi;
	private CustomerInfo cus;
	private Set<Transaction> trans = new HashSet<Transaction>();
	private DXInfo dx;

	public XartofulakioInfo() {

	}

	public XartofulakioInfo(String trexousathesi, CustomerInfo cus, DXInfo dx, Set<Transaction> trans) {
		super();
		this.trexousathesi = trexousathesi;
		this.cus = cus;
		this.dx = dx;
		this.trans = trans;
	}

	public XartofulakioInfo(Xartofulakio x) {
		super();
		this.trexousathesi = x.getTrexousathesi();
		this.cus = CustomerInfo.wrap(x.getCus());
		this.dx = DXInfo.wrap(x.getDX());
		this.trans = x.getTransactions();
	}

	public int getXId() {
		return XId;
	}

	public void setXId(int xId) {
		XId = xId;
	}

	public String getTrexousathesi() {
		return trexousathesi;
	}

	public void setTrexousathesi(String trexousathesi) {
		this.trexousathesi = trexousathesi;
	}

	public CustomerInfo getCus() {
		return cus;
	}

	public void setCus(CustomerInfo cus) {
		this.cus = cus;
	}

	public DXInfo getDx() {
		return dx;
	}

	public void setDx(DXInfo dx) {
		this.dx = dx;
	}

	public Set<Transaction> getTrans() {
		return trans;
	}

	public void setTrans(Set<Transaction> trans) {
		this.trans = trans;
	}

	public Xartofulakio getXartofulakio(EntityManager em) {

		Xartofulakio x;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (this.XId != 0) {

			x = em.find(Xartofulakio.class, this.XId);
		} else {

			x = new Xartofulakio();
		}

		tx.commit();
		x.setTrexousathesi(this.trexousathesi);
		x.setCus(this.cus.getCustomer(em));
		x.setDX(this.dx.getDX(em));
		if (this.trans.size() > 0) {
			for (Transaction t : this.trans) {
				x.addTransaction(t);
			}
		}
		return x;
	}

	public static XartofulakioInfo wrap(Xartofulakio x) {
		XartofulakioInfo asd = new XartofulakioInfo(x);
		return asd;
	}

}
