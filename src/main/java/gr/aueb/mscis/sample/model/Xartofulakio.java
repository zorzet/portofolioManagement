package gr.aueb.mscis.sample.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Xartofulakio")
public class Xartofulakio {
  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrexousathesi() {
		return trexousathesi;
	}

	public void setTrexousathesi(String trexousathesi) {
		this.trexousathesi = trexousathesi;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "trexousathesi", length = 512, nullable = false)
	private String trexousathesi;


}
