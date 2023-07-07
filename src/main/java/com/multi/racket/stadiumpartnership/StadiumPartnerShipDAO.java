package com.multi.racket.stadiumpartnership;

import java.util.List;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.dto.CourtOperatingHoursListDTO;
import com.multi.racket.dto.StadiumCourtListDTO;

public interface StadiumPartnerShipDAO {
	public StadiumDTO partnership_insert(StadiumDTO stadium);
	public void partnershipfile_insert(List<StadiumFileDTO> stadiumfile);
	public List<StadiumFileDTO> find_file_grant(StadiumDTO stadium);
	public List<StadiumFileDTO> find_file(int stadiumNo);
	public StadiumcourtDTO court_insert(StadiumDTO stadium,StadiumcourtDTO court);
	public StadiumcourtDTO court_insert(StadiumDTO stadium,StadiumCourtListDTO courtlist);
	public CourtoperatinghoursDTO hours_insert(CourtoperatinghoursDTO hours,StadiumcourtDTO court);
	public CourtoperatinghoursDTO hours_insert(CourtOperatingHoursListDTO hours,StadiumCourtListDTO court);
}
