package gr.aueb.mscis.sample.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "deiktes")
public class Deiktes {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

/*	// 1 DEIKTES YPOLOGIZONTAI ME VASI * METOXI

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "deiktes", fetch = FetchType.LAZY)
	private Set<Metoxh> metoxi = new HashSet<Metoxh>();
*/
	//GIA KA8E MIA EGGRAFH STO PINAKA METOXH, KANOUME MIA EGGRAFH STO PINAKA DEIKTES
	
	@OneToOne(orphanRemoval=true,cascade = CascadeType.ALL,
	  		   fetch=FetchType.LAZY)
	@JoinColumn(name="id", referencedColumnName="StockId")
	private Metoxh metoxh;
	 
	public Metoxh getMetoxh() {
		return this.metoxh;
	}
	public void setMetoxh(Metoxh metoxh) {
		this.metoxh=metoxh;
	}
	
	
	@Column(name = "MKO15", length = 50, nullable = false)
	private Double MKO15;

	@Column(name = "ΜΚΟ80", length = 50, nullable = false)
	private Double ΜΚΟ80;

	@Column(name = "yk20", length = 50, nullable = false)
	private Double yk20;

	@Column(name = "xk20", length = 50, nullable = false)
	private Double xk20;

/*	@OneToOne(mappedBy = "Deiktes")
	private Metoxh metoxh;
*/
	public Deiktes() {
	}

	public Deiktes(int Id, Double MKO15, Double ΜΚΟ80, Double yk20, Double xk20) {
		this.MKO15 = MKO15;
		this.ΜΚΟ80 = ΜΚΟ80;
		this.yk20 = yk20;
		this.xk20 = xk20;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Double getMKO15() {
		return MKO15;
	}

	public void setMKO15(Double MKO15) {
		MKO15 = MKO15;
	}

	public Double getΜΚΟ80() {
		return ΜΚΟ80;
	}

	public void setΜΚΟ80(Double μΚΟ80) {
		ΜΚΟ80 = μΚΟ80;
	}

	public Double getYk20() {
		return yk20;
	}

	public void setYk20(Double yk20) {
		this.yk20 = yk20;
	}

	public Double getXk20() {
		return xk20;
	}

	public void setXk20(Double xk20) {
		this.xk20 = xk20;
	}

}
