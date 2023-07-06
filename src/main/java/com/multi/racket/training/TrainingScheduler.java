package com.multi.racket.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class TrainingScheduler {

    @Autowired
    private TrainingService trainingService;

    // 매일 자정에 실행되도록 스케줄링 설정
    @Scheduled(cron = "0 0 0 * * *")
    public void updateExpiredReservations() {
        // 현재 날짜로부터 지난 예약 데이터의 상태를 "경기종료"로 수정하는 로직
        LocalDate currentDate = LocalDate.now();
        trainingService.updateExpiredTrainings(currentDate);
    }
}
