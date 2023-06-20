package com.multi.racket.login;

import com.multi.racket.domain.MemberDTO;

public interface MemberDAO {
	// 로그인
	MemberDTO login(String memberId, String memberPass);

	// 개인정보 수정
	public void update(MemberDTO updatedata);

	// 비밀번호찾기 -> 아이디 확인
	boolean idCheck(String memberId);

	// 아이디 찾기
	MemberDTO findId(String memberName, String memberEmail);
}
