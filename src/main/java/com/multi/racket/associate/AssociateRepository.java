package com.multi.racket.associate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.StadiumDTO;

public interface AssociateRepository extends JpaRepository<StadiumDTO, String>{

}
