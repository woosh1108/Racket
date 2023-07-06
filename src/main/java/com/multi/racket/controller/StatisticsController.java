package com.multi.racket.controller;


import java.util.List;

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
	public StatisticsController(MemberService memberService, ReservationService reservationService, TrainingService trainingService) {
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

}
