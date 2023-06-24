package com.multi.racket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumFileDTO;

public interface StadiumFileRepository extends JpaRepository<StadiumFileDTO, Integer> {
	List<StadiumFileDTO> findByStadiumNoAndStadiumFileNum(int stadiumNo, String stadiumFileNum);
}
