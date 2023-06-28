package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.TrainingMemberlistDTO;

public interface TrainingMemberlistRepository extends JpaRepository<TrainingMemberlistDTO, Integer> {
	@Query("SELECT COUNT(*) FROM TrainingMemberlistDTO t WHERE t.trainingNo = :trainingNo")
    int getParticipantCount(@Param("trainingNo") int trainingNo); // 예약한 인원 수 조회 메서드 추가
}
