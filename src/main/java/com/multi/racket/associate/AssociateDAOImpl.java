package com.multi.racket.associate;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
@Repository
public class AssociateDAOImpl implements AssociateDAO {
	private EntityManager entityManager;
	@Autowired
	public AssociateDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<StadiumDTO> findAll() {
		String jpql = "select stadium from StadiumDTO as stadium";
		List<StadiumDTO> list = entityManager.createQuery(jpql,StadiumDTO.class)
			         					   .getResultList();
		return list;
	}

}
