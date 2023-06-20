package com.multi.racket.manage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;

public interface ManageRepository extends JpaRepository<StadiumDTO, String>{
	 List<StadiumDTO> findByStadiumStatus(String stadiumStatus);
}
