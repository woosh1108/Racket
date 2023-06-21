package com.multi.racket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.AbsentDTO;

public interface AbsentRepository extends JpaRepository<AbsentDTO, Integer> {
	List<AbsentDTO> findByMemberId(String memberId);
    List<AbsentDTO> findByMemberIdGreaterThanEqual(String memberId);
}
