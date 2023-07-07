package com.multi.racket.member;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.AbsentDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;

	public MemberServiceImpl() {

	}

	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public MemberDTO info(String memberId) {
		// TODO Auto-generated method stub
		return dao.info(memberId);
	}

	@Override
	public MemberDTO login(String memberId, String memberPass) {
		// TODO Auto-generated method stub
		return dao.login(memberId, memberPass);
	}

	@Override
	public void update(MemberDTO updatedata) {
		dao.update(updatedata);
	}

	@Override
	public MemberDTO idCheck(String memberId) {
		// TODO Auto-generated method stub
		return dao.idCheck(memberId);
	}

	@Override
	public MemberDTO findId(String memberName, String memberEmail) {
		// TODO Auto-generated method stub
		return dao.findId(memberName, memberEmail);
	}

	@Override
	public void updatePass(MemberDTO updateUser, String memberId, String memberPass) {
		// TODO Auto-generated method stub
		dao.updatePass(updateUser, memberId, memberPass);
	}
	
	//예약
	
	
	@Override
	public List<ReservationDTO> reservationDate(Date reservationDate) {
		// TODO Auto-generated method stub
		return dao.reservationDate(reservationDate);
	}
	
	@Override
	public Page<ReservationDTO> reservationPage(String memberId,int pageNo) {
		// TODO Auto-generated method stub
		return dao.reservationPage(memberId, pageNo);
	}
	
	
	//매치참가
	
	@Override
	public Page<ReservationDTO> matchingPage(String memberId, int pageNo) {
		// TODO Auto-generated method stub
		return dao.matchingPage(memberId, pageNo);
	}
	
	@Override
	public List<MatchingDTO> matchingUser(int reservationNo) {
		// TODO Auto-generated method stub
		return dao.matchingUser(reservationNo);
	}
	
	@Override
	public AbsentDTO absent(int matchNo, String memberId) {
		// TODO Auto-generated method stub
		return dao.absent(matchNo, memberId);
	}
	
	@Override
	public AbsentDTO absentCheck(int matchNo, String memberId) {
		// TODO Auto-generated method stub
		return dao.absentCheck(matchNo, memberId);
	}
	
	@Override
	public MatchingDTO cancelMatching(int reservationNo, String memberId) {
		// TODO Auto-generated method stub
		return dao.cancelMatching(reservationNo, memberId);
	}
	
	//강습

	@Override
	public List<TrainingDTO> trainingDate(Date trainingDate) {
		// TODO Auto-generated method stub
		return dao.trainingDate(trainingDate);
	}

	@Override
	public Page<TrainingDTO> trainingPage(String memberId, int pageNo) {
		// TODO Auto-generated method stub
		return dao.trainingPage(memberId, pageNo);
	}
	
	@Override
	public int trainingIncome(String memberId) {
		// TODO Auto-generated method stub
		return dao.trainingIncome(memberId);
	}

	//강습참가

	@Override
	public Page<TrainingDTO> trainingAttendPage(String memberId, int pageNo) {
		// TODO Auto-generated method stub
		return dao.trainingAttendPage(memberId, pageNo);
	}

	@Override
	public List<ReservationDTO> reservationDto(String memberId) {
		// TODO Auto-generated method stub
		return dao.reservationDto(memberId);
	}

	@Override
	public TrainingMemberlistDTO cancelTraining(int trainingNo, String memberId) {
		// TODO Auto-generated method stub
		return dao.cancelTraining(trainingNo, memberId);
	}

	

	@Override
	public List<MemberDTO> getAllMembers() {
		 return dao.getAllMembers();
	}


	

	

}
