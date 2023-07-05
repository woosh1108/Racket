package com.multi.racket.manager;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.BlacklistDTO;

public interface BlacklistService {
	// 블랙리스트 조회
	public Page<BlacklistDTO> blacklist(int pageNo);
	
	// 블랙리스트 사유
	public String getBlacklistReason(int blacklistNo);
	
	// 블랙리스트 삭제
	void updateMemberAuthAndDeleteBlacklist(int blacklistNo);
	
	// black_date가 지난 블랙리스트 데이터를 삭제하는 메서드
    void deleteExpiredBlacklist(LocalDate currentDate);

}
