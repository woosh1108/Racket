package com.multi.racket.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.MemberDTO;

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
	public MemberDTO login(String memberId, String memberPass) {
		// TODO Auto-generated method stub
		return dao.login(memberId, memberPass);
	}

	@Override
	public void update(MemberDTO updatedata) {
		dao.update(updatedata);
	}

	@Override
	public MemberDTO idCheck(String memberId) {
		// TODO Auto-generated method stub
		return dao.idCheck(memberId);
	}

	@Override
	public MemberDTO findId(String memberName, String memberEmail) {
		// TODO Auto-generated method stub
		return dao.findId(memberName, memberEmail);
	}

	@Override
	public void updatePass(MemberDTO updateUser, String memberId, String memberPass) {
		// TODO Auto-generated method stub
		dao.updatePass(updateUser, memberId, memberPass);
	}

}
