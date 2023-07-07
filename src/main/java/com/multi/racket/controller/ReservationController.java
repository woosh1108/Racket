package com.multi.racket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.racket.cash.CashService;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.inquiry.InquiryService;
import com.multi.racket.reservation.ReservationService;
import com.multi.racket.reservation.StadiumReadService;

@Controller
public class ReservationController {
	ReservationService service;
	CashService cashService;
	StadiumReadService stadiumReadService;
	InquiryService iservice;

	@Autowired
	public ReservationController(ReservationService service, CashService cashService,
			StadiumReadService stadiumReadService,InquiryService iservice) {
		super();
		this.service = service;
		this.cashService = cashService;
		this.stadiumReadService = stadiumReadService;
		this.iservice = iservice;
	}

	// 예약하기 상세조회
	@GetMapping("/reservation/read/{courtNo}")
	public String getStadiumDetail(@PathVariable int courtNo, Model model, HttpServletRequest request,
			HttpSession session, RedirectAttributes redirectAttributes) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
		if (sessions == null || sessions.getAttribute("user") == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "로그인 후 이용 가능합니다.");
			return "redirect:/login";
		}

		StadiumcourtDTO court = stadiumReadService.getStadiumDetail(courtNo);
		List<CourtoperatinghoursDTO> hourlist = stadiumReadService.getHourtslistByCourtNo(courtNo);
		model.addAttribute("court", court);
		model.addAttribute("hourlist", hourlist);
		System.out.println("controller court: " + court);
		System.out.println("controller: " + hourlist);

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
	public String reservationInsert(HttpSession session, ReservationDTO reservation, CashDTO cash, RedirectAttributes redirectAttributes) {
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
			CourtoperatinghoursDTO courtoperatinghours = stadiumReadService
					.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
			StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
			StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());
			int stadiumFee = stadium.getStadiumFee();

			// 잔액 비교
			if (totalAmount >= stadiumFee) {
				// Cash 테이블과 Reservation 테이블에 insert
				service.reservation_insert(memberId, reservation, cash);

				
				int ntamount = cash.getTotalAmount(); MemberDTO member =
				(MemberDTO)session.getAttribute("user"); String id = member.getMemberId();
				MemberDTO upmem =  iservice.update(id,ntamount);
				session.setAttribute("user", upmem);
		        
				redirectAttributes.addFlashAttribute("alertMessage", "구장 예약에 성공했습니다.");
				
				return "redirect:/mypage/reservation";
			} else {
				// 잔액 부족으로 캐시 충전 페이지로 이동
				redirectAttributes.addFlashAttribute("alertMessage", "잔액이 부족합니다.");
				return "redirect:/mypage/cash?pageNo=0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "구장 예약에 실패했습니다.");
			return "redirect:/stadium/stadiumlist";
		}
	}

	// 예약 참가하기 상세조회
	@GetMapping("/matching/read/{reservationNo}")
	public String getReservationDetail(@PathVariable int reservationNo, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
		if (sessions == null || sessions.getAttribute("user") == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "로그인 후 이용 가능합니다.");
			return "redirect:/login";
		}

		ReservationDTO reservation = stadiumReadService.getReservationDetail(reservationNo);
		int participantCount = stadiumReadService.getReservationParticipantCount(reservationNo); // 예약한 인원 수 조회
		model.addAttribute("reservation", reservation);
		model.addAttribute("participantCount", participantCount); // 모델에 인원 수 추가

		CourtoperatinghoursDTO courtoperatinghours = stadiumReadService
				.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
		StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
		StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());
		MemberDTO member = stadiumReadService.findMemberByMemberId(reservation.getMemberId());

		// 이후에 필요한 작업을 수행하고 필요한 데이터를 모델에 담습니다.
		model.addAttribute("stadium", stadium);
		model.addAttribute("stadiumcourt", stadiumcourt);
		model.addAttribute("courtoperatinghours", courtoperatinghours);
		model.addAttribute("member", member);

		// 현재 세션값에서 member_id 가져오기
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		String memberId = user.getMemberId();
		// Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
		CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
		int totalAmount = latestCash.getTotalAmount();
		model.addAttribute("totalAmount", totalAmount);

		return "thymeleaf/reservation/matching";
	}

	// 예약 참가하기 등록
	@PostMapping(value = "/matching/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String matchingInsert(HttpSession session, MatchingDTO matching, CashDTO cash, RedirectAttributes redirectAttributes) {
		try {
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();
			String memberGender = user.getMemberGender();
			String memberGrade = user.getMemberGrade();
			ReservationDTO reservation = stadiumReadService.getReservationDetail(matching.getReservationNo());
			
			// 해당 아이디로 이미 예약된 매칭 정보가 있는지 확인
	        boolean hasExistingReservation = service.existsByMemberIdAndReservationNo(memberId, matching.getReservationNo());
	        
	        if (!hasExistingReservation && !memberId.equals(reservation.getMemberId()) && memberGrade.equals(reservation.getGradeSetting()) && (memberGender.equals(reservation.getReservationGender()) || reservation.getReservationGender().equals("혼합"))) {
				CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
				int totalAmount = latestCash.getTotalAmount();
	
				int reservationFee = reservation.getReservationFee();
				System.out.println(reservationFee);
	
				// 잔액 비교
				if (totalAmount >= reservationFee) {
					service.matching_insert(memberId, matching, cash, reservation);
					redirectAttributes.addFlashAttribute("alertMessage", "예약 참가에 성공했습니다.");
					return "redirect:/mypage/match";
				} else {
					redirectAttributes.addFlashAttribute("alertMessage", "잔액이 부족합니다.");
					return "redirect:/mypage/cash";
				}
	        } else {
	            // 이미 예약된 매칭 정보가 있을 경우에 처리할 내용 추가
				redirectAttributes.addFlashAttribute("alertMessage", "이미 신청한 예약이거나  조건이 맞지 않아 실패했습니다.");
	            return "redirect:/reservation/reservationlist";
	        }
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "예약 참가에 실패했습니다.");
			return "redirect:/reservation/reservationlist";
		}
	}
	
	// 예약 목록보기
	@RequestMapping("/reservation/reservationlist")
	public String reservationlist(Model model, @RequestParam(defaultValue = "0") int pageNo) {
		Page<ReservationDTO> reservationlistPage = service.reservationlist(pageNo);
		List<ReservationDTO> reservationlist = reservationlistPage.getContent();

		model.addAttribute("reservationlist", reservationlist);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", reservationlistPage.getTotalPages());
		
		// ReservationDTO 목록을 가져오면서 같이 관련된 정보들을 설정합니다.
	    for (ReservationDTO reservation : reservationlist) {
	        // ReservationDTO에 대한 CourtoperatinghoursDTO를 가져옵니다.
	    	CourtoperatinghoursDTO courtoperatinghours = stadiumReadService.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
		    StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
		    StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());

		    model.addAttribute("stadium", stadium);
		    model.addAttribute("stadiumcourt", stadiumcourt);
		    model.addAttribute("courtoperatinghours", courtoperatinghours);
	    }
			
		return "thymeleaf/reservation/reservationlist";
	}

	@GetMapping("/reservations/search")
    public String searchStadiums(@RequestParam String type, @RequestParam String keyword, @RequestParam(defaultValue = "0") int pageNo, Model model) {
        int pageSize = 10; // 페이지당 표시할 데이터 수

        Page<ReservationDTO> reservationPage = service.searchReservations(type, keyword, pageNo, pageSize);
        List<ReservationDTO> reservationlist = reservationPage.getContent();

        model.addAttribute("reservationlist", reservationlist);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", reservationPage.getTotalPages());
        
        // TrainingDTO 목록을 가져오면서 같이 관련된 정보들을 설정합니다.
	    for (ReservationDTO reservation : reservationlist) {
	        // TrainingDTO에 대한 CourtoperatinghoursDTO를 가져옵니다.
	    	CourtoperatinghoursDTO courtoperatinghours = stadiumReadService.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
		    StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
		    StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());

		    model.addAttribute("stadium", stadium);
		    model.addAttribute("stadiumcourt", stadiumcourt);
		    model.addAttribute("courtoperatinghours", courtoperatinghours);
	    }
	    
        return "thymeleaf/reservation/reservationlist";
    }
}