package com.multi.racket.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.AbsentDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.repository.AbsentRepository;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.MemberRepository;
import com.multi.racket.repository.ReservationRepository;
import com.multi.racket.repository.TrainingMemberlistRepository;
import com.multi.racket.repository.TrainingRepository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	MatchingRepository matchingRepository;
	@Autowired
	TrainingRepository trainingRepository;
	@Autowired
	TrainingMemberlistRepository trainingMemberlistRepository; 
	@Autowired
	AbsentRepository absentRepository;

	@Override
	public MemberDTO login(String memberId, String memberPass) {
		// TODO Auto-generated method stub
		return memberRepository.findByMemberIdAndMemberPass(memberId, memberPass);
	}

	@Override
	public MemberDTO info(String memberId) {
		MemberDTO user = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		return user;
	}

	@Override
	public void update(MemberDTO updatedata) {
		MemberDTO user = memberRepository.findById(updatedata.getMemberId()).orElseThrow(() -> new RuntimeException());
		// save메소드 객체를 새로 만들어서 호출되는 경우 insert문이 만들어지고
		// 조회한 객체의 setter메소드를 호출해서 값을 셋팅하고 save를 호출하는 경우 update문이 자동으로 만들어진다.
		user.setMemberNick(updatedata.getMemberNick());
		user.setMemberAge(updatedata.getMemberAge());
		user.setMemberPhone(updatedata.getMemberPhone());
		user.setMemberAddr(updatedata.getMemberAddr());
		memberRepository.save(user);
	}

	@Override
	public MemberDTO idCheck(String memberId) {
		MemberDTO user = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		return user;
	}

	@Override
	public MemberDTO findId(String memberName, String memberEmail) {
		return memberRepository.findByMemberNameAndMemberEmail(memberName, memberEmail);
	}

	@Override
	public void updatePass(MemberDTO updateUser, String memberId, String memberPass) {
		MemberDTO user = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		user.setMemberPass(memberPass);
		memberRepository.save(user);
	}
	
	//예약
	
	@Override
	public List<ReservationDTO> reservationDate(Date reservationDate) {
		List<ReservationDTO> Date = reservationRepository.findByReservationDateAfter(reservationDate);
		return Date;
	}

	@Override
	public Page<ReservationDTO> reservationPage(String memberId, int pageNo) {
		int pageSize = 5;

		Pageable Pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "reservationDate"));
		// ------ -----
		// 몇 번쨰 한 페이지에
		// 페이지 보여줄 레코드 갯수
		return reservationRepository.findAllByMemberId(memberId, Pageable);
	}
	
	//매치 참가
	
	@Override
	public Page<ReservationDTO> matchingPage(String memberId, int pageNo) {
		List<MatchingDTO> matchingDto = matchingRepository.findByMemberId(memberId);
		Page<ReservationDTO> matchingList;
		List<Integer> numberList = new ArrayList<>();
		for(int i = 0; i < matchingDto.size(); i++) {
			int reservationNo = matchingDto.get(i).getReservationNo();
			numberList.add(reservationNo);
		}
		int pageSize = 5;
		Pageable Pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "reservationDate"));
		// ------ -----
		// 몇 번쨰 한 페이지에

		// 페이지 보여줄 레코드 갯수
		matchingList = reservationRepository.findByReservationNoIn(numberList, Pageable);
		return matchingList;
	}
	
	@Override
	public List<MatchingDTO> matchingUser(int reservationNo) {
		return matchingRepository.findAllByReservationNo(reservationNo);
	}
	
	@Override
	public AbsentDTO absent(int matchNo, String memberId) {
		AbsentDTO user = new AbsentDTO();
		user.setMatchNo(matchNo);
		user.setMemberId(memberId);
		absentRepository.save(user);
		return user;
	}
	
	@Override
	public AbsentDTO absentCheck(int matchNo, String memberId) {
		return absentRepository.findByMatchNoAndMemberId(matchNo, memberId);
	}
	
	@Override
	public MatchingDTO cancelMatching(int reservationNo, String memberId) {
		MatchingDTO user = matchingRepository.findByReservationNoAndMemberId(reservationNo, memberId);
		matchingRepository.delete(user);
		return user;
	}
	
	//강습

	@Override
	public List<TrainingDTO> trainingDate(Date trainingDate) {
		List<TrainingDTO> Date = trainingRepository.findByTrainingDateAfter(trainingDate);
		return Date;
	}

	@Override
	public Page<TrainingDTO> trainingPage(String memberId, int pageNo) {
		int pageSize = 5;

		Pageable Pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "trainingDate"));
		// ------ -----
		// 몇 번쨰 한 페이지에
		// 페이지 보여줄 레코드 갯수
		return trainingRepository.findAllByMemberId(memberId, Pageable);
	}
	
	@Override
	public int trainingIncome(String memberId) {
		List<TrainingDTO> trainingDto = trainingRepository.findByMemberId(memberId);
		List<TrainingMemberlistDTO> trainingMemberlistDto = new ArrayList<>();
		int totalIncome = 0;
		int Income = 0;
		for(int i = 0; i < trainingDto.size(); i++) {
			int trainingNo = trainingDto.get(i).getTrainingNo();
			int price = trainingDto.get(i).getTrainingFee();
			trainingMemberlistDto = trainingMemberlistRepository.findByTrainingNo(trainingNo);
			Income = price * trainingMemberlistDto.size();
			totalIncome = totalIncome + Income;
		}
		return totalIncome;
	}
	

	//강습참가
	
	@Override
	public Page<TrainingDTO> trainingAttendPage(String memberId, int pageNo) {
		List<TrainingMemberlistDTO> trainingMemberlistDto = trainingMemberlistRepository.findByMemberId(memberId);
		Page<TrainingDTO> trainingList;
		List<Integer> numberList = new ArrayList<>();
		for(int i = 0; i < trainingMemberlistDto.size(); i++) {
			int reservationNo = trainingMemberlistDto.get(i).getTrainingNo();
			numberList.add(reservationNo);
		}
		int pageSize = 5;
		Pageable Pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "trainingDate"));
		// ------ -----
		// 몇 번쨰 한 페이지에
		// 페이지 보여줄 레코드 갯수
		trainingList = trainingRepository.findByTrainingNoIn(numberList, Pageable);
		return trainingList;
	}

	@Override
	public List<ReservationDTO> reservationDto(String memberId) {
		// TODO Auto-generated method stub
		return reservationRepository.findByMemberId(memberId);
	}

	@Override
	public TrainingMemberlistDTO cancelTraining(int trainingNo, String memberId) {
		System.out.println("DAO에여trainingNo : "  + trainingNo);
		System.out.println("DAO에여memberId : "  + memberId);
		TrainingMemberlistDTO user = trainingMemberlistRepository.findByTrainingNoAndMemberId(trainingNo, memberId);
		System.out.println("DAO에여user : "  + user);
		trainingMemberlistRepository.delete(user);
		return user;
	}

	

	

	

	

}
