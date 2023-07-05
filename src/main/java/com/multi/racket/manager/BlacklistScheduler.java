package com.multi.racket.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class BlacklistScheduler {

    @Autowired
    private BlacklistService blacklistService;

    // 매일 자정에 실행되도록 스케줄링 설정
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredBlacklist() {
        // 현재 날짜로부터 지난 블랙리스트 데이터를 삭제하는 로직
        LocalDate currentDate = LocalDate.now();
        blacklistService.deleteExpiredBlacklist(currentDate);
    }
}
