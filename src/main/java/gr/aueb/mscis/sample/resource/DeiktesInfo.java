package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Deiktes;
import gr.aueb.mscis.sample.model.Metoxh;

@XmlRootElement
public class DeiktesInfo {
	private int id;
	private Double MKO15;
	private String date;
	private Double MKO80;
	private Double yk20;
	private Double xk20;

	
	public DeiktesInfo() {
		
	}
	
	public DeiktesInfo(int id, String date,Double MKO15, Double MKO80, Double yk20, Double xk20)	{
		this(date, MKO15, MKO80, yk20, xk20);
		this.id = id;
	}
	
	public DeiktesInfo(String date,Double MKO15, Double MKO80, Double yk20, Double xk20) {
		super();
		this.id = 0;
		this.MKO15 = MKO15;
		this.date = date;
		this.MKO80 = MKO80;
		this.yk20=yk20;
		this.xk20=xk20;
	}
	
	public DeiktesInfo(Deiktes dk) {
		super();
		this.id = dk.getId();
		this.MKO15 = dk.getMKO15();
		this.date = dk.getDate();
		this.MKO80 = dk.getMKO80();
		this.yk20 = dk.getYk20();
		this.xk20 = dk.getXk20();
		
	}
	
public Deiktes getDeiktes(EntityManager em) {
		
	    Deiktes dk;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.id != 0) {
			
			dk = em.find(Deiktes.class, this.id);
		}
		else {
			
			dk = new Deiktes();
		}
		
		tx.commit();
		dk.setMKO15(this.MKO15);
		dk.setDate(this.date);
		dk.setMKO80(this.MKO80);
		dk.setYk20(this.yk20);
		dk.setXk20(this.xk20);
	
		
		return dk;
	}
	
	public static DeiktesInfo wrap(Deiktes dk) {
		return new DeiktesInfo(dk);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getMKO15() {
		return MKO15;
	}

	public void setMKO15(Double mKO15) {
		MKO15 = mKO15;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getMKO80() {
		return MKO80;
	}

	public void setMKO80(Double mKO80) {
		MKO80 = mKO80;
	}

	public Double getYk20() {
		return yk20;
	}

	public void setYk20(Double yk20) {
		this.yk20 = yk20;
	}

	public Double getXk20() {
		return xk20;
	}

	public void setXk20(Double xk20) {
		this.xk20 = xk20;
	}
	
	
	
	
}
	
