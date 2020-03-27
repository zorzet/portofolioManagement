package gr.aueb.mscis.sample.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import gr.aueb.mscis.sample.persistence.JPAUtil;
import gr.aueb.mscis.sample.model.Customer;
//import gr.aueb.mscis.sample.model.Xartofulakio;
import gr.aueb.mscis.sample.service.PelatologioManagement;

//import java.util.ArrayList;
import java.util.List;  

@Path("PelatologioManagement")
public class PelatologioManagementResource {
	
	@Context
	UriInfo uriInfo;
	private List<CustomerInfo> ln;
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();
	}

//public Customer findCustomersById(int id)
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("findCustomersById")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerInfo findCustomersById(@PathParam("id")int id) {
		EntityManager em = getEntityManager();
		
		PelatologioManagement p = new PelatologioManagement(em);
	    Customer customer = p.findCustomersById(id);
	    System.out.println(customer.getCustomerId());
	    
	    CustomerInfo c;
		c=CustomerInfo.wrap((List<CustomerInfo>) customer);
		return c;
	} 


//public List<Customer> findCustomerByLastName(String lastName)
	
	@GET
	@Path("findCustomerByLastName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByLastName(@PathParam("Surname")String surname){
		ln = null;
		try {
				List<Customer> list=null;
				
				EntityManager em=getEntityManager();
				PelatologioManagement p=new PelatologioManagement(em);
				list=p.findCustomerByLastName(surname);
				for (int i = 0; i < list.size(); i++) {
					ln.add(CustomerInfo.wrap(ln));
				}
				return ln;
		}catch (Exception c) {
			return (List<CustomerInfo>) ln;
		}
  }
	
//public List<Customer> findCustomerByFirstName(String firstName)
	@GET
	@Path("findCustomerByLastName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomerInfo> findCustomerByFirstName(@PathParam("Name")String name){
		ln = null;
		try {
				List<Customer> list=null;
				
				EntityManager em=getEntityManager();
				PelatologioManagement p=new PelatologioManagement(em);
				list=p.findCustomerByFirstName(name);
				for (int i = 0; i < list.size(); i++) {
					ln.add(CustomerInfo.wrap(ln));
				}
				return ln;
		}catch (Exception c) {
			return (List<CustomerInfo>) ln;
		}
  }
	
//public List<Customer> findCustomerByAFM(String AFM)
		@GET
		@Path("findCustomerByAFM")
		@Produces(MediaType.APPLICATION_JSON)
		public List<CustomerInfo> findCustomerByAFM(@PathParam("AFM")String afm){
			ln = null;
			try {
					List<Customer> list=null;
					
					EntityManager em=getEntityManager();
					PelatologioManagement p=new PelatologioManagement(em);
					list=p.findCustomerByAFM(afm);
					for (int i = 0; i < list.size(); i++) {
						ln.add(CustomerInfo.wrap(ln));
					}
					return ln;
			}catch (Exception c) {
				return (List<CustomerInfo>) ln;
			}
	  }
	
	
//public List<Customer> findCustomerByADT(String ADT)
				@GET
				@Path("findCustomerByADT")
				@Produces(MediaType.APPLICATION_JSON)
				public List<CustomerInfo> findCustomerByADT(@PathParam("ADT")String adt){
					ln = null;
					try {
							List<Customer> list=null;
							
							EntityManager em=getEntityManager();
							PelatologioManagement p=new PelatologioManagement(em);
							list=p.findCustomerByADT(adt);
							for (int i = 0; i < list.size(); i++) {
								ln.add(CustomerInfo.wrap(ln));
							}
							return ln;
					}catch (Exception c) {
						return (List<CustomerInfo>) ln;
					}
			  }
				
//public List<Customer> findCustomerByTel(String Tel)
				@GET
				@Path("findCustomerByLastName")
				@Produces(MediaType.APPLICATION_JSON)
				public List<CustomerInfo> findCustomerByTel(@PathParam("Tel")String tel){
					ln = null;
					try {
							List<Customer> list=null;
							
							EntityManager em=getEntityManager();
							PelatologioManagement p=new PelatologioManagement(em);
							list=p.findCustomerByTel(tel);
							for (int i = 0; i < list.size(); i++) {
								ln.add(CustomerInfo.wrap(ln));
							}
							return ln;
					}catch (Exception c) {
						return (List<CustomerInfo>) ln;
					}
			  }
	
//public List<Customer> findCustomerByEmail(String Email)
				@GET
				@Path("findCustomerByEmail")
				@Produces(MediaType.APPLICATION_JSON)
				public List<CustomerInfo> findCustomerByEmail(@PathParam("Email")String email){
					ln = null;
					try {
							List<Customer> list=null;
							
							EntityManager em=getEntityManager();
							PelatologioManagement p=new PelatologioManagement(em);
							list=p.findCustomerByEmail(email);
							for (int i = 0; i < list.size(); i++) {
								ln.add(CustomerInfo.wrap(ln));
							}
							return ln;
					}catch (Exception c) {
						return (List<CustomerInfo>) ln;
					}
			  }
	
	
//public List<Customer> findCustomerByInvestAmount(double InvestAmount)
				@GET
				@Path("findCustomerByEmail")
				@Produces(MediaType.APPLICATION_JSON)
				public List<CustomerInfo> findCustomerByInvestAmount(@PathParam("InvestAmount")double InvestAmount){
					ln = null;
					try {
							List<Customer> list=null;
							
							EntityManager em=getEntityManager();
							PelatologioManagement p=new PelatologioManagement(em);
							list=p.findCustomerByInvestAmount(InvestAmount);
							for (int i = 0; i < list.size(); i++) {
								ln.add(CustomerInfo.wrap(ln));
							}
							return ln;
					}catch (Exception c) {
						return (List<CustomerInfo>) ln;
					}
			  }
	
//public List<Customer> findCustomerByBankAccountNo(String Bank)
				@GET
				@Path("findCustomerByBankAccountNo")
				@Produces(MediaType.APPLICATION_JSON)
				public List<CustomerInfo> findCustomerByBankAccountNo(@PathParam("InvestAmount")String Bank){
					ln = null;
					try {
							List<Customer> list=null;
							
							EntityManager em=getEntityManager();
							PelatologioManagement p=new PelatologioManagement(em);
							list=p.findCustomerByBankAccountNo(Bank);
							for (int i = 0; i < list.size(); i++) {
								ln.add(CustomerInfo.wrap(ln));
							}
							return ln;
					}catch (Exception c) {
						return (List<CustomerInfo>) ln;
					}
			  }
			

				
//public List<Customer> findAllCustomer()	-- θέλει διόρθωση με βάση το Χαρτοφυλάκιο		

				@GET
				@Path("getAllStocks")
				@Produces(MediaType.APPLICATION_JSON)
				public List<Customer> findAllCustomer(){
					List<Customer> c=null;
					try {
							List<Customer> list=null;
							//List<Xartofulakio> xs=null;
							
							EntityManager em=getEntityManager();
							PelatologioManagement x=new PelatologioManagement(em);
							list=x.findAllCustomer();
							
					
							return list;
					}catch (Exception e) {
						return c;

}
				}
}
					
					