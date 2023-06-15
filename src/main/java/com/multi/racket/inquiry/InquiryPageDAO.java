package com.multi.racket.inquiry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.multi.racket.domain.InquiryDTO;
@Repository
public class InquiryPageDAO {
	InquiryBoardRepository repository;
	@Autowired
	public InquiryPageDAO(InquiryBoardRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<InquiryDTO> findAll(int pageNo) {
		PageRequest inqPage = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC,"inquiryNo"));
		Page<InquiryDTO> page = repository.findAll(inqPage);
		List<InquiryDTO> list = page.getContent();
		return list;
	}
	
}
