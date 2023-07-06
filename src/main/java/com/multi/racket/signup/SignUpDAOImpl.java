package com.multi.racket.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.SignupRepository;
@Repository
public class SignUpDAOImpl implements SignUpDAO {
	private SignupRepository repository;
	private CashRepository cashrepository;
	@Autowired
	public SignUpDAOImpl(SignupRepository repository, CashRepository cashrepository) {
		super();
		this.repository = repository;
		this.cashrepository = cashrepository;
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
	@Override
	public CashDTO member_signup(MemberDTO member,CashDTO cash) {
		cash.setMemberId(member.getMemberId());
		cash.setCashDate(member.getMemberReg());
		System.out.println("등록하려는 멤버"+member);
		System.out.println("등록하려는 멤버의 캐쉬값"+cash);
		cashrepository.save(cash);
		return cash;
	}
}
