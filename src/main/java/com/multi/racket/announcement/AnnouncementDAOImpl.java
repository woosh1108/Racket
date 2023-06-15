package com.multi.racket.announcement;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnnouncementDAOImpl implements AnnouncementDAO {
	AnnouncementDTO announcement;
	private AnnouncementRepository repository;
	private EntityManager entityManager;

	@Autowired
	public AnnouncementDAOImpl(AnnouncementDTO announcement, AnnouncementRepository repository,  EntityManager entityManager) {
		super();
		this.announcement = announcement;
		this.repository = repository;
		this.entityManager = entityManager;
	}

	// 게시글 등록
	@Override
	public String Announcement_insert(AnnouncementDTO Announcement) {
//		announcement.setAnnouncementModifyDate(null); // insert시에 수정일자를 null로 설정
//		announcement.setAnnouncementDate(new java.sql.Date(new java.util.Date().getTime()));

		repository.save(Announcement);
		return Integer.toString(Announcement.getAnnouncementNo());
	}

	// 공지시항 전체 목록
	@Override
	public List<AnnouncementDTO> announcementlist() {
		return repository.findAll();
	}

	// 게시글 상세보기
	@Override
	public AnnouncementDTO read(int announcementNo) {
		Optional<AnnouncementDTO> announcementOptional = repository.findById(announcementNo);
		AnnouncementDTO announcement = announcementOptional.orElse(null);
		return announcement;
	}

	// 게시글 삭제
	@Override
	public void delete(int announcementNo) {
		// 게시글 조회
		AnnouncementDTO announcement = repository.findById(announcementNo).orElse(null);
		if (announcement != null) {
			// 조회한 게시글이 존재하면 삭제
			repository.deleteById(announcementNo);
		} else {
			System.out.println(announcementNo + "번의 게시글을 찾을 수 없습니다.");
		}
	}

	// 게시글 수정
	@Override
	public AnnouncementDTO update(int announcementNo, AnnouncementDTO announcementDTO) {
		AnnouncementDTO existingAnnouncement = repository.findById(announcementNo).orElseThrow();

        existingAnnouncement.setAnnouncementTitle(announcementDTO.getAnnouncementTitle());
        existingAnnouncement.setAnnouncementContent(announcementDTO.getAnnouncementContent());

        return repository.save(existingAnnouncement);
	}
	
	//게시글 수정페이지를 가져옴
	@Override
	public AnnouncementDTO getAnnouncement(int announcementNo) {
		return repository.findById(announcementNo).orElseThrow();
	}
}
