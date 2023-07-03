package com.multi.racket.stadium;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
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
		Page<StadiumDTO> page = repository.findAllByStadiumStatus(1, pageRequest);
		System.out.println(page);
		List<StadiumDTO> list = page.getContent();
		List<StadiumDTO> statuslist = new ArrayList<StadiumDTO>();
		System.out.println(list);
		for(StadiumDTO stadium : list) {
	        if(stadium.getStadiumStatus()==1) {
	        	System.out.println("adssasadsad"+repository.findByStadiumStatus(1));
	            statuslist.add(stadium);
	        }
	    }
		return list;
	}

	@Override
	public StadiumDTO getStadium(int stadiumNo) {
		StadiumDTO stadium = repository.findByStadiumNo(stadiumNo)
				.orElseThrow(() -> new IllegalArgumentException("Invalid stadium ID: " + stadiumNo));
    	System.out.println("ServiceImpl: "+stadium);
    	return stadium;
	}
	
	// 코드 갯수
	@Override
	public int getStadiumCourtCount(int stadiumNo) {
		return stadiumCourtRepository.countStadiumCourtsByStadiumNo(stadiumNo);
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
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public List<StadiumDTO> getFileList(String stadium_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<StadiumDTO> stadiumlist(int pageNo) {
		int pageSize = 10; // 페이지당 표시할 데이터 수

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("stadiumNo").descending());
        return repository.findAllByStadiumStatus(1, pageable);
	}


	@Override
	public Page<StadiumDTO> searchStadiums(String type, String keyword, int pageNo, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("stadiumNo").descending());

	    if (type.equals("name")) {
	        return repository.findByStadiumNameContaining(keyword, pageable);
	    } else if (type.equals("addr")) {
	        return repository.findByStadiumAddrContaining(keyword, pageable);
	    } else {
	        return Page.empty(); // 빈 페이지 반환
	    }
	}


	@Override
	public List<StadiumcourtDTO> getCourtslistByStadiumNo(int stadiumNo) {
		StadiumDTO stadium = repository.getById(stadiumNo);
		List<StadiumcourtDTO> stadiumcourtlist = stadiumCourtRepository.findAllByStadiumNo(stadium);
		return stadiumcourtlist;
	}



}
