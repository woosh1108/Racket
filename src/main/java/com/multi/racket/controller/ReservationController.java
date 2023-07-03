package com.multi.racket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.multi.racket.cash.CashService;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.reservation.ReservationService;
import com.multi.racket.reservation.StadiumReadService;

@Controller
public class ReservationController {
	ReservationService service;
	CashService cashService;
	StadiumReadService stadiumReadService;

	@Autowired
	public ReservationController(ReservationService service, CashService cashService,
			StadiumReadService stadiumReadService) {
		super();
		this.service = service;
		this.cashService = cashService;
		this.stadiumReadService = stadiumReadService;
	}
	
	// 예약하기 상세조회
	@GetMapping("/reservation/read/{courtNo}")
	public String getStadiumDetail(@PathVariable int courtNo, Model model, HttpServletRequest request,HttpSession session) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
	    if (sessions == null || sessions.getAttribute("user") == null) {
	        return "redirect:/login";
	    }
	    
		StadiumcourtDTO court = stadiumReadService.getStadiumDetail(courtNo);
		List<CourtoperatinghoursDTO> hourlist = stadiumReadService.getHourtslistByCourtNo(courtNo);
		model.addAttribute("court", court);
        model.addAttribute("hourlist", hourlist);
		System.out.println("controller court: "+court);
		System.out.println("controller: "+hourlist);
		// 현재 세션값에서 member_id 가져오기
        MemberDTO user = (MemberDTO) session.getAttribute("user");
        String memberId = user.getMemberId();
        // Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
        CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
        int totalAmount = latestCash.getTotalAmount();
        model.addAttribute("totalAmount", totalAmount);
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
			System.out.println("memberId입니다 : " + memberId);

			// Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
			CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
			int totalAmount = latestCash.getTotalAmount();

			// Reservation 테이블에서 예약에 필요한 reservation_fee 가져오기
			int reservationFee = reservation.getReservationFee();

			// 잔액 비교
			if (totalAmount >= reservationFee) {
				// Cash 테이블과 Reservation 테이블에 insert
				service.reservation_insert(memberId, reservation, cash);
				return "redirect:/mypage/reservation";
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
	@GetMapping("/matching/read/{reservationNo}")
	public String getReservationDetail(@PathVariable int reservationNo, Model model, HttpServletRequest request) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
	    if (sessions == null || sessions.getAttribute("user") == null) {
	        return "redirect:/login";
	    }
	    
	    ReservationDTO reservation = stadiumReadService.getReservationDetail(reservationNo);
	    int participantCount = stadiumReadService.getReservationParticipantCount(reservationNo); // 예약한 인원 수 조회
	    model.addAttribute("reservation", reservation);
	    model.addAttribute("participantCount", participantCount); // 모델에 인원 수 추가

	    CourtoperatinghoursDTO courtoperatinghours = stadiumReadService.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
	    StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
	    StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());
	    MemberDTO member = stadiumReadService.findMemberByMemberId(reservation.getMemberId());
	    
	    // 이후에 필요한 작업을 수행하고 필요한 데이터를 모델에 담습니다.
	    model.addAttribute("stadium", stadium);
	    model.addAttribute("stadiumcourt", stadiumcourt);
	    model.addAttribute("courtoperatinghours", courtoperatinghours);
	    model.addAttribute("member", member);
	    
	    return "thymeleaf/reservation/matching";
	}

	// 예약 참가하기 등록
	@PostMapping(value = "/matching/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String matchingInsert(HttpSession session, MatchingDTO matching, CashDTO cash) {
		try {
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();

			CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
			int totalAmount = latestCash.getTotalAmount();

			// int reservationFee = reservation.getReservationFee();
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