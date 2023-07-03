package com.multi.racket.stadium;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;

public interface StadiumDAO {
	List<StadiumDTO> stadiumList(int pageNo);

	// 구장리스트 조회
	public Page<StadiumDTO> stadiumlist(int pageNo);
	public StadiumDTO getStadium(int stadiumNo);
	public int getStadiumCourtCount(int stadiumNo);
	public Page<StadiumDTO> searchStadiums(String type, String keyword, int pageNo, int pageSize);
	
	int update(StadiumDTO board);

	int delete(String stadium_no);

	List<StadiumDTO> search(String tag, String data);

	List<StadiumDTO> getFileList(String stadium_no);

	List<StadiumcourtDTO> getCourtslistByStadiumNo(int stadiumNo);

}
