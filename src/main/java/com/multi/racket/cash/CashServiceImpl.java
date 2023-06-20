package com.multi.racket.cash;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.repository.CashRepository;

@Service
public class CashServiceImpl implements CashService {
	private CashRepository cashRepository;

    @Autowired
    public CashServiceImpl(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

	@Override
	public CashDTO getLatestCashByMemberId(String memberId) {
		// 최신 total_amount 가져오기
        int totalAmount = cashRepository.findLatestTotalAmountByMemberId(memberId);
        System.out.println("totalAmount : " + totalAmount);
        // CashDTO 객체 생성 및 값 설정
        CashDTO cash = new CashDTO();
        cash.setMemberId(memberId);
        cash.setTotalAmount(totalAmount);
        
        return cash;
	}

	@Override
	public void saveCash(CashDTO cash) {
		cashRepository.save(cash);
	}

}
