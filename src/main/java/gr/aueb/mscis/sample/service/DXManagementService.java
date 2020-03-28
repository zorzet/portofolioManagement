package gr.aueb.mscis.sample.service;

import javax.persistence.EntityManager;
import java.util.*;
import gr.aueb.mscis.sample.model.DX;

public class DXManagementService {

	EntityManager em;

	public DXManagementService(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<DX> findDXsByLastName(String lastname) {
		List<DX> results = new ArrayList<DX>();
		results = em.createQuery("select b from DX b where b.Surname like :surname ").setParameter("surname", lastname)
				.getResultList();
		if (results.isEmpty()) {

			throw new java.lang.RuntimeException("NO DX FOUND");
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<DX> findDXsByfirstname(String firstname) {

		List<DX> results = new ArrayList<DX>();
		results = em.createQuery("select c from DX c where c.Name like :name ").setParameter("name", firstname)
				.getResultList();

		if (results.isEmpty()) {

			throw new java.lang.RuntimeException("NO DX FOUND");
		}
		return results;
	}

	public DX findDXById(int id) {
		DX result = null;
		result = em.find(DX.class, id);
		if (result == null) {
			throw new java.lang.RuntimeException("NO DX FOUND");
		}
		return result;
	}

	public List<DX> findAllDXs() {
		List<DX> dxs = em.createQuery("select c from DX c", DX.class).getResultList();
		return dxs;
	}

	public boolean saveOrUpdateDX(DX b) {

		if (b != null) {
			em.merge(b);
			return true;
		}

		return false;
	}

	public boolean createDX(DX b) {

		if (b != null) {
			em.persist(b);
			return true;
		}

		return false;
	}

	public boolean RemoveDX(DX b) {

		if (b != null) {
			em.remove(b);
			return true;
		}
		return false;
	}
}
