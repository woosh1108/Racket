package com.multi.racket.stadium;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;

public interface StadiumService {
	// 구장리스트 조회
	public Page<StadiumDTO> stadiumlist(int pageNo);
	public StadiumDTO getStadium(int stadiumNo);
	public int getStadiumCourtCount(int stadiumNo);
	public Page<StadiumDTO> searchStadiums(String type, String keyword, int pageNo, int pageSize);

	List<StadiumcourtDTO> getCourtslistByStadiumNo(int stadiumNo);
}
