package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumFileDTO;

public interface StadiumFileRepository extends JpaRepository<StadiumFileDTO, Integer> {
//	List<StadiumFileDTO> findByStadiumNo(int stadiumNo);

}
