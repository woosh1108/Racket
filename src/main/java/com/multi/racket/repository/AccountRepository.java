package com.multi.racket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.AccountDTO;

public interface AccountRepository extends JpaRepository<AccountDTO, Integer> {
	//List<AccountDTO> findByStadiumNo(int stadiumNo);
}
