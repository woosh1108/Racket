package com.multi.racket.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.memberDTO;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;

	public MemberServiceImpl() {
		
	}

	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public memberDTO login(String memberId, String memberPass) {
		// TODO Auto-generated method stub
		return dao.login(memberId, memberPass);
	}



}
