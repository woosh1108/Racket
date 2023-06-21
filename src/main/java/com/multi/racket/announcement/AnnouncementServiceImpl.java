package com.multi.racket.announcement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	// 공지사항 전체 목록 조회 - 페이징
	@Override
	public List<AnnouncementDTO> announcementlist(int pageNo) {
		return Announcementdao.announcementlist(pageNo);
	}

	// 공지사항 전체 목록 조회
	@Override
	public List<AnnouncementDTO> announcementlist() {
		// TODO Auto-generated method stub
		return null;
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

	// 게시글 삭제
	@Override
	public void delete(int announcementNo) {
		Announcementdao.delete(announcementNo);
	}

	// 게시글 수정
	@Override
	public void update(AnnouncementDTO updatedata) {
		Announcementdao.update(updatedata);
	}

	// 게시글 수정페이지를 가져옴
	@Override
	public AnnouncementDTO getAnnouncement(int announcementNo) {
		return Announcementdao.getAnnouncement(announcementNo);
	}
	
	//페이지번호를 동적으로 생성하기위한 메소드
	@Override
	public long getTotalPages(int pageSize) {
		return Announcementdao.getTotalPages(pageSize);
	}
	
	//검색하여 공지사항 게시글 찾기
	@Override
	public List<AnnouncementDTO> search(String data) {
		return Announcementdao.search(data);
	}
}
