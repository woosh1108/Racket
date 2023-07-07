package com.multi.racket.signup;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MemberDTO;

public interface SignUpDAO {
	public MemberDTO member_insert(MemberDTO member);
	public MemberDTO findMemberByMemberId(String memberId);
	public MemberDTO findByMemberNick(String memberNick);
	public MemberDTO findByMemberEmail(String memberEmail);
	public CashDTO member_signup(MemberDTO member,CashDTO cash);
}
