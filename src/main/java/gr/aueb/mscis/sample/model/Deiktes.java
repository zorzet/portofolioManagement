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

	// 1 DEIKTES YPOLOGIZONTAI ME VASI * METOXI

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "deiktes", fetch = FetchType.LAZY)
	private Set<Metoxh> metoxi = new HashSet<Metoxh>();

	@Column(name = "ΜΚΟ15", length = 50, nullable = false)
	private Double ΜΚΟ15;

	@Column(name = "ΜΚΟ80", length = 50, nullable = false)
	private Double ΜΚΟ80;

	@Column(name = "yk20", length = 50, nullable = false)
	private Double yk20;

	@Column(name = "xk20", length = 50, nullable = false)
	private Double xk20;

	@OneToOne(mappedBy = "Deiktes")
	private Metoxh metoxh;

	public Deiktes() {
	}

	public Deiktes(int Id, Double ΜΚΟ15, Double ΜΚΟ80, Double yk20, Double xk20) {
		this.ΜΚΟ15 = ΜΚΟ15;
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

	public Double getΜΚΟ15() {
		return ΜΚΟ15;
	}

	public void setΜΚΟ15(Double μΚΟ15) {
		ΜΚΟ15 = μΚΟ15;
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
