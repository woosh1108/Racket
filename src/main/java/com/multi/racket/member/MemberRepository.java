package com.multi.racket.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, String> {
	MemberDTO findByMemberIdAndMemberPass(String memberId,String memberPass);
	MemberDTO findByMemberNameAndMemberEmail(String memberName, String memeberEmail);
}
