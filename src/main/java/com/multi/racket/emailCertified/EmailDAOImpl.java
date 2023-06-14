//package com.multi.racket.emailCertified;
//
//import java.util.Random;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Repository;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//@Repository
//public class EmailDAOImpl implements EmailDAO {
//	//의존성 주입을 통해서 필요한 객체를 가져온다.
//    private JavaMailSender emailSender;
//    // 타임리프를사용하기 위한 객체를 의존성 주입으로 가져온다
//    private SpringTemplateEngine templateEngine;
//    private String authNum; //랜덤 인증 코드
//    @Autowired
//	public EmailDAOImpl(JavaMailSender emailSender, SpringTemplateEngine templateEngine, String authNum) {
//		super();
//		this.emailSender = emailSender;
//		this.templateEngine = templateEngine;
//		this.authNum = authNum;
//	}
//
//	@Override
//	public void createCode() {
//		Random random = new Random();
//        StringBuffer key = new StringBuffer();
//
//        for(int i=0;i<8;i++) {
//            int index = random.nextInt(3);
//
//            switch (index) {
//                case 0 :
//                    key.append((char) ((int)random.nextInt(26) + 97));
//                    break;
//                case 1:
//                    key.append((char) ((int)random.nextInt(26) + 65));
//                    break;
//                case 2:
//                    key.append(random.nextInt(9));
//                    break;
//            }
//        }
//        System.out.println(key);
//        authNum = key.toString();
//	}
//
//	@Override
//	public MimeMessage createEmailForm(String email) {
//		createCode(); //인증 코드 생성
//        String setFrom = "jhjooTest0221@gmail.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
//        String toEmail = email; //받는 사람
//        String title = "회원가입 인증 번호"; //제목
//        String content = 
//				"홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 ! 
//                "<br><br>" + 
//			    "인증 번호는 " + authNum + "입니다." + 
//			    "<br>" + 
//			    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
//        MimeMessage message = emailSender.createMimeMessage(); 
//        try {
//			message.addRecipients(MimeMessage.RecipientType.TO, email);//보낼 이메일 설정
//			InternetAddress fromAddress = new InternetAddress(setFrom);
//	        message.setFrom(fromAddress); //보내는 이메일
//	        message.setSubject(title); //제목 설정
//	        message.setText(setContext(authNum), "utf-8", "html");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//        
//        return message;
//	}
//
//	@Override
//	public String sendEmail(String toEmail) {
//		//메일전송에 필요한 정보 설정
//        MimeMessage emailForm = createEmailForm(toEmail);
//        //실제 메일 전송
//        emailSender.send(emailForm);
//
//        return authNum; //인증 코드 반환
//	}
//
//	@Override
//	public String setContext(String code) {
//		  Context context = new Context();
//	      context.setVariable("code", code);
//	      return templateEngine.process("mail", context); //mail.html
//	}
//
//}
