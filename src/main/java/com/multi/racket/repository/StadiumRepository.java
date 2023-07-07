package com.multi.racket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;

public interface StadiumRepository extends JpaRepository<StadiumDTO, Integer> {
	Optional<StadiumDTO> findByStadiumNo(int stadiumNo);

	// 구장 목록 조회
	Page<StadiumDTO> findAllByStadiumStatus(int stadiumStatus, Pageable pageable);
	
	// 구장 검색
	Page<StadiumDTO> findByStadiumNameContaining(String keyword, Pageable pageable);
	Page<StadiumDTO> findByStadiumAddrContaining(String keyword, Pageable pageable);
	List<StadiumDTO> findByStadiumStatus(int stadiumStatus);

}
