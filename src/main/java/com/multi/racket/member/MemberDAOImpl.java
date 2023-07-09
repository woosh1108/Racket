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
		return reservationRepository.findAllByMemberId(memberId, Pageable);
	}
	
	@Override
	public ReservationDTO cancelReservation(int reservationNo, String memberId, int reservationFee) {
		ReservationDTO reservation = reservationRepository.findByReservationNoAndMemberId(reservationNo, memberId);
		List<MatchingDTO> matchingList = matchingRepository.findAllByReservationNo(reservationNo);
		for(MatchingDTO matchingUser : matchingList) {
			MemberDTO reservationUser = memberRepository.findById(matchingUser.getMemberId()).orElseThrow(() -> new RuntimeException());
			int totalAmount = reservationUser.getTotalAmount();
			reservationUser.setTotalAmount(totalAmount + reservationFee);
			CashDTO userCash = cashRepository.findFirstByMemberIdOrderByCashDateDesc(matchingUser.getMemberId());
			int totalCash = userCash.getTotalAmount();
			CashDTO myCash = new CashDTO(matchingUser.getMemberId(), 1, totalCash + reservationFee , 0 , reservationFee);
			memberRepository.save(reservationUser);
			cashRepository.save(myCash);
			matchingRepository.delete(matchingUser);
		}
		reservationRepository.delete(reservation);
		
		return reservation;
	}
	
	@Override
	public int reservationCourtNo(int reservationNo) {
		Optional<ReservationDTO> reservation = reservationRepository.findByReservationNo(reservationNo);
		Optional<CourtoperatinghoursDTO> court = courtOperatingHoursRepository.findByCourtHourNo(reservation.get().getCourtHourNo());
		int courtNo = court.get().getCourtNo();
		return courtNo;
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
		int totalAmount = member.getTotalAmount();
		member.setTotalAmount(totalAmount + reservationFee);
		CashDTO cash = cashRepository.findFirstByMemberIdOrderByCashDateDesc(memberId);
		int userCash = cash.getTotalAmount();
		CashDTO myCash = new CashDTO(memberId, 1, userCash + reservationFee , 0 , reservationFee);
		Optional<ReservationDTO> reservation = reservationRepository.findByReservationNo(reservationNo);
		reservation.get().setReservationStatus("매칭중");
		memberRepository.save(member);
		cashRepository.save(myCash);
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
		MemberDTO trainer = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		CashDTO cash = cashRepository.findFirstByMemberIdOrderByCashDateDesc(memberId);
		//구장비 찾기
		Optional<CourtoperatinghoursDTO> court = courtOperatingHoursRepository.findById(courtHourNo);
		Optional<StadiumcourtDTO> satdiumCourt = stadiumCourtRepository.findByCourtNo(court.get().getCourtNo()); 
		StadiumDTO stadium = satdiumCourt.get().getStadiumNo();
		//강습 개설할때 사용한 구장금액
		int stadiumUsePrice = stadium.getStadiumFee() * 2;
		List<TrainingMemberlistDTO> trainingMemberList = trainingMemberlistRepository.findByTrainingNo(trainingNo);
		for(TrainingMemberlistDTO traingUser : trainingMemberList) {
			MemberDTO attendUser = memberRepository.findById(traingUser.getMemberId()).orElseThrow(() -> new RuntimeException());
			int attendAmount = attendUser.getTotalAmount();
			attendUser.setTotalAmount(attendAmount + trainingFee);
			CashDTO attendCash = cashRepository.findFirstByMemberIdOrderByCashDateDesc(traingUser.getMemberId());
			int UserTotalAmount = attendCash.getTotalAmount();
			CashDTO attendUserCash = new CashDTO(attendUser.getMemberId(), 1, UserTotalAmount + trainingFee , 0 , trainingFee);
			memberRepository.save(attendUser);
			cashRepository.save(attendUserCash);
			trainingMemberlistRepository.delete(traingUser);
		}
		int totalAmount = trainer.getTotalAmount();
		trainer.setTotalAmount(totalAmount + stadiumUsePrice);
		int trainerUserCsh = cash.getTotalAmount();
		CashDTO trainerCash = new CashDTO(memberId, 1, trainerUserCsh + stadiumUsePrice , 0 , stadiumUsePrice);
		memberRepository.save(trainer);
		cashRepository.save(trainerCash);
		trainingRepository.delete(training);
		return training;
	}
	
	@Override
	public int trainingCourtNo(int trainingNo) {
		Optional<TrainingDTO> training = trainingRepository.findByTrainingNo(trainingNo);
		Optional<CourtoperatinghoursDTO> court = courtOperatingHoursRepository.findByCourtHourNo(training.get().getCourtHourNo());
		int courtNo = court.get().getCourtNo();
		return courtNo;
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
		trainingList = trainingRepository.findByTrainingNoIn(numberList, Pageable);
		return trainingList;
	}

	@Override
	public List<ReservationDTO> reservationDto(String memberId) {
		return reservationRepository.findByMemberId(memberId);
	}

	@Override
	public List<MemberDTO> getAllMembers() {
		 return memberRepository.findAll();
	}

	@Override
	public TrainingMemberlistDTO cancelTrainingAttend(int trainingNo, String memberId, int trainingFee) {
		TrainingMemberlistDTO user = trainingMemberlistRepository.findByTrainingNoAndMemberId(trainingNo, memberId);
		MemberDTO attendUser = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
		int totalAmount = attendUser.getTotalAmount();
		attendUser.setTotalAmount(totalAmount + trainingFee);
		CashDTO attendUserCash = cashRepository.findFirstByMemberIdOrderByCashDateDesc(memberId);
		int cashTotalAmount = attendUserCash.getTotalAmount();
		CashDTO myCash = new CashDTO(memberId, 1, cashTotalAmount + trainingFee , 0 , trainingFee);
		Optional<TrainingDTO> training = trainingRepository.findByTrainingNo(trainingNo);
		training.get().setTrainingStatus("모집중");
		memberRepository.save(attendUser);
		cashRepository.save(myCash);
		trainingMemberlistRepository.delete(user);
		return user;
	}

}