package gr.aueb.mscis.sample.model;

import javax.persistence.*;

@Entity
@Table(name = "deiktes")
public class Deiktes {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

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
	
	@Column(name = "Date", length = 50, nullable = false)
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(name = "MKO15", length = 50, nullable = false)
	private Double MKO15;

	@Column(name = "MKO80", length = 50, nullable = false)
	private Double MKO80;

	@Column(name = "yk20", length = 50, nullable = false)
	private Double yk20;

	@Column(name = "xk20", length = 50, nullable = false)
	private Double xk20;

	public Deiktes() {
	}

	public Deiktes(String date,Double MKO15, Double MKO80, Double yk20, Double xk20) {
		this.date=date;
		this.MKO15 = MKO15;
		this.MKO80 = MKO80;
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
	public void setMKO15(Double mKO15) {
		MKO15 = mKO15;
	}
	public Double getMKO80() {
		return MKO80;
	}
	public void setMKO80(Double mKO80) {
		MKO80 = mKO80;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deiktes other = (Deiktes) obj;
		if (MKO15 == null) {
			if (other.MKO15 != null)
				return false;
		} else if (!MKO15.equals(other.MKO15))
			return false;
		if (MKO80 == null) {
			if (other.MKO80 != null)
				return false;
		} else if (!MKO80.equals(other.MKO80))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (xk20 == null) {
			if (other.xk20 != null)
				return false;
		} else if (!xk20.equals(other.xk20))
			return false;
		if (yk20 == null) {
			if (other.yk20 != null)
				return false;
		} else if (!yk20.equals(other.yk20))
			return false;
		return true;
	}
	
}
