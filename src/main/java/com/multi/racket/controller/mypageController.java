package com.multi.racket.controller;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
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

	@Autowired
	public mypageController(MemberService service) {
		super();
		this.service = service;
	}

	// 내정보보기페이지 - 수정x
	@RequestMapping("/info")
	public String myInfo(String memberId, Model model) {
		MemberDTO user = service.info(memberId);
		model.addAttribute("user", user);
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

		return "thymeleaf/main/mainpage";
	}

	// 내 캐쉬내역보기 - 충전가능
	@RequestMapping("/cash")
	public String myCash() {
		return "thymeleaf/mypage/myCash";
	}

	// 내 예약 내역보기
	@RequestMapping("/reservation")
	public String myReservation(String memberId, Date reservationDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
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
			model.addAttribute("msg", "나의 예약 기록이 없습니다.");
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
	
	
	

	// 내 매치보기
	@RequestMapping("/match")
	public String myMatch(String memberId, Date reservationDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
		System.out.println("리다이렉트memberId : " + memberId);
		System.out.println("리다이렉트reservationDate : " + reservationDate);
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
			model.addAttribute("msg", "나의 매치 참가 기록이 없습니다.");
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", reservationPage.getTotalPages());
		}
		return "thymeleaf/mypage/myMatch";
	}

	// 내매치보기 - 취소기능
	@RequestMapping("/match/cancel")
	public String cancelMatching(int reservationNo, String memberId, Date reservationDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
		
		MatchingDTO cacelUser = service.cancelMatching(reservationNo, memberId);
		if (cacelUser != null) {
			model.addAttribute("cancelMsg", "매칭참가 취소가 완료되었습니다.");
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
			model.addAttribute("msg", "나의 매치 참가 기록이 없습니다.");
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", reservationPage.getTotalPages());
		}
		
		return "thymeleaf/mypage/myMatch";
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
			model.addAttribute("msg", "나의 강습 기록이 없습니다.");
			model.addAttribute("totalIncome", totalIncome);
		}

		return "thymeleaf/mypage/myTraining";
	}
	
	//강습참가
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
			model.addAttribute("msg", "나의 매치 참가 기록이 없습니다.");
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", traingListPage.getTotalPages());
		}

		return "thymeleaf/mypage/myTrainingAttend";
	}
	// 내매치보기 - 취소기능
	@RequestMapping("/training/cancel")
	public String cancelTraining(int trainingNo, String memberId, Date trainingDate, Model model,
			@RequestParam(defaultValue = "0") int pageNo) {
		System.out.println("컨트롤러 trainingNo : " + trainingNo);
		System.out.println("컨트롤러  memberId: " +  memberId);
		TrainingMemberlistDTO cacelUser = service.cancelTraining(trainingNo, memberId);
		System.out.println("컨트롤러 cacelUser: " + cacelUser);
		if (cacelUser != null) {
			model.addAttribute("cancelMsg", "강습 참가 취소가 완료되었습니다.");
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
			model.addAttribute("msg", "나의 매치 참가 기록이 없습니다.");
			model.addAttribute("date", date);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", traingListPage.getTotalPages());
		}
		return "thymeleaf/mypage/myTrainingAttend";
	}	
}
