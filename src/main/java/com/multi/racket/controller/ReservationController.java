package com.multi.racket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.cash.CashService;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.reservation.ReservationService;

@Controller
public class ReservationController {
	ReservationService service;
	CashService cashService;
	

	@Autowired
	public ReservationController(ReservationService service, CashService cashService) {
		super();
		this.service = service;
		this.cashService = cashService;
	}


//	 @GetMapping("/reservation/read/{stadiumNo}")
//	    public String getStadiumData(@PathVariable int stadiumNo, Model model) {
//	        StadiumDTO stadium = stadiumReadService.getStadiumData(stadiumNo);
//	        model.addAttribute("stadium", stadium);
//	        return "thymeleaf/reservation/reservation";
//	    }
	
	@RequestMapping("/reservation/read")
	public String reservation(int reservationNo) {
		// ReservationDTO reservation = service.reservation(reservationNo);
		return "thymeleaf/reservation/reservation";
	}

	// 예약하기 등록
	@PostMapping(value = "/reservation/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String reservationInsert(HttpSession session, ReservationDTO reservation, CashDTO cash) {
		try {
			// 현재 세션값에서 member_id 가져오기
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();
			
			// 세션에 memberId 값을 저장
			System.out.println("memberId입니다 : "+ memberId);

			// Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
			CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
			int totalAmount = latestCash.getTotalAmount();

			// Reservation 테이블에서 예약에 필요한 reservation_fee 가져오기
			int reservationFee = reservation.getReservationFee();

			// 잔액 비교
			if (totalAmount >= reservationFee) {
				// Cash 테이블과 Reservation 테이블에 insert
				service.reservation_insert(memberId, reservation, cash);
				return "thymeleaf/reservation/reservation";
			} else {
				// 잔액 부족으로 캐시 충전 페이지로 이동
				return "redirect:/cash/recharge";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "thymeleaf/main/main_intro";
		}
	}

	// 예약 참가하기 상세조회
	@RequestMapping("/matching/read")
	public String matching(int matchingNo) {
		//MatchingDTO matching = service.matching(matchingNo);
		return "thymeleaf/reservation/matching";
	}

	// 예약 참가하기 등록
	@PostMapping(value = "/matching/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String matchingInsert(HttpSession session,MatchingDTO matching, CashDTO cash) {
		try {
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();

			CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
			int totalAmount = latestCash.getTotalAmount();

			//int reservationFee = reservation.getReservationFee();
			int reservationFee = 2000;

			// 잔액 비교
			if (totalAmount >= reservationFee) {
				service.matching_insert(memberId, matching, cash);
				return "thymeleaf/reservation/matching";
			} else {
				return "redirect:/cash/recharge";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "thymeleaf/main/main_intro";
		}
	}

}