package com.multi.racket.stadiumpartnership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.StadiumDTO;
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
	public StadiumDTO partnership_insert(StadiumDTO associate) {
		return dao.partnership_insert(associate);
	}

}