package com.multi.racket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumcourtDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, String> {
	MemberDTO findByMemberIdAndMemberPass(String memberId,String memberPass);
	MemberDTO findByMemberNameAndMemberEmail(String memberName, String memeberEmail);
	Optional<MemberDTO> findByMemberId(String memberId);

	@Modifying
    @Query("UPDATE MemberDTO m SET m.memberStatus = 0 WHERE m.memberId = :memberId")
    void updateMemberAuthByMemberId(@Param("memberId") String memberId);
}