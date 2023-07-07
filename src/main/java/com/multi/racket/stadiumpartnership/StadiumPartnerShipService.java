package com.multi.racket.stadiumpartnership;

import java.util.List;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.dto.CourtOperatingHoursListDTO;
import com.multi.racket.dto.StadiumCourtListDTO;

public interface StadiumPartnerShipService {
	public StadiumDTO partnership_insert(StadiumDTO associate);
	public List<StadiumFileDTO> file_insert(List<StadiumFileDTO> stadiumlist);
	public void insert(StadiumDTO stadium,List<StadiumFileDTO> stadiumfiledtolist);
	public List<StadiumFileDTO> find_file_grant(StadiumDTO stadium);
	public List<StadiumFileDTO> find_file(int stadiumNo);
	public StadiumcourtDTO court_insert(StadiumDTO stadium,StadiumcourtDTO court);
	public StadiumcourtDTO court_insert(StadiumDTO stadium,StadiumCourtListDTO court);
	public CourtoperatinghoursDTO hours_insert(CourtoperatinghoursDTO hours,StadiumcourtDTO court);
	public CourtoperatinghoursDTO hours_insert(CourtOperatingHoursListDTO hours,StadiumCourtListDTO court);
}
