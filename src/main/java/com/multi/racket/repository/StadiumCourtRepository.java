package com.multi.racket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.StadiumcourtDTO;

public interface StadiumCourtRepository extends JpaRepository<StadiumcourtDTO, Integer> {
//    Optional<StadiumcourtDTO> findByCourtNo(int courtNo);
//
//    @Query("SELECT COUNT(sc) FROM StadiumcourtDTO sc WHERE sc.stadium.stadiumNo = :stadiumNo")
//    int countByStadium(@Param("stadiumNo") int stadiumNo);
}