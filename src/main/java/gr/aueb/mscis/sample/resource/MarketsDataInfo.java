package gr.aueb.mscis.sample.resource;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.MarketsData;

@XmlRootElement
public class MarketsDataInfo {
	private int MDId;
	private String date;
	private double Opening;
	private double Closing;
	private double Max;
	private double Min;
	
	public MarketsDataInfo() {
	}
	
	public MarketsDataInfo(String date,double Opening,double Closing,double Max,double Min) {
		super();
		this.date=date;
		this.Opening=Opening;
		this.Closing=Closing;
		this.Max=Max;
		this.Min=Min;
	}

	public MarketsDataInfo(MarketsData md) {
		super();
		this.Closing=md.getClosing();
		this.date=md.getDate();
		this.Max=md.getMax();
		this.MDId=md.getMDId();
		this.Min=md.getMin();

	}
	
	public int getMDId() {
		return MDId;
	}

	public void setMDId(int mDId) {
		MDId = mDId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getOpening() {
		return Opening;
	}

	public void setOpening(double opening) {
		Opening = opening;
	}

	public double getClosing() {
		return Closing;
	}

	public void setClosing(double closing) {
		Closing = closing;
	}

	public double getMax() {
		return Max;
	}

	public void setMax(double max) {
		Max = max;
	}

	public double getMin() {
		return Min;
	}

	public void setMin(double min) {
		Min = min;
	}
	
	public MarketsData getMarketsData(EntityManager em) {
		
		MarketsData md;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.MDId != 0) {
			
			md = em.find(MarketsData.class, this.MDId);
		}
		else {
			
			md = new MarketsData();
		}
		
		tx.commit();
		md.setClosing(this.Closing);
		md.setDate(this.date);
		md.setMax(this.Max);
		md.setMin(this.Min);
		md.setOpening(Opening);
		return md;
	}
	
	public static MarketsDataInfo wrap(MarketsData md) {
		return new MarketsDataInfo(md);
	}

}
