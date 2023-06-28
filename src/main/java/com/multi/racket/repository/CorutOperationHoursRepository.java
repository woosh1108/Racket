package com.multi.racket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.CourtoperatinghoursDTO;

public interface CorutOperationHoursRepository extends JpaRepository<CourtoperatinghoursDTO, Integer>{
//	List<CourtoperatinghoursDTO> findByCourtNo(int courtNo);
}
