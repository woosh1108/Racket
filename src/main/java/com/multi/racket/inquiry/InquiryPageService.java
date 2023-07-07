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
	
	public PageDTO searchinq(int pageNo, String keyword) {
		return dao.searchinq(pageNo, keyword);
	}
	
	public PageDTO mycash(int pageNo, String id) {
		return dao.mycash(pageNo, id);
	}

	
}
