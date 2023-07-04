package com.multi.racket.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.MemberDTO;
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
	public MemberDTO member_insert(MemberDTO member) {
		return dao.member_insert(member);
	}

	@Override
	public MemberDTO findMemberByMemberId(String memberId) {
		return dao.findMemberByMemberId(memberId);
	}

	@Override
	public MemberDTO findByMemberEmail(String memberEmail) {
		return dao.findByMemberEmail(memberEmail);
	}

	@Override
	public MemberDTO findByMemberNick(String memberNick) {
		return dao.findByMemberNick(memberNick);
	}

}
