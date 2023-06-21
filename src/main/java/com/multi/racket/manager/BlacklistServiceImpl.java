package com.multi.racket.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.BlacklistDTO;
import com.multi.racket.repository.BlacklistRepository;

@Service
public class BlacklistServiceImpl implements BlacklistService {
	private BlacklistRepository bRepository;
	
	@Autowired
	public BlacklistServiceImpl(BlacklistRepository bRepository) {
		super();
		this.bRepository = bRepository;
	}

	@Override
	public Page<BlacklistDTO> blacklist(int pageNo) {
		int pageSize = 5; // 페이지당 표시할 데이터 수

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("blacklistNo").descending());
        return bRepository.findAll(pageable);
	}

	@Override
	public String getBlacklistReason(int blacklistNo) {
		System.out.println("서비스"+blacklistNo);
		BlacklistDTO blacklist = bRepository.findById(blacklistNo).orElse(null);
		System.out.println("서비스"+blacklist);
        if (blacklist != null) {
            return blacklist.getBlacklistReason();
        }
        return null;
	}
	
}
