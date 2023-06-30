package com.multi.racket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;

public interface StadiumRepository extends JpaRepository<StadiumDTO, Integer> {
	Optional<StadiumDTO> findByStadiumNo(int stadiumNo);
}
