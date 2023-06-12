package com.multi.racket.announcement;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	//게시글 등록
	@Override
	public int Announcement_insert(AnnouncementDTO Announcement) {
		return Announcementdao.Announcement_insert(Announcement);
	}
	
	//공지사항 전체 목록 조회
	@Override
	public List<AnnouncementDTO> announcementlist() {
		return repository.findAll();
	}

}
