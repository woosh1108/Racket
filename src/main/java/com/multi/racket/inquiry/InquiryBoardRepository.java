package com.multi.racket.inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.multi.racket.domain.InquiryDTO;
@Repository
public interface InquiryBoardRepository extends JpaRepository<InquiryDTO, Integer> {

}
