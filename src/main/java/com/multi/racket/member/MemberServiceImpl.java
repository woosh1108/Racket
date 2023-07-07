package com.multi.racket.member;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;

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

	@Override
	public List<ReservationDTO> reservationId(String memberId) {
		// TODO Auto-generated method stub
		return dao.reservationId(memberId);
	}
	
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

	@Override
	public List<TrainingDTO> training(String memberId) {
		// TODO Auto-generated method stub
		return dao.training(memberId);
	}

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
	public List<MemberDTO> getAllMembers() {
		 return dao.getAllMembers();
	}


	

	

	


}
