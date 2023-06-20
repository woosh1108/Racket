package com.multi.racket.manage;

import java.util.List;

import com.multi.racket.domain.StadiumDTO;

public interface ManageService {
	public List<StadiumDTO> findAll() ;
	public List<StadiumDTO> find_grant() ;
	public void update(List<StadiumDTO> stadiums);
}
