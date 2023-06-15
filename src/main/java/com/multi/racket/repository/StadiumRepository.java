package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;

@Repository
public interface StadiumRepository extends JpaRepository<StadiumDTO, Integer> {

}
