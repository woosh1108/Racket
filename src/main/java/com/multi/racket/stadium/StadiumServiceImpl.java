package com.multi.racket.stadium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
@Service
public class StadiumServiceImpl implements StadiumService {
	StadiumDAO dao;
	
	@Autowired
	public StadiumServiceImpl(StadiumDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public StadiumDTO getStadium(int stadiumNo) {
		return dao.getStadium(stadiumNo);
	}
	
	public int getStadiumCourtCount(int stadiumNo) {
		return dao.getStadiumCourtCount(stadiumNo);
	}

	@Override
	public Page<StadiumDTO> stadiumlist(int pageNo) {
		return dao.stadiumlist(pageNo);
	}

	@Override
	public Page<StadiumDTO> searchStadiums(String type, String keyword, int pageNo, int pageSize) {
		return dao.searchStadiums(type, keyword, pageNo, pageSize);
	}

	@Override
	public List<StadiumcourtDTO> getCourtslistByStadiumNo(int stadiumNo) {
		return dao.getCourtslistByStadiumNo(stadiumNo);
	}

	

}
