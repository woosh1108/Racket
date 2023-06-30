package com.multi.racket.announcement;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.racket.announcement.AnnouncementDTO;

public interface AnnouncementService {

	// 게시글 등록
//	public AnnouncementDTO Announcement_insert(AnnouncementDTO Announcement);

	// 게시글 등록
	public String Announcement_insert(AnnouncementDTO Announcement);

	// 공지시항 전체 목록
	// 페이징
	public List<AnnouncementDTO> announcementlist(int pageNo);

	public List<AnnouncementDTO> announcementlist();

	// 게시글 상세보기
	public AnnouncementDTO read(int announcementNo);

	// 조회수 저장
	public AnnouncementDTO save(AnnouncementDTO announcement);

	// 게시글 삭제
	public void delete(int announcementNo);

	// 게시글 수정
	public void update(AnnouncementDTO updatedata);

	// 게시글 수정페이지 - 수정버튼을 누르면 글을 가져옴
	public AnnouncementDTO getAnnouncement(int announcementNo);

	// 페이지 번호를 동적으로 생성하기 위한 메소드
	public long getTotalPages(int pageSize);

	// 검색하여 공지사항 게시글 찾기
	public List<AnnouncementDTO> search(String data);

}
