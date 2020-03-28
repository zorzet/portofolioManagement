package gr.aueb.mscis.sample.model;

import java.util.Set;

import javax.persistence.*;

/**Kataxoreitai ka8e sunallagh pou ginetai gia ena pelath
 * Susxetizetai me th class Xartofulakio
 * */

@Entity
@Table(name="Transactions",uniqueConstraints = {
        @UniqueConstraint(columnNames = "TransId")})
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransId", unique = true, nullable = false)
	private int TransId;
	
	
	@Column(name = "CommandType", length = 512, nullable = false)
	private String CmdType;
	
	@Column(name = "Stock", length = 512, nullable = false)
	private String Stock;
	
	@Column(name = "Units", length = 512, nullable = false)
	private int Units;
	
	@Column(name = "Price", length = 512, nullable = false)
	private double price;
	
	@Column(name = "Date", length = 10, nullable = false)
	private String date;
		
	@Column(name = "State", length = 10, nullable = false)
	private String state;
	
	/* Each transaction belongs to one Xartofulakio */ 
	@ManyToOne(cascade = CascadeType.ALL,
	  		   fetch=FetchType.LAZY)
    @JoinColumn(name="XartofulakioNo")
    Xartofulakio Xartofulakio;
	
    public Xartofulakio getXartofulakio() {
    	return Xartofulakio;
    }
    
    public void setXartofulakio(Xartofulakio x) {
        if (this.Xartofulakio != null) {
            this.Xartofulakio.friendTransactions().remove(this);
        }
        //this.Xartofulakio = x;
        if (this.Xartofulakio != null) {
            this.Xartofulakio.friendTransactions().add(this);
        }
    	Xartofulakio = x;
    }
    
    public void addXartofulakio(Xartofulakio x) {
        if (x != null) {
            x.addTransaction(this);
        }
    }
    
    public void removeXartofulakio(Xartofulakio x) {
    	if (x != null) {
    		x.removeTransaction(this);
    	}
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
	
	//open an exw metoxes sth katoxh mou, closed an tis poulhsa kai den exw, pending otan mpainei mexri na katalavw ti einai
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Transaction() {
	}
	
	public Transaction(String cmdType, String stock, int units, double price, String date, String state) {
		super();	
		this.CmdType = cmdType;
		this.Stock = stock;
		this.Units = units;
		this.price = price;
		this.date = date;
		this.state= state;
	}
	
    public boolean isPending() {
        return state.equalsIgnoreCase("pending");
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
		if (Units != other.Units)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [CmdType=" + CmdType + ", Stock=" + Stock + ", Units=" + Units
				+ ", price=" + price + ", date=" + date + ", state=" + state + "]";
	}
    

		
}
