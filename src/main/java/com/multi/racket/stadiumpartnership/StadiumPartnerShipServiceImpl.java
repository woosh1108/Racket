package com.multi.racket.stadiumpartnership;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.dto.CourtOperatingHoursListDTO;
import com.multi.racket.dto.StadiumCourtListDTO;
@Service
@Transactional
public class StadiumPartnerShipServiceImpl implements StadiumPartnerShipService {
	StadiumPartnerShipDAO dao;
	@Autowired
	public StadiumPartnerShipServiceImpl(StadiumPartnerShipDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public StadiumDTO partnership_insert(StadiumDTO stadium) {
		return dao.partnership_insert(stadium);
	}

	@Override
	public void insert(StadiumDTO stadium, List<StadiumFileDTO> stadiumfiledtolist) {
		partnership_insert(stadium);
//		dao.partnershipfile_insert(stadiumfiledtolist);
		dao.partnership_insert(stadium);
		
	}

	@Override
	public List<StadiumFileDTO> file_insert(List<StadiumFileDTO> stadiumfile) {
		dao.partnershipfile_insert(stadiumfile);
		return stadiumfile;
	}

	@Override
	public List<StadiumFileDTO> find_file_grant(StadiumDTO stadium) {
		return dao.find_file_grant(stadium);
	}

	@Override
	public List<StadiumFileDTO> find_file(int stadiumNo) {
		return dao.find_file(stadiumNo);
	}

	@Override
	public StadiumcourtDTO court_insert(StadiumDTO stadium,StadiumcourtDTO court) {
		return dao.court_insert(stadium,court);
	}

	@Override
	public CourtoperatinghoursDTO hours_insert(CourtoperatinghoursDTO hours, StadiumcourtDTO court) {
		return dao.hours_insert(hours, court);
	}

	@Override
	public StadiumcourtDTO court_insert(StadiumDTO stadium, StadiumCourtListDTO court) {
		return dao.court_insert(stadium, court);
	}

	@Override
	public CourtoperatinghoursDTO hours_insert(CourtOperatingHoursListDTO hours, StadiumCourtListDTO court) {
		return dao.hours_insert(hours, court);
	}

	@Override
	public StadiumDTO find_stadiumno(int stadiumNo) {
		return dao.find_stadiumno(stadiumNo);
	}

	@Override
	public void insert_court(int stadiumNo, StadiumcourtDTO court, CourtOperatingHoursListDTO hours) {
		dao.addcourt(stadiumNo, court);
		dao.addcourthour(hours, court);
	}


}
