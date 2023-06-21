package com.multi.racket.manager;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.BlacklistDTO;

public interface BlacklistService {
	// 블랙리스트 조회
	public Page<BlacklistDTO> blacklist(int pageNo);
	
	// 블랙리스트 사유
	public String getBlacklistReason(int blacklistNo);
}
