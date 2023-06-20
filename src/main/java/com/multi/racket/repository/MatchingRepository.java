package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.MatchingDTO;

public interface MatchingRepository extends JpaRepository<MatchingDTO, Integer> {
}
