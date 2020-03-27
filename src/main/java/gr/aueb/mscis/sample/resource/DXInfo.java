package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.DX;

@XmlRootElement
public class DXInfo {
	private int DXId;
	private String ADT;
	private String AFM;
	private String Name;
	private String Surname;
	private String Tel;
	private String email;
	private String BirthDate;
	
	public DXInfo() {}
	
	public DXInfo(int DXId,String aDT, String aFM, String name, String surname, String tel, String email, String birthDate) {	
		this(aDT, aFM, name, surname, tel, email,birthDate);
		this.DXId = DXId;
	}
	
	public DXInfo(String aDT, String aFM, String name, String surname, String tel, String email, String birthDate) {
		super();
		this.ADT = aDT;
		this.AFM = aFM;
		this.Name = name;
		this.Surname = surname;
		this.Tel = tel;
		this.email = email;
		this.BirthDate = birthDate;
	}
	
	public DXInfo(DX dx) {
		super();
		
		this.ADT=dx.getADT();
		this.AFM=dx.getAFM();
		this.DXId = dx.getDXId();
		this.Name = dx.getName();
		this.Surname = dx.getSurname();
		this.Tel = dx.getTel();
		this.email = dx.getEmail();
		this.BirthDate = dx.getBirthDate();
	
	}
	
	
	
	public DX getDX(EntityManager em) {
		
		DX dx;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.DXId != 0) {
			
			dx = em.find(DX.class, this.DXId);
		}
		else {
			
			dx = new DX();
		}
		
		tx.commit();
		
		dx.setName(this.Name);
		dx.setSurname(this.Surname);
		dx.setTel(this.Tel);
		dx.setEmail(this.email);
		dx.setBirthDate(this.BirthDate);
		dx.setADT(this.ADT);
		dx.setAFM(this.AFM);
		
		return dx;
	}
	
	public static DXInfo wrap(DX dx) {
		return new DXInfo(dx);
	}

	public int getDXId() {
		return DXId;
	}

	public void setDXId(int dXId) {
		DXId = dXId;
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
	
	

}
