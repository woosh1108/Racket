package com.multi.racket.member;

import com.multi.racket.domain.MemberDTO;

public interface MemberService {
	// 로그인
	MemberDTO login(String memberId, String memberPass);

	// 개인정보 수정하기
	public void update(MemberDTO updatedata);

	// 비밀번호 찾기 -> 아이디 확인
	public boolean idCheck(String memberId);
	
	// 아이디 앚기
	MemberDTO findId(String memberName, String memberEmail);
}
