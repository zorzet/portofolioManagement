package gr.aueb.mscis.sample.service;


import java.util.*;
import javax.persistence.*;

import gr.aueb.mscis.sample.model.DX;
import gr.aueb.mscis.sample.model.MarketsData;
import gr.aueb.mscis.sample.model.Metoxh;

public class LoginService {
	private EntityManager em;

	public LoginService(EntityManager em) {
		this.em = em;
	}  
	
//////////////////////////////////////////////////	
///	O χρήστης εισάγει το username και το password.
///	Το σύστημα επιβεβαιώνει την ορθότητα των credentials.
/////////////////////////////////////////////////


	public int LoginUser(String username, String password) {
		DX dx=null;
	    Query query = em.createQuery("select dx from DX dx ", DX.class).setMaxResults(1);
	    dx=(DX)query.getSingleResult();		
	    if(dx.getPassword().equalsIgnoreCase(password)) {
	    	return 0;
	    }else {
	    	return -1;
	    }
	
	}

}
