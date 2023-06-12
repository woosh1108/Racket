package com.multi.racket.associate;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.stadiumDTO;
@Repository
public class AssociateDAOImpl implements AssociateDAO {
	private EntityManager entityManager;
	@Autowired
	public AssociateDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<stadiumDTO> findAll() {
		String jpql = "select stadium from stadiumDTO as stadium";
		List<stadiumDTO> list = entityManager.createQuery(jpql,stadiumDTO.class)
			         					   .getResultList();
		return list;
	}

}
