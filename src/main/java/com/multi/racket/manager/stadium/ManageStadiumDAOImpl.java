package com.multi.racket.manager.stadium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
@Repository
public class ManageStadiumDAOImpl implements ManageStadiumDAO {
	ManageStadiumRepository repository;
	@Autowired
	public ManageStadiumDAOImpl(ManageStadiumRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public StadiumDTO update(StadiumDTO stadium) {
		StadiumDTO stadiumlist = repository.findById(stadium.getMemberId()).orElseThrow();
		stadiumlist.setStadiumStatus(1);
		return null;
	}

}
