package com.multi.racket.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.memberDTO;
@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {
	SignUpDAO dao;
	@Autowired
	public SignUpServiceImpl(SignUpDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public memberDTO member_insert(memberDTO member) {
		return dao.member_insert(member);
	}

}
