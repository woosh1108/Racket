package com.multi.racket.controller;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.racket.domain.AbsentDTO;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.inquiry.InquiryPageService;
import com.multi.racket.inquiry.PageDTO;
import com.multi.racket.member.MemberService;
import com.multi.racket.reservation.StadiumReadService;

@Controller
@RequestMapping("/mypage") // 공유메핑명
@SessionAttributes("user") // 데이터공유명
public class mypageController {
	@Autowired
	MemberService service;
	@Autowired
	StadiumReadService stadiumReadService;
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
		if (updateInfo != null) {
			model.addAttribute("msg", "정보변경이 완료되었습니다.");
		}
		return "redirect:/main";
	}
	
	//비밀번호 변경페이지
	@RequestMapping("/passChange")
	public String Pass() {
		return "thymeleaf/mypage/myPassChange";
	}
	
	//비밀번호 변경하기
	
	@RequestMapping("/passChange.do")
	public String changePass(MemberDTO updateUser, HttpSession session,
			Model model, String memberPass, String memberPass1, String memberPass2) {
		MemberDTO user = (MemberDTO) session.getAttribute("user");
        String memberId = user.getMemberId();
		updateUser = user;
		if(memberPass1.equals(memberPass2)) {
			memberPass = memberPass2;
			service.updatePass(updateUser,memberId, memberPass);
			model.addAttribute("change", "비밀번호 변경이 완료되었습니다.");
			return "thymeleaf/main/mainpage";
		}else {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다. 다시한번 확인해주세요.");
			return "thymeleaf/login/login_findPass2";
		}
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

        //원준코드추가
        MemberDTO user = service.idCheck(id);
        model.addAttribute("totalAmount", user.getTotalAmount());

        model.addAttribute("mycash", list);
        model.addAttribute("totalPageNumber", totalPageNumber);
        model.addAttribute("member", member);
        return "thymeleaf/mypage/myCash";
    }

	// 내 예약 내역보기
	@RequestMapping("/reservation")
	public String myReservation(String memberId, Date reservationDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo,HttpSession session) {
		// 현재 날짜 가져오기
		LocalDate today = LocalDate.now();
		Date currentDate = Date.valueOf(today);
		// 3일 후 짜 계산하기
		LocalDate threeDaysAgoLocalDate = today.plusDays(2);
		Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
		reservationDate = threeDaysAgoDate;

		List<ReservationDTO> date = service.reservationDate(reservationDate);
		Page<ReservationDTO> reservationPage = service.reservationPage(memberId, pageNo);
		List<ReservationDTO> reservationUser = reservationPage.getContent();

		// 참여인원
		List<Integer> peopleList = new ArrayList<>();
		for (ReservationDTO reservation : reservationUser) {
			int people = stadiumReadService.getReservationParticipantCount(reservation.getReservationNo());
			peopleList.add(people);
		}
		model.addAttribute("people", peopleList);

		if (reservationUser.size() != 0) {
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", reservationPage.getTotalPages());
			model.addAttribute("reservationUser", reservationUser);

		} else {
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", reservationPage.getTotalPages());
			model.addAttribute("reservationUser", reservationUser);
		}

		// 코트시간
		List<String> startHour = new ArrayList<>();
		List<String> endHour = new ArrayList<>();
		List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
		for (ReservationDTO reservation : reservationUser) {
			CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
					.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
			hourList.add(courtoperatinghoursDto);
		}
		for (CourtoperatinghoursDTO hour : hourList) {
			startHour.add(hour.getCourtStart());
			endHour.add(hour.getCourtEnd());
		}
		model.addAttribute("startHour", startHour);
		model.addAttribute("endHour", endHour);
		
		return "thymeleaf/mypage/myReservation";
	}

	// 내예약보기 - 취소기능
	@RequestMapping("/reservation/cancel")
	public String cancelReservation(int reservationNo, String memberId, Date reservationDate, Model model,
			int reservationFee, @RequestParam(defaultValue = "0") int pageNo, RedirectAttributes redirectAttributes,HttpSession session) {
		try {

			ReservationDTO cacelUser = service.cancelReservation(reservationNo, memberId, reservationFee);
			if (cacelUser != null) {
				model.addAttribute("cancelMsg", "구장예약취소 및 환불처리가 완료되었습니다.");
			}
			// 현재 날짜 가져오기
			LocalDate today = LocalDate.now();
			Date currentDate = Date.valueOf(today);
			// 3일 후 짜 계산하기
			LocalDate threeDaysAgoLocalDate = today.plusDays(2);
			Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
			reservationDate = threeDaysAgoDate;

			List<ReservationDTO> date = service.reservationDate(reservationDate);
			Page<ReservationDTO> reservationPage = service.reservationPage(memberId, pageNo);
			List<ReservationDTO> reservationUser = reservationPage.getContent();

			// 참여인원
			List<Integer> peopleList = new ArrayList<>();
			for (ReservationDTO reservation : reservationUser) {
				int people = stadiumReadService.getReservationParticipantCount(reservation.getReservationNo());
				peopleList.add(people);
			}
			model.addAttribute("people", peopleList);

			if (reservationUser.size() != 0) {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", reservationPage.getTotalPages());
				model.addAttribute("reservationUser", reservationUser);

			} else {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", reservationPage.getTotalPages());
				model.addAttribute("reservationUser", reservationUser);
			}

			// 코트시간
			List<String> startHour = new ArrayList<>();
			List<String> endHour = new ArrayList<>();
			List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
			for (ReservationDTO reservation : reservationUser) {
				CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
						.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
				hourList.add(courtoperatinghoursDto);
			}
			for (CourtoperatinghoursDTO hour : hourList) {
				startHour.add(hour.getCourtStart());
				endHour.add(hour.getCourtEnd());
			}
			model.addAttribute("startHour", startHour);
			model.addAttribute("endHour", endHour);
			MemberDTO member = (MemberDTO) session.getAttribute("user");
//			System.out.println("-----------------------------------------------------------");
//			System.out.println(member.getTotalAmount());
//			System.out.println("-----------------------------------------------------------");
			return "thymeleaf/mypage/myReservation";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "오류로 인해서 작업이 중단되었습니다.");
			return "redirect:/mypage/reservation";
		}
	}

	// 내 매치보기
	@RequestMapping("/match")
	public String myMatch(String memberId, Date reservationDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
		LocalDate today = LocalDate.now();
		Date currentDate = Date.valueOf(today);

		// 3일 후 짜 계산하기
		LocalDate threeDaysAgoLocalDate = today.plusDays(2);
		Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
		reservationDate = threeDaysAgoDate;

		List<ReservationDTO> date = service.reservationDate(reservationDate);
		Page<ReservationDTO> reservationPage = service.matchingPage(memberId, pageNo);
		List<ReservationDTO> reservationList = reservationPage.getContent();

		// 참여인원
		List<Integer> peopleList = new ArrayList<>();
		for (ReservationDTO reservation : reservationList) {
			int people = stadiumReadService.getReservationParticipantCount(reservation.getReservationNo());
			peopleList.add(people);
		}
		model.addAttribute("people", peopleList);

		// 코트시간
		List<String> startHour = new ArrayList<>();
		List<String> endHour = new ArrayList<>();
		List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
		for (ReservationDTO reservation : reservationList) {
			CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
					.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
			hourList.add(courtoperatinghoursDto);
		}
		for (CourtoperatinghoursDTO hour : hourList) {
			startHour.add(hour.getCourtStart());
			endHour.add(hour.getCourtEnd());
		}
		model.addAttribute("startHour", startHour);
		model.addAttribute("endHour", endHour);

		if (reservationList.size() != 0) {
			model.addAttribute("reservationList", reservationList);
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", reservationPage.getTotalPages());
		} else {
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", reservationPage.getTotalPages());
		}
		return "thymeleaf/mypage/myMatch";
	}

	// 내매치보기 - 취소기능
	@RequestMapping("/match/cancel")
	public String cancelMatching(int reservationNo, String memberId, Date reservationDate, Model model,
			int reservationFee, @RequestParam(defaultValue = "0") int pageNo, RedirectAttributes redirectAttributes) {
		try {
			MatchingDTO cacelUser = service.cancelMatching(reservationNo, memberId, reservationFee);
			if (cacelUser != null) {
				model.addAttribute("cancelMsg", "매칭참가 취소 및 환불처리가 완료되었습니다.");
			}

			LocalDate today = LocalDate.now();
			Date currentDate = Date.valueOf(today);

			// 3일 후 짜 계산하기
			LocalDate threeDaysAgoLocalDate = today.plusDays(2);
			Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
			reservationDate = threeDaysAgoDate;

			List<ReservationDTO> date = service.reservationDate(reservationDate);
			Page<ReservationDTO> reservationPage = service.matchingPage(memberId, pageNo);
			List<ReservationDTO> reservationList = reservationPage.getContent();

			// 참여인원
			List<Integer> peopleList = new ArrayList<>();
			for (ReservationDTO reservation : reservationList) {
				int people = stadiumReadService.getReservationParticipantCount(reservation.getReservationNo());
				peopleList.add(people);
			}
			model.addAttribute("people", peopleList);

			// 코트시간
			List<String> startHour = new ArrayList<>();
			List<String> endHour = new ArrayList<>();
			List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
			for (ReservationDTO reservation : reservationList) {
				CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
						.findCourtoperatinghoursByCourtHourNo(reservation.getCourtHourNo());
				hourList.add(courtoperatinghoursDto);
			}
			for (CourtoperatinghoursDTO hour : hourList) {
				startHour.add(hour.getCourtStart());
				endHour.add(hour.getCourtEnd());
			}
			model.addAttribute("startHour", startHour);
			model.addAttribute("endHour", endHour);

			if (reservationList.size() != 0) {
				model.addAttribute("reservationList", reservationList);
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", reservationPage.getTotalPages());
			} else {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", reservationPage.getTotalPages());
			}

			return "thymeleaf/mypage/myMatch";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "오류로 인해서 작업이 중단되었습니다.");
			return "redirect:/mypage/match";
		}

	}

	// 내매치보기 - 신고기능
	@RequestMapping("/match/declaration")
	public String declaration(String memberId, int reservationNo, Model model) {
		List<MatchingDTO> matchingList = service.matchingUser(reservationNo);
		model.addAttribute("matchingList", matchingList);
		model.addAttribute("reservationNo", reservationNo);
		return "thymeleaf/mypage/myMatchDeclaration";
	}

	@RequestMapping("/match/absent")
	public String absent(@RequestParam("matchNo") int matchNo, @RequestParam("memberId") String memberId,
			int reservationNo, RedirectAttributes redirectAttributes) {
		AbsentDTO alreadyReported = service.absentCheck(matchNo, memberId);
		if (alreadyReported != null) {
			redirectAttributes.addFlashAttribute("absentMsg", "이미 신고한 사용자입니다.");
		} else {
			AbsentDTO absentUser = service.absent(matchNo, memberId);
			redirectAttributes.addFlashAttribute("absentMsg", absentUser.getMemberId() + "님 신고가 완료되었습니다.");
		}
		return "redirect:/mypage/match/declaration?reservationNo=" + reservationNo;
	}

	// 강습

	// 내 강의 내역보기
	@RequestMapping("/training")
	public String myTraining(String memberId, Date trainingDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
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

		// 총 강습비 구하기
		int Income = service.trainingIncome(memberId);
		DecimalFormat formmatter = new DecimalFormat("###,###");
		String totalIncome = formmatter.format(Income);

		// 참여인원
		List<Integer> peopleList = new ArrayList<>();
		for (TrainingDTO trainingDto : training) {
			int people = stadiumReadService.getTrainingParticipantCount(trainingDto.getTrainingNo());
			peopleList.add(people);
		}
		model.addAttribute("people", peopleList);

		// 코트시간
		List<String> startHour = new ArrayList<>();
		List<String> endHour = new ArrayList<>();
		List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
		for (TrainingDTO TrainingDto : training) {
			CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
					.findCourtoperatinghoursByCourtHourNo(TrainingDto.getCourtHourNo());
			hourList.add(courtoperatinghoursDto);
		}
		for (CourtoperatinghoursDTO hour : hourList) {
			startHour.add(hour.getCourtStart());
			endHour.add(hour.getCourtEnd());
		}
		model.addAttribute("startHour", startHour);
		model.addAttribute("endHour", endHour);

		if (training.size() != 0) {
			model.addAttribute("training", training);
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", trainingPage.getTotalPages());
			model.addAttribute("totalIncome", totalIncome);
		} else {
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", trainingPage.getTotalPages());
			model.addAttribute("totalIncome", totalIncome);
		}

		return "thymeleaf/mypage/myTraining";
	}

	// 내강습보기 - 취소기능
	@RequestMapping("/training/cancel")
	public String cancelTraining(int trainingNo, String memberId, Date trainingDate, Model model, int trainingFee,
			int courtHourNo, @RequestParam(defaultValue = "0") int pageNo, RedirectAttributes redirectAttributes,HttpSession session) {
		try {
			TrainingDTO cacelUser = service.cancelTraining(trainingNo, memberId, trainingFee, courtHourNo);
			if (cacelUser != null) {
				model.addAttribute("cancelMsg", "해당강습 취소 및 환불처리가 완료되었습니다.");
			}

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

			// 총 강습비 구하기
			int Income = service.trainingIncome(memberId);
			DecimalFormat formmatter = new DecimalFormat("###,###");
			String totalIncome = formmatter.format(Income);

			// 참여인원
			List<Integer> peopleList = new ArrayList<>();
			for (TrainingDTO trainingDto : training) {
				int people = stadiumReadService.getTrainingParticipantCount(trainingDto.getTrainingNo());
				peopleList.add(people);
			}
			model.addAttribute("people", peopleList);

			// 코트시간
			List<String> startHour = new ArrayList<>();
			List<String> endHour = new ArrayList<>();
			List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
			for (TrainingDTO TrainingDto : training) {
				CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
						.findCourtoperatinghoursByCourtHourNo(TrainingDto.getCourtHourNo());
				hourList.add(courtoperatinghoursDto);
			}
			for (CourtoperatinghoursDTO hour : hourList) {
				startHour.add(hour.getCourtStart());
				endHour.add(hour.getCourtEnd());
			}
			model.addAttribute("startHour", startHour);
			model.addAttribute("endHour", endHour);

			if (training.size() != 0) {
				model.addAttribute("training", training);
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", trainingPage.getTotalPages());
				model.addAttribute("totalIncome", totalIncome);
			} else {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", trainingPage.getTotalPages());
				model.addAttribute("totalIncome", totalIncome);
			}
			MemberDTO member = (MemberDTO) session.getAttribute("user");
			System.out.println(member.getTotalAmount());
			return "thymeleaf/mypage/myTraining";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "오류로 인해서 작업이 중단되었습니다.");
			return "redirect:/mypage/training";
		}
	}

	// 강습참가
	@RequestMapping("/trainingAttend")
	public String myTrainingAttend(String memberId, Date trainingDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
		LocalDate today = LocalDate.now();
		Date currentDate = Date.valueOf(today);

		// 3일 후 짜 계산하기
		LocalDate threeDaysAgoLocalDate = today.plusDays(2);
		Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
		trainingDate = threeDaysAgoDate;

		List<TrainingDTO> date = service.trainingDate(trainingDate);
		Page<TrainingDTO> traingListPage = service.trainingAttendPage(memberId, pageNo);
		List<TrainingDTO> trainingList = traingListPage.getContent();

		// 참여인원
		List<Integer> peopleList = new ArrayList<>();
		for (TrainingDTO trainingDto : trainingList) {
			int people = stadiumReadService.getTrainingParticipantCount(trainingDto.getTrainingNo());
			peopleList.add(people);
		}
		model.addAttribute("people", peopleList);

		// 코트시간
		List<String> startHour = new ArrayList<>();
		List<String> endHour = new ArrayList<>();
		List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
		for (TrainingDTO TrainingDto : trainingList) {
			CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
					.findCourtoperatinghoursByCourtHourNo(TrainingDto.getCourtHourNo());
			hourList.add(courtoperatinghoursDto);
		}
		for (CourtoperatinghoursDTO hour : hourList) {
			startHour.add(hour.getCourtStart());
			endHour.add(hour.getCourtEnd());
		}
		model.addAttribute("startHour", startHour);
		model.addAttribute("endHour", endHour);

		if (trainingList.size() != 0) {
			model.addAttribute("trainingList", trainingList);
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", traingListPage.getTotalPages());
		} else {
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", traingListPage.getTotalPages());
		}
		return "thymeleaf/mypage/myTrainingAttend";
	}

	// 강습참가 보기 - 취소기능
	@RequestMapping("/trainingAttend/cancel")
	public String cancelTrainingAttend(int trainingNo, String memberId, Date trainingDate, Model model, int trainingFee,
			@RequestParam(defaultValue = "0") int pageNo, RedirectAttributes redirectAttributes,HttpSession session) {
		try {
			TrainingMemberlistDTO cacelUser = service.cancelTrainingAttend(trainingNo, memberId, trainingFee);
			if (cacelUser != null) {
				model.addAttribute("cancelMsg", "강습참가 취소 및 환불처리가 완료되었습니다.");
			}

			LocalDate today = LocalDate.now();
			Date currentDate = Date.valueOf(today);

			// 3일 후 짜 계산하기
			LocalDate threeDaysAgoLocalDate = today.plusDays(2);
			Date threeDaysAgoDate = Date.valueOf(threeDaysAgoLocalDate);
			trainingDate = threeDaysAgoDate;

			List<TrainingDTO> date = service.trainingDate(trainingDate);
			Page<TrainingDTO> traingListPage = service.trainingAttendPage(memberId, pageNo);
			List<TrainingDTO> trainingList = traingListPage.getContent();

			// 참여인원
			List<Integer> peopleList = new ArrayList<>();
			for (TrainingDTO trainingDto : trainingList) {
				int people = stadiumReadService.getTrainingParticipantCount(trainingDto.getTrainingNo());
				peopleList.add(people);
			}
			model.addAttribute("people", peopleList);

			// 코트시간
			List<String> startHour = new ArrayList<>();
			List<String> endHour = new ArrayList<>();
			List<CourtoperatinghoursDTO> hourList = new ArrayList<>();
			for (TrainingDTO TrainingDto : trainingList) {
				CourtoperatinghoursDTO courtoperatinghoursDto = stadiumReadService
						.findCourtoperatinghoursByCourtHourNo(TrainingDto.getCourtHourNo());
				hourList.add(courtoperatinghoursDto);
			}
			for (CourtoperatinghoursDTO hour : hourList) {
				startHour.add(hour.getCourtStart());
				endHour.add(hour.getCourtEnd());
			}
			model.addAttribute("startHour", startHour);
			model.addAttribute("endHour", endHour);

			if (trainingList.size() != 0) {
				model.addAttribute("trainingList", trainingList);
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", traingListPage.getTotalPages());
			} else {
				model.addAttribute("date", date);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", traingListPage.getTotalPages());
			}
			MemberDTO member = (MemberDTO) session.getAttribute("user");
			System.out.println(member.getTotalAmount());
			return "thymeleaf/mypage/myTrainingAttend";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "오류로 인해서 작업이 중단되었습니다.");
			return "redirect:/mypage/trainingAttend";
		}
	}
}
