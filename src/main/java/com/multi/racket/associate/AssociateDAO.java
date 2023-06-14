package com.multi.racket.associate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.multi.racket.domain.StadiumDTO;

public interface AssociateDAO {
	public List<StadiumDTO> findAll() ;
}
