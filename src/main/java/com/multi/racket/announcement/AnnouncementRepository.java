package com.multi.racket.announcement;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementDTO, Integer> {
	//해당 코드는 DTO에 있는 필드명을 정확히 적어줘야 에러가 안 뜬다.
	List<AnnouncementDTO> findBymemberIdContaining(String keyword);
	List<AnnouncementDTO> findByannouncementTitleContaining(String keyword);
	List<AnnouncementDTO> findByannouncementContentContaining(String keyword);
}
