package gr.aueb.mscis.sample.model;

import java.util.*;
import javax.persistence.*;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Transaction;

@Entity
@Table(name="Xartofulakio",uniqueConstraints = {
        @UniqueConstraint(columnNames = "XId")})
public class Xartofulakio {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "XId", unique = true, nullable = false)
	private int XId;
	
	@Column(name = "trexousathesi", length = 512, nullable = false)
	private String trexousathesi;
	
    @Embedded
    private Customer cus = new Customer();
    
	/*1 portofolio is associated with many transactions.*/
    @OneToMany(cascade = CascadeType.ALL, 
            mappedBy="Xartofulakio", fetch=FetchType.LAZY)    
    private Set<Transaction> trans = new HashSet<Transaction>();

	/**
	 *  Επιστέφει τη συλλογή των συναλλαγών
	 *  Η συλλογή είναι αντίγραφο της συλλογής των συναλλαγών
	 * @return H συλλογή των συναλλαγών
	 */
    public Set<Transaction> getTransactions() {
        return this.trans;
    }
    
    public void addTransaction(Transaction t) {
    	this.friendTransactions().add(t);
    	
    }
    
    public void removeTransaction(Transaction t) {
    	this.friendTransactions().remove(t);
    }
    
    Set<Transaction> friendTransactions() {
        return trans;
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
            cascade= {CascadeType.ALL}    
            ) 
    @JoinColumn(name="DXno")
    DX dx;
	
    public DX getDX() {
    	return this.dx;
    }
    
    public void setDX(DX dx) {
    	this.dx=dx;
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
		this.trexousathesi = trexousathesi;
		this.cus = c;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Xartofulakio other = (Xartofulakio) obj;
		if (cus == null) {
			if (other.cus != null)
				return false;
		} else if (!cus.equals(other.cus))
			return false;
		if (trexousathesi == null) {
			if (other.trexousathesi != null)
				return false;
		} else if (!trexousathesi.equals(other.trexousathesi))
			return false;
		return true;
	}
	
	
	
}
