package com.multi.racket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;

public interface StadiumCorutRepository extends JpaRepository<StadiumcourtDTO, Integer>{

}
