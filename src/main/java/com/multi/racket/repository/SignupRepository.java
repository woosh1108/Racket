package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.MemberDTO;

public interface SignupRepository extends JpaRepository<MemberDTO, String>{
	MemberDTO findMemberByMemberId(String memberId);
}
