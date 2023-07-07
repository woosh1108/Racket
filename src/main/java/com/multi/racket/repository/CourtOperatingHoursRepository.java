package com.multi.racket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.CourtoperatinghoursDTO;

public interface CourtOperatingHoursRepository extends JpaRepository<CourtoperatinghoursDTO, Integer>{
	List<CourtoperatinghoursDTO> findAllByCourtNo(int courtNo);
    Optional<CourtoperatinghoursDTO> findByCourtHourNo(int courtHourNo);
}
