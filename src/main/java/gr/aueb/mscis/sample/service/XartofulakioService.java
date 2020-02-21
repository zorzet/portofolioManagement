package gr.aueb.mscis.sample.service;

/* Περίπτωση Χρήσης 6 */ 



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
		results = em
				.createQuery(
						"select x from Xartofulakio x where x.XId like :DXId ")
						.setParameter("DXId", DXId).getResultList();
		return results;
	}

/*gurise ta stoixeia pou exeis gia sugkekrimeno xartofulakio*/
/*gia ena xartofulakio gurna onomatepwnumo, tis closed trasactions kai tis open*/





/*enhmerwse ta stoixeia enos sugkekrimenou xartofulakiou*/
}
