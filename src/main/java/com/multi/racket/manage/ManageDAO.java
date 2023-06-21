package com.multi.racket.manage;

import java.util.List;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;

public interface ManageDAO {
	public List<StadiumDTO> findAll() ;
	public List<MemberDTO> findUser();
	public List<StadiumDTO> find_grant() ;
//	public StadiumDTO update(StadiumDTO stadium);
	public void update(List<StadiumDTO> stadiums);
}
