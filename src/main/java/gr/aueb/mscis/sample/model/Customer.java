package gr.aueb.mscis.sample.model;

import javax.persistence.*;

@Embeddable
@DiscriminatorValue("A")
@Table(name = "Customer",uniqueConstraints = {
        @UniqueConstraint(columnNames = "CustomerId")})
public class Customer extends User{ 
	  
	
	@Column(name = "CustomerId", unique = true, nullable = false)
	private int CustomerId;
	
	@Column(name = "ADT", length = 9, unique = true, nullable = false)
	private String ADT;

	@Column(name="AFM", unique = true, nullable = false)
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
	
	@Column(name="InvestAmount", nullable = false)
	private double InvestAmount;
	
	@Column(name="BankAccountNo", unique = true, nullable = false)
	private String BankAccountNo;
	
	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int id) {
		this.CustomerId = id;
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

	public double getInvestAmount() {
		return InvestAmount;
	}

	public void setInvestAmount(double investAmount) {
		InvestAmount = investAmount;
	}

	public String getBankAccountNo() {
		return BankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		BankAccountNo = bankAccountNo;
	}

	@Override
	public String toString() {
		return "Customer [CustomerId=" + CustomerId + ", ADT=" + ADT + ", AFM=" + AFM + ", Name=" + Name + ", Surname=" + Surname
				+ ", Tel=" + Tel + ", email=" + email + ", BirthDate=" + BirthDate + ", InvestAmount=" + InvestAmount
				+ ", BankAccountNo=" + BankAccountNo + "]";
	}

	public Customer(int CustomerId, String aDT, String aFM, String name, String surname, String tel, String email, String birthDate,
			int investAmount, String bankAccountNo) {
		super();
		
		this.CustomerId = CustomerId;
		this.ADT = aDT;
		this.AFM = aFM;
		this.Name = name;
		this.Surname = surname;
		this.Tel = tel;
		this.email = email;
		this.BirthDate = birthDate;
		this.InvestAmount = investAmount;
		this.BankAccountNo = bankAccountNo;
	}
	
	public Customer() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
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
		if (BankAccountNo == null) {
			if (other.BankAccountNo != null)
				return false;
		} else if (!BankAccountNo.equals(other.BankAccountNo))
			return false;
		if (BirthDate == null) {
			if (other.BirthDate != null)
				return false;
		} else if (!BirthDate.equals(other.BirthDate))
			return false;
		if (InvestAmount != other.InvestAmount)
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
		return true;
	}

	
}