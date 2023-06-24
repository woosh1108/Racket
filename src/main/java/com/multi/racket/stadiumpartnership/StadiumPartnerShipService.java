package com.multi.racket.stadiumpartnership;

import java.util.List;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;

public interface StadiumPartnerShipService {
	public StadiumDTO partnership_insert(StadiumDTO associate);
	public List<StadiumFileDTO> file_insert(List<StadiumFileDTO> stadiumlist);
	public void insert(StadiumDTO stadium,List<StadiumFileDTO> stadiumfiledtolist);
	public List<StadiumFileDTO> find_file_grant(StadiumDTO stadium);
}
