package com.multi.racket.stadiumpartnership;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.repository.StadiumFileRepository;
@Repository
public class StadiumPartnerShipDAOImpl implements StadiumPartnerShipDAO {
	private StadiumPartnerShipRepository repository;
	private StadiumFileRepository filerepository;
	@Autowired
	public StadiumPartnerShipDAOImpl(StadiumPartnerShipRepository repository, StadiumFileRepository filerepository) {
		super();
		this.repository = repository;
		this.filerepository = filerepository;
	}

	@Override
	public StadiumDTO partnership_insert(StadiumDTO associate) {
		repository.save(associate);
		return associate;
	}

	@Override
	public void partnershipfile_insert(List<StadiumFileDTO> associate) {
		filerepository.saveAll(associate);
	}

	

}
