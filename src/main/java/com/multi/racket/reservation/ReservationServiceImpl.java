package com.multi.racket.reservation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.ReservationRepository;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	ReservationRepository rRepository;
	CashRepository cashRepository;
	MatchingRepository mRepository;

	@Autowired
	public ReservationServiceImpl(ReservationRepository rRepository, CashRepository cashRepository,
			MatchingRepository mRepository) {
		super();
		this.rRepository = rRepository;
		this.cashRepository = cashRepository;
		this.mRepository = mRepository;
	}

	// 현재 캐시 잔액 조회
	@Override
    public boolean checkSufficientBalance(String memberId, int reservationFee) {
        // 최신 total_amount 조회
        int latestTotalAmount = cashRepository.findLatestTotalAmountByMemberId(memberId);
        return latestTotalAmount >= reservationFee;
    }
	

	@Override
	public ReservationDTO reservation(int reservationNo) {
		return rRepository.findById(reservationNo).orElseGet(ReservationDTO::new);
	}
	
	@Override
	public void reservation_insert(String memberId, ReservationDTO reservation, CashDTO cash) throws Exception {
	    try {
	        System.out.println("Service 성공: " + reservation + ", " + cash);
	        rRepository.save(reservation);
	        cashRepository.save(cash);
	    } catch (Exception e) {
	        System.out.println("Service 실패");
	        e.printStackTrace();
	        throw new Exception("Failed to create Reservation with Cash", e);
	    }
	}

	@Override
	public MatchingDTO matching(int matchingNo) {
		return mRepository.findById(matchingNo).orElseGet(MatchingDTO::new);
	}

	@Override
	public void matching_insert(String memberId, MatchingDTO matching, CashDTO cash, ReservationDTO reservation) throws Exception {
	    try {
	        System.out.println("Service 성공: " + matching + ", " + cash);
	        
	        // 매칭 정보 insert
	        cashRepository.save(cash);
	        mRepository.save(matching);
	        
	        // 해당 예약의 참가 인원수 조회
	        int participantCount = mRepository.getParticipantCount(matching.getReservationNo());
	        
	        // 예약 최대 인원수 조회
	        int maxCapacity = reservation.getPeopleNum();
	        System.out.println("service: "+participantCount+1);
	        System.out.println("service: "+maxCapacity);
	        
	        // 예약 최대 인원보다 1명 적을 때 예약 상태를 "매칭완료"로 변경
	        if (participantCount + 1 == maxCapacity) {
	        	rRepository.updateReservationStatus(matching.getReservationNo(), "매칭완료");
	        }
	    } catch (Exception e) {
	        System.out.println("Service 실패");
	        e.printStackTrace();
	        throw new Exception("Failed to create Reservation with Cash", e);
	    }
	}

	@Override
	public Page<ReservationDTO> reservationlist(int pageNo) {
		int pageSize = 10; // 페이지당 표시할 데이터 수

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("reservationNo").descending());
		return rRepository.findAllByReservationStatus("매칭중", pageable);
	}

	@Override
	public Page<ReservationDTO> searchReservations(String type, String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("reservationNo").descending());

		if (type.equals("met")) {
			return rRepository.findByReservationMetContaining(keyword, pageable);
		} else if (type.equals("gender")) {
			return rRepository.findByReservationGenderContaining(keyword, pageable);
		} else if (type.equals("grade")) {
			return rRepository.findByGradeSettingContaining(keyword, pageable);
		} else {
			return Page.empty(); // 빈 페이지 반환
		}
	}

	@Override
	public void updateExpiredReservations(LocalDate currentDate) {
		// 현재 날짜 이후의 예약 데이터를 조회하고 상태를 "경기종료"로 수정하는 로직
        List<ReservationDTO> expiredReservations = rRepository.findByReservationDateBeforeAndReservationStatus(currentDate, "경기진행중");
        for (ReservationDTO reservation : expiredReservations) {
            reservation.setReservationStatus("경기종료");
        }
        rRepository.saveAll(expiredReservations);
	}

	@Override
	public boolean existsByMemberIdAndReservationNo(String memberId, int reservationNo) {
		boolean Whether = mRepository.existsByMemberIdAndReservationNo(memberId, reservationNo);
		return Whether;
	}


}
