package com.multi.racket.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.memberDTO;
import com.multi.racket.repository.MemberRepository;
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
    MemberRepository memberRepository;

	@Override
	public memberDTO login(String memberId, String memberPass) {
		// TODO Auto-generated method stub
		return memberRepository.findByMemberIdAndMemberPass(memberId, memberPass);
	}



}
