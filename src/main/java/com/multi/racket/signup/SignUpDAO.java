package com.multi.racket.signup;

import com.multi.racket.domain.MemberDTO;

public interface SignUpDAO {
	public MemberDTO member_insert(MemberDTO member);
}
