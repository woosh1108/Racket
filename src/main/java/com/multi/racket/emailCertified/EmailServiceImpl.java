//package com.multi.racket.emailCertified;
//
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//	EmailDAO dao;
//	@Autowired
//	public EmailServiceImpl(EmailDAO dao) {
//		super();
//		this.dao = dao;
//	}
//	@Override
//	public void createCode() {
//		dao.createCode();
//	}
//
//	@Override
//	public MimeMessage createEmailForm(String email) {
//		return dao.createEmailForm(email);
//	}
//
//	@Override
//	public String sendEmail(String toEmail) {
//		return dao.sendEmail(toEmail);
//	}
//
//	@Override
//	public String setContext(String code) {
//		return dao.setContext(code);
//	}
//
//	
//
//}
