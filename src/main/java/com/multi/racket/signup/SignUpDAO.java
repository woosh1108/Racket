package com.multi.racket.signup;

import com.multi.racket.domain.memberDTO;

public interface SignUpDAO {
	public memberDTO member_insert(memberDTO member);
}
