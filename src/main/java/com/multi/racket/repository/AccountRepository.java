package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.AccountDTO;

public interface AccountRepository extends JpaRepository<AccountDTO, Integer> {

}
