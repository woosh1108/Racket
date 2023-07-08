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
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.inquiry.InquiryService;
import com.multi.racket.reservation.StadiumReadService;
import com.multi.racket.training.TrainingService;

@Controller
public class TrainingController {
	TrainingService service;
	CashService cashService;
	StadiumReadService stadiumReadService;
	InquiryService iservice;
	
	@Autowired
	public TrainingController(TrainingService service, CashService cashService, StadiumReadService stadiumReadService,
			InquiryService iservice) {
		super();
		this.service = service;
		this.cashService = cashService;
		this.stadiumReadService = stadiumReadService;
		this.iservice = iservice;
	}

	// 구장번호로 강습하기 상세조회
	@GetMapping("/training/read/{courtNo}")
	public String getStadiumDetail(@PathVariable int courtNo, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
	    if (sessions == null || sessions.getAttribute("user") == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "로그인 후 이용 가능합니다.");
	        return "redirect:/login";
	    }
	    
		StadiumcourtDTO court = stadiumReadService.getStadiumDetail(courtNo);
		List<CourtoperatinghoursDTO> hourlist = stadiumReadService.getHourtslistByCourtNo(courtNo);

		model.addAttribute("court", court);
        model.addAttribute("hourlist", hourlist);
        
		// 현재 세션값에서 member_id 가져오기
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		String memberId = user.getMemberId();
		// Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
		CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
		int totalAmount = latestCash.getTotalAmount();
		model.addAttribute("totalAmount", totalAmount);

        
		return "thymeleaf/reservation/training";
	}

	// 강습하기 등록
	@PostMapping(value = "/training/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String trainingInsert(HttpSession session, TrainingDTO training, CashDTO cash, RedirectAttributes redirectAttributes) {
		try {
			// 현재 세션값에서 member_id 가져오기
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();

			// Cash 테이블에서 현재 사용자의 최신 total_amount 가져오기
			CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
			int totalAmount = latestCash.getTotalAmount();

			// Reservation 테이블에서 예약에 필요한 reservation_fee 가져오기
			CourtoperatinghoursDTO courtoperatinghours = stadiumReadService
								.findCourtoperatinghoursByCourtHourNo(training.getCourtHourNo());
			StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
			StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());
			int stadiumFee = stadium.getStadiumFee();

			// 잔액 비교
			if (totalAmount >= stadiumFee*2) {
				// Cash 테이블과 Reservation 테이블에 insert
				service.training_insert(memberId, training, cash);
				
				int ntamount = cash.getTotalAmount(); 
                MemberDTO member = (MemberDTO)session.getAttribute("user"); 
                String id = member.getMemberId();
                MemberDTO upmem =  iservice.update(id,ntamount);
                session.setAttribute("user", upmem);
				
				redirectAttributes.addFlashAttribute("alertMessage", "강습 등록에 성공했습니다.");
				return "redirect:/mypage/training";
			} else {
				// 잔액 부족으로 캐시 충전 페이지로 이동
				redirectAttributes.addFlashAttribute("alertMessage", "잔액이 부족합니다.");
				return "redirect:/mypage/cash?pageNo=0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "강습 등록에 실패했습니다.");
			return "redirect:/stadium/stadiumlist";
		}
	}

	// 강습 참가하기 상세조회
	@GetMapping("/training/memberlist/read/{trainingNo}")
	public String getTrainingDetail(@PathVariable int trainingNo, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
	    if (sessions == null || sessions.getAttribute("user") == null) {
			redirectAttributes.addFlashAttribute("alertMessage", "로그인 후 이용 가능합니다.");
	        return "redirect:/login";
	    }
	    
		TrainingDTO training = stadiumReadService.getTrainingDetail(trainingNo);
	    int participantCount = stadiumReadService.getTrainingParticipantCount(trainingNo); // 예약한 인원 수 조회
		model.addAttribute("training", training);
	    model.addAttribute("participantCount", participantCount); // 모델에 인원 수 추가
	    

	    CourtoperatinghoursDTO courtoperatinghours = stadiumReadService.findCourtoperatinghoursByCourtHourNo(training.getCourtHourNo());
	    StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
	    StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());
	    MemberDTO member = stadiumReadService.findMemberByMemberId(training.getMemberId());
	    
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

	    return "thymeleaf/reservation/training_memberlist";
	}
	
	// 강습 참가하기 등록
	@PostMapping(value = "/training/memberlist/insert", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public String trainingMemberlistInsert(HttpSession session, TrainingMemberlistDTO trainingMemberlist, CashDTO cash, RedirectAttributes redirectAttributes) {
		try {
			MemberDTO user = (MemberDTO) session.getAttribute("user");
			String memberId = user.getMemberId();
			TrainingDTO training = stadiumReadService.getTrainingDetail(trainingMemberlist.getTrainingNo());

			// 해당 아이디로 이미 예약된 매칭 정보가 있는지 확인
	        boolean hasExistingTraining = service.existsByMemberIdAndTrainingNo(memberId, trainingMemberlist.getTrainingNo());
	        
	        if (!hasExistingTraining && !memberId.equals(training.getMemberId())) {
				
				CashDTO latestCash = cashService.getLatestCashByMemberId(memberId);
				int totalAmount = latestCash.getTotalAmount();
				
				int trainingFee = training.getTrainingFee();
				System.out.println(trainingFee);
	
				// 잔액 비교
				if (totalAmount >= trainingFee) {
					service.trainingMemberlist_insert(memberId, trainingMemberlist, cash, training);
					
					int ntamount = cash.getTotalAmount(); 
	                MemberDTO member = (MemberDTO)session.getAttribute("user"); 
	                String id = member.getMemberId();
	                MemberDTO upmem =  iservice.update(id,ntamount);
	                session.setAttribute("user", upmem);
					
					redirectAttributes.addFlashAttribute("alertMessage", "강습 신청에 성공했습니다.");
					return "redirect:/mypage/trainingAttend";
				} else {
					redirectAttributes.addFlashAttribute("alertMessage", "잔액이 부족합니다.");
					return "redirect:/mypage/cash?pageNo=0";
				}
	        } else {
	            // 이미 예약된 매칭 정보가 있을 경우에 처리할 내용 추가
				redirectAttributes.addFlashAttribute("alertMessage", "이미 신청한 강습입니다.");
	            return "redirect:/training/traininglist";
	        }
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "강습 신청에 실패했습니다.");
			return "redirect:/training/traininglist";
		}
	}
	
	// 예약 목록보기
	@RequestMapping("/training/traininglist")
	public String traininglist(Model model, @RequestParam(defaultValue = "0") int pageNo) {
		Page<TrainingDTO> traininglistPage = service.traininglist(pageNo);
		List<TrainingDTO> traininglist = traininglistPage.getContent();

		model.addAttribute("traininglist", traininglist);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", traininglistPage.getTotalPages());
		
		// TrainingDTO 목록을 가져오면서 같이 관련된 정보들을 설정합니다.
	    for (TrainingDTO training : traininglist) {
	        // TrainingDTO에 대한 CourtoperatinghoursDTO를 가져옵니다.
	    	CourtoperatinghoursDTO courtoperatinghours = stadiumReadService.findCourtoperatinghoursByCourtHourNo(training.getCourtHourNo());
		    StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
		    StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());

		    model.addAttribute("stadium", stadium);
		    model.addAttribute("stadiumcourt", stadiumcourt);
		    model.addAttribute("courtoperatinghours", courtoperatinghours);
	    }
			
		return "thymeleaf/reservation/traininglist";
	}

	@GetMapping("/trainings/search")
    public String searchTrainings(@RequestParam String type, @RequestParam String keyword, @RequestParam(defaultValue = "0") int pageNo, Model model) {
        int pageSize = 10; // 페이지당 표시할 데이터 수

        Page<TrainingDTO>trainingPage = service.searchTrainings(type, keyword, pageNo, pageSize);
        List<TrainingDTO> traininglist = trainingPage.getContent();

        model.addAttribute("traininglist", traininglist);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", trainingPage.getTotalPages());
        
        // TrainingDTO 목록을 가져오면서 같이 관련된 정보들을 설정합니다.
	    for (TrainingDTO training : traininglist) {
	        // TrainingDTO에 대한 CourtoperatinghoursDTO를 가져옵니다.
	    	CourtoperatinghoursDTO courtoperatinghours = stadiumReadService.findCourtoperatinghoursByCourtHourNo(training.getCourtHourNo());
		    StadiumcourtDTO stadiumcourt = stadiumReadService.findStadiumcourtByCourtNo(courtoperatinghours.getCourtNo());
		    StadiumDTO stadium = stadiumReadService.findStadiumByStadiumNo(stadiumcourt.getStadiumNo().getStadiumNo());

		    model.addAttribute("stadium", stadium);
		    model.addAttribute("stadiumcourt", stadiumcourt);
		    model.addAttribute("courtoperatinghours", courtoperatinghours);
	    }
	    
        return "thymeleaf/reservation/traininglist";
    }
}
