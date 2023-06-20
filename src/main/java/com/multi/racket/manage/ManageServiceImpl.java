package com.multi.racket.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.StadiumDTO;
@Service
@Transactional
public class ManageServiceImpl implements ManageService {
	ManageDAO dao;
	@Autowired
	public ManageServiceImpl(ManageDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<StadiumDTO> findAll() {
		return dao.findAll();
	}

	@Override
	public List<StadiumDTO> find_grant() {
		return dao.find_grant();
	}
	@Override
	public void update(StadiumDTO stadium) {
		dao.update(stadium);
	}
}
