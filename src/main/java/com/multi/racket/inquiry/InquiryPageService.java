package com.multi.racket.inquiry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multi.racket.domain.InquiryDTO;
@Service
public class InquiryPageService {
	InquiryPageDAO dao;
	@Autowired
	public InquiryPageService(InquiryPageDAO dao) {
		super();
		this.dao = dao;
	}

	public List<InquiryDTO> findAll(int pageNo) { 
		 return dao.findAll(pageNo); 
	}
	
}
