package com.multi.racket.repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.TrainingDTO;

public interface TrainingRepository extends JpaRepository<TrainingDTO, Integer> {
	Optional<TrainingDTO> findByTrainingNo(int trainingNo);
	List<TrainingDTO> findByMemberId(String memberId);
    List<TrainingDTO> findByTrainingDateAfter(Date trainingDate);
    Page<TrainingDTO> findAllByMemberId(String memberId, Pageable pageable);

    // 예약 목록 검색
	Page<TrainingDTO> findByTrainingGradeContaining(String keyword, Pageable pageable);
    Page<TrainingDTO> findByTrainingFeeGreaterThanEqual(int minFee, Pageable pageable);
    Page<TrainingDTO> findByTrainingFeeLessThanEqual(int maxFee, Pageable pageable);
}
