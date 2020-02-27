package gr.aueb.mscis.sample.model;

import java.util.*;
import javax.persistence.*;
import gr.aueb.mscis.sample.model.Metoxh;

@Entity
//@DiscriminatorValue("B")
@Table(name = "DX",
	   uniqueConstraints = {@UniqueConstraint(columnNames = "DXId")})
public class DX extends User { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DXId", unique = true, nullable = false)
	private int DXId;
	
	@Column(name = "ADT", length = 9, nullable = false)
	private String ADT;

	@Column(name="AFM", nullable = false)
	private String AFM;
	
	@Column(name="Name", nullable = false)
	private String Name;
	
	@Column(name="Surname", nullable = false)
	private String Surname;
	
	@Column(name="Tel", nullable = false)
	private String Tel;
	
	@Column(name="Email", nullable = false)
	private String email;
	
	@Column(name="BirthDate", nullable = false)
	private String BirthDate;
	
	/*1 DX EXEI POLLA XARTOFYLAKIA*/
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
            mappedBy="dx", fetch=FetchType.LAZY)
    private Set<Xartofulakio> xartofulakia = new HashSet<Xartofulakio>();
	
    public Set<Xartofulakio> getXartofulakio() {
    	return this.xartofulakia;
    }
    
    public void setXartofulakio(Set<Xartofulakio> x) {
    	this.xartofulakia = x;
    }
    
    public void addXartofulakio(Xartofulakio x) {
        if (x != null) {
            x.addDX(this);
        }
    }
    
    public void removeXartofulakio(Xartofulakio x) {
    	if (x != null) {
    		x.removeDX(this);
    	}
    }
    
	// * DX EPILEGEI POLLES METOXES 
	@ManyToMany(mappedBy="dxs",fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Metoxh> metoxes = new HashSet<Metoxh>();
	
	public DX() {
	}
	
	public int getDXId() {
		return DXId;
	}

	public void setDXId(int id) {
		this.DXId = id;
	}

	public String getADT() {
		return ADT;
	}

	public void setADT(String aDT) {
		ADT = aDT;
	}

	public String getAFM() {
		return AFM;
	}

	public void setAFM(String aFM) {
		AFM = aFM;
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}

	public DX(String aDT, String aFM, String name, String surname, String tel, String email, String birthDate) {	
		this.ADT = aDT;
		this.AFM = aFM;
		this.Name = name;
		this.Surname = surname;
		this.Tel = tel;
		this.email = email;
		this.BirthDate = birthDate;
	}
	
	//ADD METOXH ME VASH TOUS DXs
	public void addMetoxh(Metoxh metoxh) {
		if (metoxh != null) {
			metoxh.friendDXs().add(this);
	        this.metoxes.add(metoxh);
	    }
	}

	public void removeMetoxh(Metoxh metoxh) {
	    if (metoxh != null) {
	        metoxh.friendDXs().remove(this);
	        this.metoxes.remove(metoxh);
	    }
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DX other = (DX) obj;
		if (ADT == null) {
			if (other.ADT != null)
				return false;
		} else if (!ADT.equals(other.ADT))
			return false;
		if (AFM == null) {
			if (other.AFM != null)
				return false;
		} else if (!AFM.equals(other.AFM))
			return false;
		if (BirthDate == null) {
			if (other.BirthDate != null)
				return false;
		} else if (!BirthDate.equals(other.BirthDate))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Surname == null) {
			if (other.Surname != null)
				return false;
		} else if (!Surname.equals(other.Surname))
			return false;
		if (Tel == null) {
			if (other.Tel != null)
				return false;
		} else if (!Tel.equals(other.Tel))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (DXId != other.DXId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + DXId + ", ADT=" + ADT + ", AFM=" + AFM + ", Name=" + Name + ", Surname=" + Surname
				+ ", Tel=" + Tel + ", email=" + email + ", BirthDate=" + BirthDate + "]";
	}

}
