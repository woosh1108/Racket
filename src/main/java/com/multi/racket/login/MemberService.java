package com.multi.racket.login;

import com.multi.racket.domain.memberDTO;

public interface MemberService {
	memberDTO login(String memberId, String memberPass);
}
