package gr.aueb.mscis.sample.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.*;
import java.util.HashSet;

@Entity
@Table(name="Metoxh",uniqueConstraints = {
        @UniqueConstraint(columnNames = "StockId")})
public class Metoxh {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "StockId", unique = true, nullable = false)
	private int StockId;
	
	@Column(name = "Name", length = 512, nullable = false)
	private String Name;

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name = "High", length = 10, nullable = false)
	private Float High;
	
	@Column(name = "Low", length = 10, nullable = false)
	private Float Low;
	
	@Column(name = "Closing", length = 10, nullable = false)
	private Float Closing;

	@Column(name = "Beta", length = 10, nullable = false)
	private Float Beta;
	
	@Column(name = "Volume", length = 10, nullable = false)
	private Float Volume;
	
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

	public Float getHigh() {
		return High;
	}

	public void setHigh(Float high) {
		High = high;
	}

	public Float getLow() {
		return Low;
	}

	public void setLow(Float low) {
		Low = low;
	}

	public Float getClosing() {
		return Closing;
	}

	public void setClosing(Float closing) {
		Closing = closing;
	}

	public Float getBeta() {
		return Beta;
	}

	public void setBeta(Float beta) {
		Beta = beta;
	}

	public Metoxh() {
	}

	public Metoxh(int id, String name, String date, Float high, Float low, Float closing,Float beta,Float Volume) {
		this.StockId = id;
		this.Name = name;
		this.date = date;
		this.High = high;
		this.Low = low;
		this.Closing = closing;
		this.Beta=beta;
		this.Volume=Volume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Closing == null) ? 0 : Closing.hashCode());
		result = prime * result + ((High == null) ? 0 : High.hashCode());
		result = prime * result + ((Low == null) ? 0 : Low.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + StockId;
		return result;
	}


	/*Metoxh get_stock(String Name,Date date ) {
	}
	*/
}
