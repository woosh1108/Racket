package com.multi.racket.stadiumpartnership;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
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

}
