package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;

@XmlRootElement
public class XartofulakioInfo {
	private int XId;
	private String trexousathesi;

	
	public XartofulakioInfo() {
		
	}
	
	public XartofulakioInfo(int XId, String trexousathesi) {
		this(trexousathesi);
		this.XId=XId;
	}
	
	public XartofulakioInfo(String trexousathesi) {
		this.trexousathesi = trexousathesi;
		
	}
	
	public XartofulakioInfo(Xartofulakio x) {
		super();
		this.trexousathesi = x.getTrexousathesi();
		
	}
	
	
	public Xartofulakio getXartofulakio(EntityManager em) {
		
		Xartofulakio x;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.XId != 0) {
			
			x = em.find(Xartofulakio.class, this.XId);
		}
		else {
			
			x = new Xartofulakio();
		}
		
		tx.commit();
		x.setTrexousathesi(this.trexousathesi);
		
		
		return x;
	}
	
	public static XartofulakioInfo wrap(Xartofulakio x) {
		return new XartofulakioInfo(x);
	}

}
