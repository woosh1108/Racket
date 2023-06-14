package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
	MemberDTO findByMemberIdAndMemberPass(String memberId,String memberPass);
}
