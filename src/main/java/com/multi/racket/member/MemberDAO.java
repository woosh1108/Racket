package com.multi.racket.member;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;

public interface MemberDAO {
	// 로그인
	MemberDTO login(String memberId, String memberPass);

	// 개인정보 보기
	MemberDTO info(String memberId);
	
	// 개인정보 수정
	public void update(MemberDTO updatedata);

	// 비밀번호 변경
	public void updatePass(MemberDTO updateUser, String memberId, String memberPass);

	// 비밀번호찾기 -> 아이디 확인
	MemberDTO idCheck(String memberId);

	// 아이디 찾기
	MemberDTO findId(String memberName, String memberEmail);

	// 나의 예약목록
	List<ReservationDTO> reservationId(String memberId);
	// 나의 예약목록  시간
	List<ReservationDTO> reservationDate(Date reservationDate);
	// 나의 예약목록 페이징처리
	Page<ReservationDTO> reservationPage(String memberId,int pageNo);

	// 나의 강습목록
	List<TrainingDTO> training(String memberId);
	// 나의 강습목록  시간
	List<TrainingDTO> trainingDate(Date trainingDate);
	// 나의 강습목록 페이징처리
	Page<TrainingDTO> trainingPage(String memberId,int pageNo);
	// 모든 멤버 정보 가져오기
    List<MemberDTO> getAllMembers();

}
