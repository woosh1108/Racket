package com.multi.racket.inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryPageService {
	InquiryPageDAO dao;
	@Autowired
	public InquiryPageService(InquiryPageDAO dao) {
		super();
		this.dao = dao;
	}

	public PageDTO findAll(int pageNo) { 
		 return dao.findAll(pageNo); 
	}

	
}
