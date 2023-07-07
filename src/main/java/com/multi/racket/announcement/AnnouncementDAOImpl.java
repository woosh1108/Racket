package com.multi.racket.announcement;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class AnnouncementDAOImpl implements AnnouncementDAO {
	AnnouncementDTO announcement;
	private AnnouncementRepository repository;

	@Autowired
	public AnnouncementDAOImpl(AnnouncementDTO announcement, AnnouncementRepository repository) {
		super();
		this.announcement = announcement;
		this.repository = repository;
	}

	// 게시글 등록
	@Override
	public String Announcement_insert(AnnouncementDTO Announcement) {
		repository.save(Announcement);
		return Integer.toString(Announcement.getAnnouncementNo());
	}

	// 공지시항 전체 목록
	@Override
	public List<AnnouncementDTO> announcementlist(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "announcementNo");
		Page<AnnouncementDTO> page = repository.findAll(pageRequest);
		List<AnnouncementDTO> list = page.getContent();

		return list;
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
	public void update(AnnouncementDTO updatedata) {
		AnnouncementDTO announcement = repository.findById(updatedata.getAnnouncementNo())
				.orElseThrow(() -> new RuntimeException());
		announcement.setAnnouncementTitle(updatedata.getAnnouncementTitle());
		announcement.setAnnouncementContent(updatedata.getAnnouncementContent());
		repository.save(announcement);
	}

	// 게시글 수정페이지를 가져옴
	@Override
	public AnnouncementDTO getAnnouncement(int announcementNo) {
		return repository.findById(announcementNo).orElseThrow();
	}

	// 페이지번호를 동적으로 생성하기 위한 메소드
	@Override
	public long getTotalPages(int pageSize) {
		long totalItems = repository.count();
		return (totalItems + pageSize - 1) / pageSize;
	}

	// 검색하여 공지사항 게시글 찾기
	@Override
	public List<AnnouncementDTO> search(String data) {
		List<AnnouncementDTO> announcementmemberId = repository.findBymemberIdContaining(data);
		List<AnnouncementDTO> announcementTitle = repository.findByannouncementTitleContaining(data);
		List<AnnouncementDTO> announcementContent = repository.findByannouncementContentContaining(data);

		List<AnnouncementDTO> result = new ArrayList<>();
		result.addAll(announcementmemberId);
		result.addAll(announcementTitle);
		result.addAll(announcementContent);

		List<AnnouncementDTO> uniqueResult = new ArrayList<>();

		// 글이 중복되어서 출력된다면
		for (AnnouncementDTO announcement : result) {
			if (!uniqueResult.contains(announcement)) {
				uniqueResult.add(announcement);
			}
		}

		return uniqueResult;
	}
}
