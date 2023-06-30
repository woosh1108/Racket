package com.multi.racket.manage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;

public interface ManageRepository extends JpaRepository<StadiumDTO, Integer>{
	 List<StadiumDTO> findByStadiumStatus(int stadiumStatus);
	 List<StadiumDTO> findBystadiumNameContaining(String keyword);
	 List<StadiumDTO> findBystadiumAddrContaining(String keyword);
	 Page<StadiumDTO> findAllByStadiumStatus(int stadiumStatus, Pageable pageable);
	 StadiumDTO findByStadiumNo(int stadiumNo);
	 long countByStadiumStatus(int stadiumStatus);
}
