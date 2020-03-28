package gr.aueb.mscis.sample.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.sample.model.Customer;
import gr.aueb.mscis.sample.model.Xartofulakio;

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

	public CustomerInfo() {
	}

	public CustomerInfo(int CustomerId, String aDT, String aFM, String name, String surname, String tel, String email,
			String birthDate, int investAmount, String bankAccountNo) {
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

	public Customer getCustomer(EntityManager em) {

		Customer c = null;
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (this.CustomerId != 0) {
			List<Xartofulakio> xs = em.createQuery("select x from Xartofulakio x", Xartofulakio.class).getResultList();
			for (Xartofulakio x : xs) {
				if (x.getCus().getCustomerId() == this.CustomerId)
					c = x.getCus();
			}
		} else {

			c = new Customer();
		}

		tx.commit();
		c.setCustomerId(this.CustomerId);
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

	public static CustomerInfo wrap(Customer c) {
		return new CustomerInfo(c);
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
