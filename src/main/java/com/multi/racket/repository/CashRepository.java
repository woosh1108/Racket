package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.CashDTO;

public interface CashRepository extends JpaRepository<CashDTO, String> {
	@Query(value = "SELECT total_amount FROM cash WHERE member_id = :memberId ORDER BY cash_no DESC LIMIT 1", nativeQuery = true)
    int findLatestTotalAmountByMemberId(@Param("memberId") String memberId);
	
}
