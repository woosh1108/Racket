package com.multi.racket.stadium;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.repository.StadiumCourtRepository;
import com.multi.racket.repository.StadiumRepository;

@Repository
public class StadiumDAOImpl implements StadiumDAO {
	StadiumCourtRepository stadiumCourtRepository;
	StadiumRepository repository;
	
	@Autowired
	public StadiumDAOImpl(StadiumCourtRepository stadiumCourtRepository, StadiumRepository repository) {
		super();
		this.stadiumCourtRepository = stadiumCourtRepository;
		this.repository = repository;
	}
	

	@Override
	public List<StadiumDTO> stadiumList(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC,"stadiumNo")); 
		Page<StadiumDTO> page = repository.findAll(pageRequest);
		System.out.println(page);
		List<StadiumDTO> list = page.getContent();
		System.out.println(list);
		return list;
	}

	@Override
	public StadiumDTO getStadium(int stadiumNo) {
		StadiumDTO stadium = repository.findByStadiumNo(stadiumNo)
				.orElseThrow(() -> new IllegalArgumentException("Invalid stadium ID: " + stadiumNo));
    	System.out.println("ServiceImpl: "+stadium);
    	return stadium;
		//return repository.findByStadiumNo(stadiumNo);
	}
	
	// 코드 갯수
	@Override
	public int getStadiumCourtCount(int stadiumNo) {
		return stadiumCourtRepository.countByStadium(stadiumNo);
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
