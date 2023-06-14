package com.multi.racket.signup;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.MemberDTO;
@Repository
public class SignUpDAOImpl implements SignUpDAO {
	private EntityManager entityManager;
	@Autowired
	public SignUpDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public MemberDTO member_insert(MemberDTO member) {
		entityManager.persist(member);
		return member;
	}

}
