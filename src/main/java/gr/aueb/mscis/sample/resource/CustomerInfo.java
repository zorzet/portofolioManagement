package gr.aueb.mscis.sample.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Customer;

@XmlRootElement
public class CustomerInfo {
	private int CustomerId;
	private String ADT;
	private String AFM;
	private String Name;
	private String Surname;
	private String Tel;
	private String email;
	private String BirthDate;
	private double InvestAmount;
	private String BankAccountNo;
	
	
	public CustomerInfo() {}
		
	
	
	public CustomerInfo(int CustomerId,String aDT, String aFM, String name, String surname, String tel, String email, String birthDate,
			int investAmount, String bankAccountNo) {	
		this(aDT, aFM, name,surname,tel,email,birthDate,investAmount,bankAccountNo);
		this.CustomerId = CustomerId;
	}
	
	public CustomerInfo(String aDT, String aFM, String name, String surname, String tel, String email, String birthDate,
			int investAmount, String bankAccountNo) {
		super();
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
	
	public CustomerInfo(Customer c) {
		super();
		this.CustomerId = c.getCustomerId();
		this.Name = c.getName();
		this.ADT = c.getADT();
		this.AFM = c.getAFM();
		this.Surname = c.getSurname();
		this.Tel = c.getTel();
		this.BirthDate = c.getBirthDate();
		this.InvestAmount = c.getInvestAmount();
		this.email = c.getEmail();
		this.BankAccountNo = c.getBankAccountNo();
	}
	

	
	public Customer getMetoxh(EntityManager em) {
		
		Customer c;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if(this.CustomerId != 0) {
			
			c = em.find(Customer.class, this.CustomerId);
		}
		else {
			
			c = new Customer();
		}
		
		tx.commit();
		c.setName(this.Name);
		c.setADT(this.ADT);
		c.setAFM(this.AFM);
		c.setSurname(this.Surname);
		c.setInvestAmount(this.InvestAmount);
		c.setTel(this.Tel);
		c.setBirthDate(this.BirthDate);
		c.setEmail(this.email);
		c.setBankAccountNo(this.BankAccountNo);
		
		return c;
	}
	
	public static CustomerInfo wrap(List<CustomerInfo> c) {
		return new CustomerInfo();
	}



	public int getCustomerId() {
		return CustomerId;
	}



	public void setCustomerId(int customerId) {
		CustomerId = customerId;
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

}
