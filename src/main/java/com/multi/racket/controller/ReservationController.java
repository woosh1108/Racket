package com.multi.racket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.reservation.ReservationService;

@Controller
public class ReservationController {
	ReservationService service;

	@Autowired
	public ReservationController(ReservationService service) {
		super();
		this.service = service;
	}

	// 구장번호로 예약하기 상세조회
	@GetMapping("/reservation/read")
	public String reservation(int reservationNo) {
		ReservationDTO reservation = service.reservation(reservationNo);
		return "thymeleaf/reservation/reservation";
	}

	// 예약하기 등록
	@RequestMapping("/reservation/insert")
	public String reservationInsert(ReservationDTO reservation) {
		service.reservation_insert(reservation);
		System.out.println("controller : " + reservation);
		return "thymeleaf/reservation/reservation";
	}

	// 예약 참가하기 상세조회
	@GetMapping("/matching/read")
	public String matching(int matchingNo) {
		MatchingDTO matching = service.matching(matchingNo);
		return "thymeleaf/reservation/matching";
	}

	// 예약 참가하기 등록
	@PostMapping("/matching/insert")
	public String matchingInsert(MatchingDTO matching) {
		service.matching_insert(matching);
		return "thymeleaf/reservation/matching";
	}

	// 구장번호로 강습하기 상세조회
	@GetMapping("/training/read")
	public String training(int trainingNo) {
		TrainingDTO training = service.training(trainingNo);
		return "thymeleaf/reservation/training";
	}

	// 강습하기 등록
	@RequestMapping("/training/insert")
	public String trainingInsert(TrainingDTO training) {
		service.training_insert(training);
		System.out.println("controller : " + training);
		return "thymeleaf/reservation/training";
	}

	// 강습 참가하기 상세조회
	@GetMapping("/training/memberlist/read")
	public String trainingMemberlist(int trainingApplyNo) {
		TrainingMemberlistDTO trainingMemberlist = service.trainingMemberlist(trainingApplyNo);
		return "thymeleaf/reservation/training_memberlist";
	}

	// 강습 참가하기 등록
	@PostMapping("/training/memberlist/insert")
	public String trainingMemberlistInsert(TrainingMemberlistDTO trainingMemberlist) {
		service.trainingMemberlist_insert(trainingMemberlist);
		return "thymeleaf/reservation/training_memberlist";
	}

}