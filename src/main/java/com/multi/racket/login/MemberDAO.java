package com.multi.racket.login;

import com.multi.racket.domain.MemberDTO;

public interface MemberDAO {
	MemberDTO login(String memberId, String memberPass);
	
}
