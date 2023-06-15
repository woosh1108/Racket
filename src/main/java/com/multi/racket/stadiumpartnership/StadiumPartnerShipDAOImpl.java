package com.multi.racket.stadiumpartnership;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.AssociateDTO;
@Repository
public class StadiumPartnerShipDAOImpl implements StadiumPartnerShipDAO {
	private EntityManager entitymanager; 
	@Autowired
	public StadiumPartnerShipDAOImpl(EntityManager entitymanager) {
		super();
		this.entitymanager = entitymanager;
	}


	@Override
	public AssociateDTO partnership_insert(AssociateDTO associate) {
		entitymanager.persist(associate);
		return associate;
	}

}
