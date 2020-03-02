package gr.aueb.mscis.sample.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Metoxh",uniqueConstraints = {
        @UniqueConstraint(columnNames = "StockId")})
public class Metoxh {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StockId", unique = true, nullable = false)
	private int StockId;
	
	@Column(name = "Name", length = 512, nullable = false)
	private String Name;

	@Column(name = "date", nullable = false)
	private String date;

	@Column(name = "High", length = 10, nullable = false)
	private Double High;
	
	@Column(name = "Low", length = 10, nullable = false)
	private Double Low;
	
	@Column(name = "Closing", length = 10, nullable = false)
	private Double Closing;

	@Column(name = "Beta", length = 10, nullable = false)
	private Double Beta;
	
	@Column(name = "Volume", length = 10, nullable = false)
	private int Volume;
	
    @OneToOne(orphanRemoval=true,cascade = CascadeType.ALL,
     		  mappedBy="metoxh", fetch=FetchType.LAZY)
      private Deiktes deikths;

	public Deiktes getDeikth() {
		return this.deikths;
	}
	
	public void setDeikth(Deiktes deikths) {
		this.deikths=deikths;
	}
    
    //* DX EPILEGOUN * METOXES
    
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
            fetch=FetchType.LAZY)
    @JoinTable(name="DXsandMetoxes", 
            joinColumns = {@JoinColumn(name="StockId")},
            inverseJoinColumns = {@JoinColumn(name="authorid")}
    )
    private Set<DX> dxs = new HashSet<DX>();
    
    public Set<DX> getDXs() {
        return new HashSet<DX>(dxs);
    }
    
    public void addDX(DX dx) {
        if (dx != null) {
            dx.addMetoxh(this);
        }
    }
   
    public void removeDX(DX dx) {
        if (dx != null) {
            dx.removeMetoxh(this);
        }
    }

    Set<DX> friendDXs() {
        return dxs;
    }
    
	public int getStockId() {
		return StockId;
	}

	public void setStockId(int id) {
		this.StockId = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getHigh() {
		return High;
	}

	public void setHigh(Double high) {
		High = high;
	}

	public Double getLow() {
		return Low;
	}

	public void setLow(Double low) {
		Low = low;
	}

	public Double getClosing() {
		return Closing;
	}

	public void setClosing(Double closing) {
		Closing = closing;
	}

	public Double getBeta() {
		return Beta;
	}

	public void setBeta(Double beta) {
		Beta = beta;
	}

	public int getVolume() {
		return Volume;
	}

	public void setVolume(int volume) {
		Volume = volume;
	}
    
	public Metoxh() {
	}

	public Metoxh(String name, String date, Double high, Double low, Double closing,Double beta,int Volume) {
		this.Name = name;
		this.date = date;
		this.High = high;
		this.Low = low;
		this.Closing = closing;
		this.Beta=beta;
		this.Volume=Volume;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metoxh other = (Metoxh) obj;
		if (Beta == null) {
			if (other.Beta != null)
				return false;
		} else if (!Beta.equals(other.Beta))
			return false;
		if (Closing == null) {
			if (other.Closing != null)
				return false;
		} else if (!Closing.equals(other.Closing))
			return false;
		if (High == null) {
			if (other.High != null)
				return false;
		} else if (!High.equals(other.High))
			return false;
		if (Low == null) {
			if (other.Low != null)
				return false;
		} else if (!Low.equals(other.Low))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Volume != other.Volume)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deikths == null) {
			if (other.deikths != null)
				return false;
		} else if (!deikths.equals(other.deikths))
			return false;
		if (dxs == null) {
			if (other.dxs != null)
				return false;
		} else if (!dxs.equals(other.dxs))
			return false;
		return true;
	}
	
}
