package com.multi.racket.login;

import com.multi.racket.domain.MemberDTO;

public interface MemberService {
	MemberDTO login(String memberId, String memberPass);
}
