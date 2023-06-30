package com.multi.racket.stadium;

import java.util.List;
import java.util.Optional;

import com.multi.racket.domain.StadiumDTO;

public interface StadiumService {
	List<StadiumDTO> stadiumList(int pageNo);

	Optional<StadiumDTO> getStadium(int stadium_no);

	int update(StadiumDTO board);

	int delete(String stadium_no);

	List<StadiumDTO> search(String tag, String data);

	List<StadiumDTO> getFileList(String stadium_no);
}
