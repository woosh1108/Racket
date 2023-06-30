package com.multi.racket.stadium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.StadiumDTO;
@Service
public class StadiumServiceImpl implements StadiumService {
	StadiumDAO dao;
	
	@Autowired
	public StadiumServiceImpl(StadiumDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<StadiumDTO> stadiumList(int pageNo) {
		return dao.stadiumList(pageNo);
	}

	@Override
	public StadiumDTO getStadium(int stadium_no) {
		return dao.getStadium(stadium_no);
	}

	@Override
	public int update(StadiumDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String stadium_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StadiumDTO> search(String tag, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StadiumDTO> getFileList(String stadium_no) {
		// TODO Auto-generated method stub
		return null;
	}

}
