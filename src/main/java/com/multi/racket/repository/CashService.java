package com.multi.racket.repository;

import com.multi.racket.domain.CashDTO;

public interface CashService {
    CashDTO getLatestCashByMemberId(String memberId);
    void saveCash(CashDTO cash);
}
