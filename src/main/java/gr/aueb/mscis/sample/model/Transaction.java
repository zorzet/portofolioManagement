package gr.aueb.mscis.sample.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;

/**Kataxoreitai ka8e sunallagh pou ginetai gia ena pelath
 * Susxetizetai me th class Xartofulakio
 * */


@Entity
@Table(name="Xartofulakio",uniqueConstraints = {
        @UniqueConstraint(columnNames = "TransId")})
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TransId", unique = true, nullable = false)
	private int TransId;
	
	
	@Column(name = "CommandType", length = 512, nullable = false)
	private String CmdType;
	
	@Column(name = "Stock", length = 512, nullable = false)
	private String Stock;
	
	@Column(name = "Units", length = 512, nullable = false)
	private String Units;
	
	@Column(name = "Price", length = 512, nullable = false)
	private double price;
	
	@Column(name = "Date", length = 10, nullable = false)
	private String date;
	

	
	@Column(name = "State", length = 10, nullable = false)
	private String state;
	/* Each transaction belongs to one Xartofulakio */ 
	@ManyToOne(fetch=FetchType.LAZY, 
            cascade= {CascadeType.PERSIST, CascadeType.MERGE}    
            ) 
    @JoinColumn(name="Xartofulakiono")
    Xartofulakio Xartofulakio;
    
    
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

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
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
	
	//open an exw metoxes sth katoxh mou, closed an tis poulhsa kai den exw, pending otan mpainei mexri na katalavw ti einai
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	public Transaction(String cmdType, String stock, String units, double price, String date) {
		super();
		CmdType = cmdType;
		Stock = stock;
		Units = units;
		this.price = price;
		this.date = date;
		this.state="pending";
	}
    public boolean isPending() {
        return state.equalsIgnoreCase("pending");
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CmdType == null) ? 0 : CmdType.hashCode());
		result = prime * result + ((Stock == null) ? 0 : Stock.hashCode());
		result = prime * result + TransId;
		result = prime * result + ((Units == null) ? 0 : Units.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Transaction other = (Transaction) obj;
		if (CmdType == null) {
			if (other.CmdType != null)
				return false;
		} else if (!CmdType.equals(other.CmdType))
			return false;
		if (Stock == null) {
			if (other.Stock != null)
				return false;
		} else if (!Stock.equals(other.Stock))
			return false;
		if (TransId != other.TransId)
			return false;
		if (Units == null) {
			if (other.Units != null)
				return false;
		} else if (!Units.equals(other.Units))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
}
