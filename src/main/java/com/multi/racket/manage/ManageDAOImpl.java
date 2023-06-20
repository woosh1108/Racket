package com.multi.racket.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
@Repository
public class ManageDAOImpl implements ManageDAO {
	private ManageRepository repository;
	@Autowired
	public ManageDAOImpl(ManageRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<StadiumDTO> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<StadiumDTO> find_grant() {
		return repository.findByStadiumStatus(1);
	}
	@Override
	public void update(List<StadiumDTO> stadiums) {
		 for (StadiumDTO stadium : stadiums) {
		        StadiumDTO stadiumlist = repository.findById(stadium.getStadiumNo()).orElseThrow(() -> new RuntimeException());
		        stadiumlist.setStadiumStatus(stadium.getStadiumStatus());
		        repository.save(stadiumlist);
		    }
	}
	
}
