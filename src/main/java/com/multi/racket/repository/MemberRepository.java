package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.memberDTO;

public interface MemberRepository extends JpaRepository<memberDTO, Long> {
	memberDTO findByMemberIdAndMemberPass(String memberId,String memberPass);
}
