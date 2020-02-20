package gr.aueb.mscis.sample.model;
//leipoun toString kai equals 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
