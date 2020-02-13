package gr.aueb.mscis.sample.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name="Metoxh")
public class Metoxh {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "Name", length = 512, nullable = false)
	private String Name;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "High", length = 10, nullable = false)
	private Float High;
	
	@Column(name = "Low", length = 10, nullable = false)
	private Float Low;
	
	@Column(name = "Closing", length = 10, nullable = false)
	private Float Closing;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public Metoxh() {
	}

	public Metoxh(int id, String name, Date date, Float high, Float low, Float closing) {
		this.id = id;
		Name = name;
		this.date = date;
		High = high;
		Low = low;
		Closing = closing;
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
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metoxh other = (Metoxh) obj;
		if (Closing == null) {
			if (other.Closing != null)
				return false;
		} else if (!Closing.equals(other.Closing))
			return false;
		if (High == null) {
			if (other.High != null)
				return false;
		} else if (!High.equals(other.High))
			return false;
		if (Low == null) {
			if (other.Low != null)
				return false;
		} else if (!Low.equals(other.Low))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	/*Metoxh get_stock(String Name,Date date ) {
	}
	*/
}
