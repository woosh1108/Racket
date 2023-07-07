package com.multi.racket.member;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.repository.MemberRepository;
import com.multi.racket.repository.ReservationRepository;
import com.multi.racket.repository.TrainingRepository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	TrainingRepository trainingRepository;

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

	@Override
	public List<ReservationDTO> reservationId(String memberId) {
		List<ReservationDTO> reservationUser = reservationRepository.findByMemberId(memberId);
		return reservationUser;
	}
	
	@Override
	public List<ReservationDTO> reservationDate(Date reservationDate) {
		List<ReservationDTO> Date = reservationRepository.findByReservationDateAfter(reservationDate);
		return Date;
	}
	
	@Override
	public Page<ReservationDTO> reservationPage(String memberId, int pageNo) {
		int pageSize = 5;
		
		Pageable Pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "reservationDate"));
		//                                       ------  -----
		//                                       몇 번쨰      한 페이지에
		//                                       페이지       보여줄 레코드 갯수
        return reservationRepository.findAllByMemberId(memberId, Pageable);
	}
	
	@Override
	public List<TrainingDTO> training(String memberId) {
		List<TrainingDTO> training = trainingRepository.findByMemberId(memberId);
		return training;
	}

	@Override
	public List<TrainingDTO> trainingDate(Date trainingDate) {
		List<TrainingDTO> Date = trainingRepository.findByTrainingDateAfter(trainingDate);
		return Date;
	}

	@Override
	public Page<TrainingDTO> trainingPage(String memberId, int pageNo) {
		int pageSize = 5;
		
		Pageable Pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "trainingDate"));
		//                                       ------  -----
		//                                       몇 번쨰      한 페이지에
		//                                       페이지       보여줄 레코드 갯수
        return trainingRepository.findAllByMemberId(memberId, Pageable);
	}

	@Override
	public List<MemberDTO> getAllMembers() {
		 return memberRepository.findAll();
	}

	

	
}
