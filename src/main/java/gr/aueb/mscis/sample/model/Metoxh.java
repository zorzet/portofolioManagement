package gr.aueb.mscis.sample.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import java.util.*;
import java.util.HashSet;

@Entity
@Table(name="Metoxh",uniqueConstraints = {
        @UniqueConstraint(columnNames = "StockId")})
public class Metoxh {
	
	@Id
	@Column(name = "StockId", unique = true, nullable = false)
	private int StockId;
	
	@Column(name = "Name", length = 512, nullable = false)
	private String Name;

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name = "High", length = 10, nullable = false)
	private Double High;
	
	@Column(name = "Low", length = 10, nullable = false)
	private Double Low;
	
	@Column(name = "Closing", length = 10, nullable = false)
	private Double Closing;

	@Column(name = "Beta", length = 10, nullable = false)
	private Double Beta;
	
	@Column(name = "Volume", length = 10, nullable = false)
	private int Volume;
	
	public int getStockId() {
		return StockId;
	}

	public void setStockId(int id) {
		this.StockId = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public void setHigh(Double high) {
		High = high;
	}

	public Double getLow() {
		return Low;
	}

	public void setLow(Double low) {
		Low = low;
	}

	public Double getClosing() {
		return Closing;
	}

	public void setClosing(Double closing) {
		Closing = closing;
	}

	public Double getBeta() {
		return Beta;
	}

	public void setBeta(Double beta) {
		Beta = beta;
	}

	public int getVolume() {
		return Volume;
	}

	public void setVolume(int volume) {
		Volume = volume;
	}
   
    
    @OneToOne(orphanRemoval=true,cascade = CascadeType.ALL,
    		  mappedBy="Metoxh", fetch=FetchType.LAZY)
    private Deiktes deikths;
	
    public void setDeiktes(Deiktes deiktes) {
        this.deikths = deiktes;
    }
    public Deiktes getDeiktes() {
    	return this.deikths;
    }
    
	public Metoxh() {
	}

	public Metoxh(int id, String name, String date, Double high, Double low, Double closing,Double beta,int Volume) {
		this.StockId = id;
		this.Name = name;
		this.date = date;
		this.High = high;
		this.Low = low;
		this.Closing = closing;
		this.Beta=beta;
		this.Volume=Volume;
	
	
	}



}
