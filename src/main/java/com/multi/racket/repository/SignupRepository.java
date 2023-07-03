package com.multi.racket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;

public interface SignupRepository extends JpaRepository<MemberDTO, String>{
	MemberDTO findMemberByMemberId(String memberId);
}
