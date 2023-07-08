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
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.AbsentDTO;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.repository.AbsentRepository;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.CourtOperatingHoursRepository;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.MemberRepository;
import com.multi.racket.repository.ReservationRepository;
import com.multi.racket.repository.StadiumCourtRepository;
import com.multi.racket.repository.StadiumRepository;
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
	@Autowired
	CourtOperatingHoursRepository courtOperatingHoursRepository;
	@Autowired
	StadiumCourtRepository stadiumCourtRepository;
	@Autowired
	StadiumRepository stadiumRepository;
	@Autowired
	CashRepository cashRepository;
	

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
	
	@Override
	public ReservationDTO cancelReservation(int reservationNo, String memberId, int reservationFee) {
		ReservationDTO reservation = reservationRepository.findByReservationNoAndMemberId(reservationNo, memberId);
		List<MatchingDTO> matchingList = matchingRepository.findAllByReservationNo(reservationNo);
		for(MatchingDTO matchingUser : matchingList) {
			MemberDTO member = memberRepository.findById(matchingUser.getMemberId()).orElseThrow(() -> new RuntimeException());
			CashDTO cash = cashRepository.findByMemberId(matchingUser.getMemberId());
			int totalAmount = member.getTotalAmount();
			int userCash = cash.getTotalAmount();
			member.setTotalAmount(totalAmount + reservationFee);
			cash.setTotalAmount(userCash + reservationFee);
			memberRepository.save(member);
			cashRepository.save(cash);
			matchingRepository.delete(matchingUser);
		}
		reservationRepository.delete(reservation);
		
		return reservation;
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
	public MatchingDTO cancelMatching(int reservationNo, String memberId, int reservationFee) {
		MatchingDTO user = matchingRepository.findByReservationNoAndMemberId(reservationNo, memberId);
		MemberDTO member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		CashDTO cash = cashRepository.findByMemberId(memberId);
		int totalAmount = member.getTotalAmount();
		int cashTotalAmount = cash.getTotalAmount();
		member.setTotalAmount(totalAmount + reservationFee);
		cash.setTotalAmount(cashTotalAmount + reservationFee);
		memberRepository.save(member);
		cashRepository.save(cash);
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
	
	@Override
	public TrainingDTO cancelTraining(int trainingNo, String memberId, int trainingFee, int courtHourNo) {
		TrainingDTO training = trainingRepository.findByTrainingNoAndMemberId(trainingNo,memberId);
		MemberDTO member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		CashDTO cash = cashRepository.findByMemberId(memberId);
		int totalAmount = member.getTotalAmount();
		int cashTotalAmount = cash.getTotalAmount();
		//구장비 찾기
		Optional<CourtoperatinghoursDTO> court = courtOperatingHoursRepository.findById(courtHourNo);
		Optional<StadiumcourtDTO> satdiumCourt = stadiumCourtRepository.findByCourtNo(court.get().getCourtNo()); 
		StadiumDTO stadium = satdiumCourt.get().getStadiumNo();
		//강습 개설할때 사용한 구장금액
		int stadiumUsePrice = stadium.getStadiumFee();
		List<TrainingMemberlistDTO> trainingMemberList = trainingMemberlistRepository.findByTrainingNo(trainingNo);
		for(TrainingMemberlistDTO traingUser : trainingMemberList) {
			MemberDTO user = memberRepository.findById(traingUser.getMemberId()).orElseThrow(() -> new RuntimeException());
			CashDTO userCash = cashRepository.findByMemberId(traingUser.getMemberId());
			int amount = user.getTotalAmount();
			int UserTotalAmount = userCash.getTotalAmount();
			user.setTotalAmount(amount + trainingFee);
			userCash.setTotalAmount(UserTotalAmount + trainingFee);
			memberRepository.save(user);
			cashRepository.save(userCash);
			trainingMemberlistRepository.delete(traingUser);
		}
		member.setTotalAmount(totalAmount + stadiumUsePrice);
		cash.setTotalAmount(cashTotalAmount+stadiumUsePrice);
		memberRepository.save(member);
		cashRepository.save(cash);
		trainingRepository.delete(training);
		return training;
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
	public List<MemberDTO> getAllMembers() {
		 return memberRepository.findAll();
	}

	@Override
	public TrainingMemberlistDTO cancelTrainingAttend(int trainingNo, String memberId, int trainingFee) {
		TrainingMemberlistDTO user = trainingMemberlistRepository.findByTrainingNoAndMemberId(trainingNo, memberId);
		MemberDTO member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		CashDTO cash = cashRepository.findByMemberId(memberId);
		int totalAmount = member.getTotalAmount();
		int cashTotalAmount = cash.getTotalAmount();
		member.setTotalAmount(totalAmount + trainingFee);
		cash.setTotalAmount(cashTotalAmount + trainingFee);
		memberRepository.save(member);
		cashRepository.save(cash);
		trainingMemberlistRepository.delete(user);
		return user;
	}

}
