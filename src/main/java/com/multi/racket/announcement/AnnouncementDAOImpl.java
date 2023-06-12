package com.multi.racket.announcement;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnnouncementDAOImpl implements AnnouncementDAO {
	private EntityManager AnnouncementEntityManager;
	private AnnouncementRepository repository;
	
	
	
	@Autowired
	public AnnouncementDAOImpl(EntityManager announcementEntityManager, AnnouncementRepository repository) {
		super();
		AnnouncementEntityManager = announcementEntityManager;
		this.repository = repository;
	}

	//게시글 등록
	@Override
	public int Announcement_insert(AnnouncementDTO Announcement) {
		repository.save(Announcement);
		return Announcement.getAnnouncementNo();
	}
	
	//공지시항 전체 목록
	@Override
	public List<AnnouncementDTO> announcementlist() {
		return repository.findAll();
	}

}
