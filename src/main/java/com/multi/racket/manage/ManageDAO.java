package com.multi.racket.manage;

import java.util.List;

import com.multi.racket.domain.StadiumDTO;

public interface ManageDAO {
	public List<StadiumDTO> findAll() ;
	public List<StadiumDTO> find_grant() ;
//	public StadiumDTO update(StadiumDTO stadium);
}
