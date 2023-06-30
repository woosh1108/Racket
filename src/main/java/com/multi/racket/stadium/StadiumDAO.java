package com.multi.racket.stadium;

import java.util.List;

import com.multi.racket.domain.StadiumDTO;

public interface StadiumDAO {
	List<StadiumDTO> stadiumList(int pageNo);

	StadiumDTO getStadium(int stadiumNo);

	int update(StadiumDTO board);

	int delete(String stadium_no);

	List<StadiumDTO> search(String tag, String data);

	List<StadiumDTO> getFileList(String stadium_no);

	int getStadiumCourtCount(int stadiumNo);
}
