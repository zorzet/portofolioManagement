package gr.aueb.mscis.sample.service;

import java.util.List;

import javax.persistence.EntityManager;

import gr.aueb.mscis.sample.model.Xartofulakio;


public class XartofulakioService {
	private EntityManager em;

	public XartofulakioService(EntityManager em) {
		this.em = em;
	}
	
/*vres ta xartofulakia pou diaxeirizetai o ka8e DX*/	
	
	@SuppressWarnings("unchecked")
	public List<Xartofulakio> findXartofulakioByLastName(int DXId) {

		List< Xartofulakio> results = null;
/*		results = em
				.createQuery(
						"select b from Borrower b where b.person.lastName like :surname ")
				.setParameter("surname", last_name).getResultList();
*/				
		return results;
	}


}
