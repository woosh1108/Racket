package com.multi.racket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.multi.racket.cash.CashService;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.reservation.StadiumReadService;
import com.multi.racket.training.TrainingService;

@Controller
public class TrainingController {
	TrainingService service;
	CashService cashService;
	StadiumReadService stadiumReadService;
	
	@Autowired
	public TrainingController(TrainingService service, CashService cashService, StadiumReadService stadiumReadService) {
		super();
		this.service = service;
		this.cashService = cashService;
		this.stadiumReadService = stadiumReadService;
	}

	// 구장번호로 강습하기 상세조회
	@GetMapping("/training/read/{courtNo}")
	public String getStadiumDetail(@PathVariable int courtNo, Model model) {
		StadiumcourtDTO court = stadiumReadService.getStadiumDetail(courtNo);
		model.addAttribute("court", court);
		System.out.println("controller: "+court.getOperatingHours());
		return "thymeleaf/reservation/training";
	}

	// 강습하기 등록
	@PostMapping(value = "/training/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String trainingInsert(HttpSession session, TrainingDTO training, CashDTO cash) {
		try {
			// 현재 세션값에서 member_id 가져오기
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();

			// Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
			CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
			int totalAmount = latestCash.getTotalAmount();

			// Reservation 테이블에서 예약에 필요한 reservation_fee 가져오기
			//int trainingFee = stadium.getStadiumFee();
			int trainingFee = 2000;

			// 잔액 비교
			if (totalAmount >= trainingFee) {
				// Cash 테이블과 Reservation 테이블에 insert
				service.training_insert(memberId, training, cash);
				return "thymeleaf/reservation/training";
			} else {
				// 잔액 부족으로 캐시 충전 페이지로 이동
				return "redirect:/cash/recharge";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "thymeleaf/main/main_intro";
		}
	}

	// 강습 참가하기 상세조회
	@GetMapping("/training/memberlist/read/{trainingNo}")
	public String getTrainingDetail(@PathVariable int trainingNo, Model model) {
		TrainingDTO training = stadiumReadService.getTrainingDetail(trainingNo);
	    int participantCount = stadiumReadService.getTrainingParticipantCount(trainingNo); // 예약한 인원 수 조회
		model.addAttribute("training", training);
	    model.addAttribute("participantCount", participantCount); // 모델에 인원 수 추가
	    System.out.println("controller: "+participantCount);
		return "thymeleaf/reservation/training_memberlist";
	}
	
	// 강습 참가하기 등록
	@PostMapping(value = "/training/memberlist/insert")
	public String trainingMemberlistInsert(HttpSession session, TrainingMemberlistDTO trainingMemberlist) {
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		String memberId = user.getMemberId();
		service.trainingMemberlist_insert(memberId, trainingMemberlist);
		return "thymeleaf/reservation/training_memberlist";
	}
}
