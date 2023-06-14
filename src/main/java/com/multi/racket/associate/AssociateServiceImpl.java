package com.multi.racket.associate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.StadiumDTO;
@Service
@Transactional
public class AssociateServiceImpl implements AssociateService {
	AssociateDAO dao;
	@Autowired
	public AssociateServiceImpl(AssociateDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<StadiumDTO> findAll() {
		return dao.findAll();
	}

}
