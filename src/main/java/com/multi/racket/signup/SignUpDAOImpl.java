package com.multi.racket.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.repository.SignupRepository;
@Repository
public class SignUpDAOImpl implements SignUpDAO {
	private SignupRepository repository;
	@Autowired
	public SignUpDAOImpl(SignupRepository repository) {
		super();
		this.repository = repository;
	}
	public MemberDTO member_insert(MemberDTO member) {
		repository.save(member);
		return member;
	}
	@Override
	public MemberDTO findMemberByMemberId(String memberId) {
		return repository.findMemberByMemberId(memberId);
	}
	@Override
	public MemberDTO findByMemberEmail(String memberEmail) {
		return repository.findByMemberEmail(memberEmail);
	}
	@Override
	public MemberDTO findByMemberNick(String memberNick) {
		return repository.findByMemberNick(memberNick);
	}
}
