package gr.aueb.mscis.sample.model;
//leipoun toString kai equals 

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
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
            cascade = CascadeType.ALL, 
            mappedBy="Xartofulakio", fetch=FetchType.LAZY)    
    private Set<Transaction> trans = new HashSet<Transaction>();

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
	public Xartofulakio(int xid, String trexousathesi,int CustomerId,String aDT, String aFM, String name, String surname, String tel, String email, String birthDate,
			int investAmount, String bankAccountNo) {
		super();
		this.XId = xid;
		this.trexousathesi = trexousathesi;
		cus.setADT(aDT);
		cus.setAFM(aFM);
		cus.setBankAccountNo(bankAccountNo);
		cus.setBirthDate(birthDate);
		cus.setCustomerId(CustomerId);
		cus.setEmail(email);
		cus.setInvestAmount(investAmount);
		cus.setName(name);
		cus.setSurname(surname);
		cus.setTel(tel);
	}
	
	/**
	 *  Επιστέφει τη συλλογή των συναλλαγών
	 *  Η συλλογή είναι αντίγραφο της συλλογής των συναλλαγών
	 * @return H συλλογή των συναλλαγών
	 */
    public Set<Transaction> getTransactions() {
        return new HashSet<Transaction>(trans);
    }
    
	/**
	 * Επιστρέφει τον αριθμό των συναλλαγών που δεν έχουν εκτελεστεί ακόμα
	 * χρησιμοποιείται για έλεγχο
	 * @return
	 */

	private int countPendingStocks() {
        int pendingItems = 0;
        for (Transaction tran : trans) {
            if (tran.isPending()) {
                pendingItems++;
            }
        }
        return pendingItems;
    }
}
