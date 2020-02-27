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
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@ManyToOne(fetch=FetchType.LAZY, 
            cascade= {CascadeType.PERSIST, CascadeType.MERGE}    
            ) 
    @JoinColumn(name="Xartofulakiono")
    Xartofulakio Xartofulakio;
	
    public Xartofulakio getXartofulakio() {
    	return this.Xartofulakio;
    }
    
    public void setXartofulakio(Xartofulakio x) {
    	this.Xartofulakio = x;
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
		this.state="pending";
	}
	
    public boolean isPending() {
        return state.equalsIgnoreCase("pending");
    }
		
}
