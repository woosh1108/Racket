package com.multi.racket.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.multi.racket.domain.CashDTO;
public interface CashRepository extends JpaRepository<CashDTO, String> {
	@Query(value = "SELECT total_amount FROM cash WHERE member_id = :memberId ORDER BY cash_no DESC LIMIT 1", nativeQuery = true)
    int findLatestTotalAmountByMemberId(@Param("memberId") String memberId);
	
	Page<CashDTO> findByMemberId(String id, Pageable pageable);

	CashDTO findByMemberId(String memberId);
	@Query(value = "SELECT * FROM cash WHERE member_id = :memberId ORDER BY cash_no DESC LIMIT 1", nativeQuery = true)
    CashDTO findLatestByMemberId(@Param("memberId") String memberId);
	
	CashDTO findFirstByMemberIdOrderByCashDateDesc(String memberId);
}
