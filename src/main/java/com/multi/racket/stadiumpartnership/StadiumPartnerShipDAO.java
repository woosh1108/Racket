package com.multi.racket.stadiumpartnership;

import java.util.List;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;

public interface StadiumPartnerShipDAO {
	public StadiumDTO partnership_insert(StadiumDTO stadium);
	public void partnershipfile_insert(List<StadiumFileDTO> stadiumfile);
}
