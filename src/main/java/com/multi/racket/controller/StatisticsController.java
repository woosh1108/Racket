package com.multi.racket.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.member.MemberService;
import com.multi.racket.reservation.ReservationService;
import com.multi.racket.training.TrainingService;

@Controller
public class StatisticsController {
	private MemberService memberService;
	private ReservationService reservationService;
	private TrainingService trainingService;

	@Autowired
	public StatisticsController(MemberService memberService, ReservationService reservationService,
			TrainingService trainingService) {
		super();
		this.memberService = memberService;
		this.reservationService = reservationService;
		this.trainingService = trainingService;
	}

	@GetMapping("/statistics")
	public String showView(Model model) {
		return "thymeleaf/statistics/statistics";
	}

	// 멤버정보 가져오기
	@GetMapping("/statistics/members")
	@ResponseBody
	public ResponseEntity<List<MemberDTO>> getMemberData() {
		List<MemberDTO> memberList = memberService.getAllMembers();
		return ResponseEntity.ok(memberList);
	}

	// 예약정보 가져오기
	@GetMapping("/statistics/reservations")
	@ResponseBody
	public ResponseEntity<List<ReservationDTO>> getReservationData() {
		List<ReservationDTO> reservationList = reservationService.getAllReservationInfo();
		return ResponseEntity.ok(reservationList);
	}

	// 강습 현황 가져오기
	@GetMapping("/statistics/training")
	@ResponseBody
	public ResponseEntity<List<TrainingMemberlistDTO>> getTrainingData() {
		List<TrainingMemberlistDTO> trainingList = trainingService.getAllTrainingMembers();
		return ResponseEntity.ok(trainingList);
	}

	// 멤버쪽 년도 데이터 받아오기
	@GetMapping("/statistics/membersInfo_years")
	@ResponseBody
	public ResponseEntity<Map<String, List<String>>> getYearsDataMember() {
	    List<MemberDTO> members = memberService.getAllMembers();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	    List<String> years = members.stream()
	            .map(member -> dateFormat.format(member.getMemberReg()))
	            .distinct()
	            .sorted()
	            .collect(Collectors.toCollection(ArrayList::new));
	    Map<String, List<String>> response = new HashMap<>();
	    response.put("years", years);

	    return ResponseEntity.ok(response);
	}
	
	// 예약쪽 년도 데이터 받아오기
	@GetMapping("/statistics/reservationsInfo_years")
	@ResponseBody
	public ResponseEntity<Map<String, List<String>>> getYearsDataReservation() {
		List<ReservationDTO> reservations = reservationService.getAllReservationInfo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		List<String> years = reservations.stream()
				.map(reservation -> dateFormat.format(reservation.getReservationDate()))
				.distinct()
				.sorted()
				.collect(Collectors.toCollection(ArrayList::new));
		Map<String, List<String>> response = new HashMap<>();
		response.put("yearsReservation", years);

		return ResponseEntity.ok(response);
	}
	
	// 트레이닝쪽 년도 데이터 받아오기
	@GetMapping("/statistics/trainingInfo_years")
	@ResponseBody
	public ResponseEntity<Map<String, List<String>>> getYearsDataTraining() {
	    List<TrainingMemberlistDTO> trainingList = trainingService.getAllTrainingMembers();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	    List<String> years = trainingList.stream()
	            .map(training -> dateFormat.format(training.getTrainingDate()))
	            .distinct()
	            .sorted()
	            .collect(Collectors.toCollection(ArrayList::new));
	    Map<String, List<String>> response = new HashMap<>();
	    response.put("yearsTraining", years);

	    return ResponseEntity.ok(response);
	}
}
