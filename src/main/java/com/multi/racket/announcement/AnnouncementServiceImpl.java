package com.multi.racket.announcement;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {
	private AnnouncementDAO Announcementdao;
	private AnnouncementRepository repository;

	@Autowired
	public AnnouncementServiceImpl(AnnouncementDAO announcementdao, AnnouncementRepository repository) {
		super();
		Announcementdao = announcementdao;
		this.repository = repository;
	}

	// 게시글 등록
	@Override
	public String Announcement_insert(AnnouncementDTO Announcement) {
		return Announcementdao.Announcement_insert(Announcement);
	}

	// 공지사항 전체 목록 조회
	@Override
	public List<AnnouncementDTO> announcementlist() {
		return Announcementdao.announcementlist();
	}

	// 게시글 상세보기
	@Override
	public AnnouncementDTO read(int announcementNo) {
		// TODO Auto-generated method stub
		return Announcementdao.read(announcementNo);
	}

	// 조회수 저장
	public AnnouncementDTO save(AnnouncementDTO announcement) {
		return repository.save(announcement);
	}
	
	//게시글 삭제
	@Override
	public void delete(int announcementNo) {
		Announcementdao.delete(announcementNo);
	}
	
	
	//게시글 수정
	@Override
	public AnnouncementDTO update(int announcementNo, AnnouncementDTO announcementDTO) {
		return Announcementdao.update(announcementNo, announcementDTO);
	}
	
	//게시글 수정페이지를 가져옴
	@Override
	public AnnouncementDTO getAnnouncement(int announcementNo) {
		return Announcementdao.getAnnouncement(announcementNo);
	}
}
