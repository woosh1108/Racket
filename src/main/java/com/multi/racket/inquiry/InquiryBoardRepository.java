package com.multi.racket.inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import com.multi.racket.domain.InquiryDTO;
public interface InquiryBoardRepository extends JpaRepository<InquiryDTO, Integer> {

}
