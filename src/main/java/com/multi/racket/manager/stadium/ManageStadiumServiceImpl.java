package com.multi.racket.manager.stadium;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.StadiumDTO;
@Service
@Transactional
public class ManageStadiumServiceImpl implements ManageStadiumService {
	ManageStadiumDAO dao;
	@Autowired
	public ManageStadiumServiceImpl(ManageStadiumDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public StadiumDTO update(StadiumDTO stadium) {
		return dao.update(stadium);
	}

}
