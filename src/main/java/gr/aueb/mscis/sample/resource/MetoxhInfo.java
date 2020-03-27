package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Metoxh;

@XmlRootElement
public class MetoxhInfo {
	private int StockId;
	private String Name;
	private String date;
	private Double High;
	private Double Low;
	private Double Closing;
	private Double Beta;
	private int Volume;
	
	public MetoxhInfo() {
		
	}
	
	public MetoxhInfo(int StockId, String Name, String date, Double High, Double Low, Double Closing, Double Beta, int Volume) {	
		this(Name, date, High, Low, Closing, Beta, Volume);
		this.StockId = StockId;
	}
	
	public MetoxhInfo(String Name, String date, Double High, Double Low, Double Closing, Double Beta, int Volume) {
		super();
		this.StockId = 0;
		this.Name = Name;
		this.date = date;
		this.High = High;
		this.Low = Low;
		this.Closing = Closing;
		this.Beta = Beta;
		this.Volume = Volume;
	}
	
	public MetoxhInfo(Metoxh m) {
		super();
		this.StockId = m.getStockId();
		this.Name = m.getName();
		this.date = m.getDate();
		this.High = m.getHigh();
		this.Low = m.getLow();
		this.Closing = m.getClosing();
		this.Beta = m.getBeta();
		this.Volume = m.getVolume();
	}
	
	public int getStockId() {
		return StockId;
	}
	
	public void setStockId(int StockId) {
		this.StockId = StockId;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getHigh() {
		return High;
	}

	public void setHigh(Double High) {
		this.High = High;
	}

	public Double getLow() {
		return Low;
	}

	public void setLow(Double Low) {
		this.Low = Low;
	}

	public Double getClosing() {
		return Closing;
	}

	public void setClosing(Double Closing) {
		this.Closing = Closing;
	}

	public Double getBeta() {
		return Beta;
	}

	public void setBeta(Double Beta) {
		this.Beta = Beta;
	}

	public int getVolume() {
		return Volume;
	}

	public void setVolume(int Volume) {
		this.Volume = Volume;
	}
	
	public Metoxh getMetoxh(EntityManager em) {
		
		Metoxh m;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.StockId != 0) {
			
			m = em.find(Metoxh.class, this.StockId);
		}
		else {
			
			m = new Metoxh();
		}
		
		tx.commit();
		m.setName(this.Name);
		m.setDate(this.date);
		m.setHigh(this.High);
		m.setLow(this.Low);
		m.setClosing(this.Closing);
		m.setBeta(this.Beta);
		m.setVolume(this.Volume);
		
		return m;
	}
	
	public static MetoxhInfo wrap(Metoxh m) {
		return new MetoxhInfo(m);
	}

}
