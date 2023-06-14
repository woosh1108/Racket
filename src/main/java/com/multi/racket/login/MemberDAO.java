package com.multi.racket.login;

import com.multi.racket.domain.memberDTO;

public interface MemberDAO {
	memberDTO login(String memberId, String memberPass);
	
}
