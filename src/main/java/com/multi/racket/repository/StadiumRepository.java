package com.multi.racket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;

public interface StadiumRepository extends JpaRepository<StadiumDTO, Integer> {
	Optional<StadiumDTO> findByStadiumNo(int stadiumNo);
	
	Page<StadiumDTO> findByStadiumNameContaining(String keyword, Pageable pageable);
	Page<StadiumDTO> findByStadiumAddrContaining(String keyword, Pageable pageable);
	Page<StadiumDTO> findAllByStadiumStatus(int stadiumStatus, Pageable pageable);
	List<StadiumDTO> findByStadiumStatus(int stadiumStatus);
}
