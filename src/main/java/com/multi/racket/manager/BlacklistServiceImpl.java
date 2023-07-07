package com.multi.racket.manager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.BlacklistDTO;
import com.multi.racket.repository.BlacklistRepository;
import com.multi.racket.repository.MemberRepository;

@Service
public class BlacklistServiceImpl implements BlacklistService {
	private BlacklistRepository bRepository;
	private MemberRepository mRepository;
	
	@Autowired
	public BlacklistServiceImpl(BlacklistRepository bRepository, MemberRepository mRepository) {
		super();
		this.bRepository = bRepository;
		this.mRepository = mRepository;
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
	
	@Override
    @Transactional
    public void updateMemberAuthAndDeleteBlacklist(int blacklistNo) {
        BlacklistDTO blacklist = bRepository.findById(blacklistNo).orElse(null);
        if (blacklist != null) {
            String memberId = blacklist.getMemberId();
            System.out.println("service: "+memberId);
            // member_auth를 1로 수정
            mRepository.updateMemberAuthByMemberId(memberId);

            System.out.println("service: "+blacklistNo);
            // 블랙리스트 삭제
            bRepository.deleteById(blacklistNo);
        }
    }

	@Override
	public void deleteExpiredBlacklist(LocalDate currentDate) {
		// 현재 날짜 이후의 블랙리스트 데이터를 삭제하는 로직
        List<BlacklistDTO> expiredBlacklist = bRepository.findByBlackDateBefore(currentDate);
        bRepository.deleteAll(expiredBlacklist);
	}
}
