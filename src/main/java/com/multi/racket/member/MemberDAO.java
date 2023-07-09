package com.multi.racket.member;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.AbsentDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;

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

	// 나의 예약목록  시간
	List<ReservationDTO> reservationDate(Date reservationDate);
	// 나의 예약목록 페이징처리
	Page<ReservationDTO> reservationPage(String memberId,int pageNo);
	List<ReservationDTO> reservationDto(String memberId);
	// 예약취소처리
	ReservationDTO cancelReservation(int reservationNo, String memberId, int reservationFee);
	// 상세보기위해서 코트번호 가져오기
	int reservationCourtNo(int reservationNo);
	
	// 나의 매치목록 페이징처리
	Page<ReservationDTO> matchingPage(String memberId,int pageNo);	
	// 매칭들 목록가져오기
	List<MatchingDTO> matchingUser(int reservationNo);
	// 신고자처리
	AbsentDTO absent(int matchNo, String memberId);
	// 신고자처리 - 확인작업
	AbsentDTO absentCheck(int matchNo, String memberId);
	// 매칭취소처리
	MatchingDTO cancelMatching(int reservationNo, String memberId, int reservationFee);
	
	// 나의 강습목록  시간
	List<TrainingDTO> trainingDate(Date trainingDate);
	// 나의 강습목록 페이징처리
	Page<TrainingDTO> trainingPage(String memberId,int pageNo);
	// 나의 강습 총수입
	int trainingIncome(String memberId);
	// 매칭취소처리
	TrainingDTO cancelTraining(int trainingNo, String memberId, int trainingFee, int courtHourNo);
	// 상세보기위해서 코트번호 가져오기
	int trainingCourtNo(int trainingNo);
	
	// 나의 강습 참가목록 페이징처리
	Page<TrainingDTO> trainingAttendPage(String memberId,int pageNo);
	// 매칭취소처리
	TrainingMemberlistDTO cancelTrainingAttend(int trainingNo, String memberId, int trainingFee);
	
	// 모든 멤버 정보 가져오기
    List<MemberDTO> getAllMembers();

}
