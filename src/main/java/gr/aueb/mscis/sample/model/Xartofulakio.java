package gr.aueb.mscis.sample.model;
//leipoun toString kai equals 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstrain;

@Entity
@Table(name="Xartofulakio",uniqueConstraints = {
        @UniqueConstraint(columnNames = "XId")})
public class Xartofulakio {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "XId"", unique = true, nullable = false)
	private int Xid;
	
	@Column(name = "trexousathesi", length = 512, nullable = false)
	private String trexousathesi;
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Xartofulakiono")
    private Xartofulakio X;
    
	public int getXid() {
		return Xid;
	}


	public void setXid(int xid) {
		Xid = xid;
	}


	public String getTrexousathesi() {
		return trexousathesi;
	}


	public void setTrexousathesi(String trexousathesi) {
		this.trexousathesi = trexousathesi;
	}


}
