package gr.aueb.mscis.sample.model;
//leipoun toString kai equals 

import java.util.*;
import javax.persistence.*;
import gr.aueb.mscis.sample.model.Customer;

@Entity
@Table(name="Xartofulakio",uniqueConstraints = {
        @UniqueConstraint(columnNames = "XId")})
public class Xartofulakio {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "XId", unique = true, nullable = false)
	private int XId;
	
	@Column(name = "trexousathesi", length = 512, nullable = false)
	private String trexousathesi;
	
    @Embedded
    private Customer cus = new Customer();
    
	/*1 portofolio is associated with many transactions.*/
    @OneToMany(orphanRemoval=true, 
            cascade = CascadeType.MERGE, 
            mappedBy="Xartofulakio", fetch=FetchType.LAZY)    
    private Set<Transaction> trans = new HashSet<Transaction>();

	/**
	 *  Επιστέφει τη συλλογή των συναλλαγών
	 *  Η συλλογή είναι αντίγραφο της συλλογής των συναλλαγών
	 * @return H συλλογή των συναλλαγών
	 */
    public Set<Transaction> getTransactions() {
        return new HashSet<Transaction>(trans);
    }
    
    public void setTransactions(Set<Transaction> trans) {
    	this.trans = trans;
    }
    
    public void addTransaction(Transaction trans) {
        if (trans != null) {
            trans.addXartofulakio(this);
        }
    }
    
    public void removeTransaction(Transaction trans) {
    	if (trans != null) {
    		trans.removeXartofulakio(this);
    	}
    }
    
	/**
	 * Επιστρέφει τον αριθμό των συναλλαγών που δεν έχουν εκτελεστεί ακόμα
	 * χρησιμοποιείται για έλεγχο
	 * @return
	 */

	public int countPendingStocks() {
        int pendingTrans = 0;
        for (Transaction tran : trans) {
            if (tran.isPending()) {
                pendingTrans++;
            }
        }
        return pendingTrans;
    }
	
	/* kathe xartofulakio exei 1 diaxeiristi */ 
	@ManyToOne(fetch=FetchType.LAZY, 
            cascade= {CascadeType.MERGE}    
            ) 
    @JoinColumn(name="DXno")
    DX dx;
	
    public DX getDX() {
    	return this.dx;
    }
    
    public void setDX(DX dx) {
    	this.dx=dx;
    }
    
    public void addDX(DX dx) {
        if (dx != null) {
            dx.addXartofulakio(this);
        }
    }
    
    public void removeDX(DX dx) {
    	if (dx != null) {
    		dx.removeXartofulakio(this);
    	}
    }
    
    /**
     * Επιστρεφει τον αριθμό του χαρτοφυλακίου
     * που προσδιοριζει μοναδικά κάθε χαρτοφυλάκιο.
     * @return Τον αριθμό του χαρτοφυλακίου
     */
	public int getXid() {
		return XId;
	}
	 /**
	  * Θέτει τον αριθμό του χαρτοφυλακίου
      * που προσδιοριζει μοναδικά κάθε χαρτοφυλάκιο.
	  * @param xid
	  */
	public void setXid(int xid) {
		XId = xid;
	}
	
	public String getTrexousathesi() {
		return trexousathesi;
	}
	public void setTrexousathesi(String trexousathesi) {
		this.trexousathesi = trexousathesi;
	}
		
	public Xartofulakio() {
		super();
	}
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	
	/**
	 *Βοηθητικός Κώδικας που αρχικοποιεί τα στοιχεία του κάθε χαρτοφυλακίου,
	 *τόσο τεχνικές πληροφορίες όσο και προς τον ιδιοκτήτη του
	 * @param xid
	 * @param trexousathesi
	 * @param CustomerId
	 * @param aDT
	 * @param aFM
	 * @param name
	 * @param surname
	 * @param tel
	 * @param email
	 * @param birthDate
	 * @param investAmount
	 * @param bankAccountNo
	 */
	public Xartofulakio(String trexousathesi, Customer c) {
		super();
		this.trexousathesi = trexousathesi;
		this.cus = c;
	}
	
}
