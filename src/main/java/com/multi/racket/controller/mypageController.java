package com.multi.racket.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;

import com.multi.racket.inquiry.InquiryPageService;
import com.multi.racket.inquiry.PageDTO;
import com.multi.racket.member.MemberService;
@Controller
@RequestMapping("/mypage") // 공유메핑명
@SessionAttributes("user") // 데이터공유명
public class mypageController {
	@Autowired
	MemberService service;
	
	@Autowired
	InquiryPageService service2;

	@Autowired
	public mypageController(MemberService service, InquiryPageService service2) {
		super();
		this.service = service;
		this.service2 = service2;
	}

	// 내정보보기페이지 - 수정x
	@RequestMapping("/info")
	public String myInfo() {
		return "thymeleaf/mypage/myInfo";
	}

	// 내정보 수정페이지
	@RequestMapping("/change")
	public String myInfoChange() {
		return "thymeleaf/mypage/myInfo_change";
	}


	// 내정보 수정하기
	@PostMapping("/change.do")
	public String infoChange(MemberDTO updateInfo, Model model) {
    service.update(updateInfo);
		if(updateInfo!=null) {
			model.addAttribute("msg", "정보변경이 완료되었습니다.");
		}
    
		return "redirect:/main";
	}

	// 내 캐쉬내역보기 - 충전가능
	@RequestMapping("/cash")
	public String myCash(Model model, @RequestParam("pageNo") String pageNo, String id, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		id = member.getMemberId();
		System.out.println(pageNo);
		PageDTO page = service2.mycash(Integer.parseInt(pageNo), id);
		List<CashDTO> list = page.getCashlist();
		int totalPageNumber = page.getTotalPageNumber();
		model.addAttribute("mycash", list);
		model.addAttribute("totalPageNumber", totalPageNumber);
		model.addAttribute("member", member);
		return "thymeleaf/mypage/myCash";
	}

	
	// 내 예약 내역보기
		@RequestMapping("/reservation")
		public String myReservation(String memberId, Date reservationDate, Model model, @RequestParam(defaultValue = "0") int pageNo) {
			// 현재 날짜 가져오기
			LocalDate today = LocalDate.now();
			Date currentDate = Date.valueOf(today);

			// 3일  후 짜 계산하기
			LocalDate threeDaysAgoLocalDate = today.plusDays(2);
			Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
			reservationDate = threeDaysAgoDate;
			
			List<ReservationDTO> date = service.reservationDate(reservationDate);
		    Page<ReservationDTO> reservationPage = service.reservationPage(memberId, pageNo);
		    List<ReservationDTO> reservationUser = reservationPage.getContent();
		    
			if (reservationUser.size() != 0) {
				model.addAttribute("reservationUser", reservationUser);
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", reservationPage.getTotalPages());
			} else {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", reservationPage.getTotalPages());
				model.addAttribute("msg", "나의 예약 기록이 없습니다.");
			}
			return "thymeleaf/mypage/myReservation";
		}
		// 내 강의 내역보기
		@RequestMapping("/training")
		public String myTraining(String memberId, Date trainingDate, Model model, @RequestParam(defaultValue = "0") int pageNo) {
			// 현재 날짜 가져오기
			LocalDate today = LocalDate.now();
			Date currentDate = Date.valueOf(today);
			// 3일 후 날짜 계산하기
			LocalDate threeDaysAgoLocalDate = today.plusDays(2);
			Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
			trainingDate = threeDaysAgoDate;

			List<TrainingDTO> date = service.trainingDate(trainingDate);
		    Page<TrainingDTO> trainingPage = service.trainingPage(memberId, pageNo);
		    List<TrainingDTO> training = trainingPage.getContent();
			
			if (training.size() != 0) {
				model.addAttribute("training", training);
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", trainingPage.getTotalPages());
			} else {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", trainingPage.getTotalPages());
				model.addAttribute("msg", "나의 강습 기록이 없습니다.");
			}

			return "thymeleaf/mypage/myTraining";
		}
	// 내 매치보기 - 신고가능
	@RequestMapping("/match")
	public String myMatch() {
		return "thymeleaf/mypage/myMatch";
	}
  // 내 매치보기 - 신고가능
	@RequestMapping("/trainingAttend")
	public String myTrainingAttend() {
		return "thymeleaf/mypage/myTrainingAttend";
	}
}
